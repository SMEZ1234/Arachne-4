package arachne4.lib;

import java.util.function.Function;

import arachne4.lib.game.GameState;
import arachne4.lib.logging.ArachneLogger;
import arachne4.lib.scheduler.Scheduler;
import arachne4.lib.scheduler.SchedulerProvider;
import arachne4.lib.scheduler.data.ValueChange;
import arachne4.lib.subsystems.Subsystem;
import arachne4.lib.units.Duration;
import arachne4.lib.units.Microseconds;
import arachne4.lib.units.Seconds;
import edu.wpi.first.hal.HAL;
import edu.wpi.first.hal.NotifierJNI;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.Watchdog;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ArachneRobot4<RobotT> extends RobotBase implements SchedulerProvider {
	public static final Duration DEFAULT_PERIOD = new Seconds(0.02);

	protected final long loopPeriodMicroseconds;
	protected final Watchdog watchdog;
	
	protected final Scheduler scheduler;

	// The C pointer to the notifier object. We don't use it directly, it is
	// just passed to the JNI bindings.
	protected final int notifierHandle = NotifierJNI.initializeNotifier();

	protected GameState gameState = GameState.PRE_INIT;
	
	protected ArachneRobot4(Function<SchedulerProvider, RobotT> robotSupplier, Duration loopPeriod) {
		this.loopPeriodMicroseconds = (long) loopPeriod.asRaw(Microseconds::convert);
	    this.watchdog = new Watchdog(loopPeriod.asRaw(Seconds::convert), this::handleLoopOverrun);

		this.scheduler = new Scheduler();
		robotSupplier.apply(this);
	}

	public static <RobotT extends Subsystem<?>> void startArachneRobot(Function<SchedulerProvider, RobotT> robotSupplier) {
		startArachneRobot(robotSupplier, DEFAULT_PERIOD);
	}

	public static <RobotT extends Subsystem<?>> void startArachneRobot(Function<SchedulerProvider, RobotT> robotSupplier, Duration loopPeriod) {
		startRobot(() -> new ArachneRobot4<>(robotSupplier, loopPeriod));
	}

	@Override
	public Scheduler getScheduler() {
		return scheduler;
	}

	@Override
	public void startCompetition() {
		fire(Scheduler.INITIALIZE);

		// Tell the DS that the robot is ready to be enabled
    	System.out.println("********** Robot program startup complete **********");
		HAL.observeUserProgramStarting();

		var nextLoopTimeMicroseconds = RobotController.getFPGATime() + loopPeriodMicroseconds;
		NotifierJNI.updateNotifierAlarm(notifierHandle, nextLoopTimeMicroseconds);

		// Loop forever, calling Arachne's execution and state change functions
		while(true) {
			try {
				if(NotifierJNI.waitForNotifierAlarm(notifierHandle) == 0) break;

				nextLoopTimeMicroseconds += loopPeriodMicroseconds;
				NotifierJNI.updateNotifierAlarm(notifierHandle, nextLoopTimeMicroseconds);

				loopFunc();
			}
			catch(Exception e) {
				errorFallback(e);
			}
		}
	}
	
	@Override
	public void endCompetition() {
		NotifierJNI.stopNotifier(notifierHandle);
		fire(Scheduler.END);
	}

	protected void loopFunc() {
		watchdog.reset();
		
		// Determine current state
		GameState newState;
		if(isDisabled()) newState = GameState.DISABLED;
		else if(isAutonomous()) newState = GameState.AUTO;
		else if(isTeleop()) newState = GameState.TELEOP;
		else newState = GameState.TELEOP;
		
		// Handle state change
		if(gameState != newState) {
			// Only enable live window and actuator widgets in test mode
			if(newState == GameState.TEST) {
				LiveWindow.setEnabled(true);
				Shuffleboard.enableActuatorWidgets();
			}
			else {
				LiveWindow.setEnabled(false);
				Shuffleboard.disableActuatorWidgets();
			}
			
			// Handle mode-specific state changes
			fire(Scheduler.GAME_STATE_CHANGE, new ValueChange<GameState>(gameState, newState));
			watchdog.addEpoch("onStateChange: " + gameState + " -> " + newState);
		}
		
		// Execute periodic code for state
		gameState = newState;
		gameState.halObserver.run();
		
		fire(Scheduler.PRE_EXECUTE, gameState);
		watchdog.addEpoch("pre-execute: " + gameState);
		
		fire(Scheduler.EXECUTE, gameState);
		watchdog.addEpoch("execute: " + gameState);
		
		fire(Scheduler.POST_EXECUTE, gameState);
		watchdog.addEpoch("post-execute: " + gameState);
		
		watchdog.disable();
		
		// Update displayed values
		SmartDashboard.updateValues();
		LiveWindow.updateValues();
		Shuffleboard.update();

		// Warn on loop time overruns
		if(watchdog.isExpired()) watchdog.printEpochs();
	}

	protected void errorFallback(Exception exception) {
		ArachneLogger.getInstance().critical("Error from inside robot's loopFunc(): " + exception.getMessage());
		DriverStation.reportError(exception.getMessage(), exception.getStackTrace());
	}

	protected void handleLoopOverrun() {
		DriverStation.reportWarning("Loop time of " + (loopPeriodMicroseconds * 1e-6d) + "s overrun\n", false);
	}
}
