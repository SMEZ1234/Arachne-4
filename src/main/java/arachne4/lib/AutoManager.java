package arachne4.lib;

import java.util.function.Function;

import arachne4.lib.sequences.ActionConductor;
import arachne4.lib.sequences.Actionable;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class AutoManager<RobotT, AutoT extends Function<RobotT, Actionable>> implements Runnable
{
	protected static final String DASHBOARD_TAB = "Autonomous";
	protected static final String DEFAULT_SELECTION_KEY = "Auto selection";
	
	protected final ActionConductor conductor;
	protected final SendableChooser<Actionable> autoChooser;
	
	public AutoManager(RobotT robot, AutoT defaultAuto, AutoT[] autos) {
		this(robot, defaultAuto, autos, DEFAULT_SELECTION_KEY);
	}
	
	public AutoManager(RobotT robot, AutoT defaultAuto, AutoT[] autos, String selectionKey) {		
		this.conductor = new ActionConductor();
		
		this.autoChooser = new SendableChooser<Actionable>();
		this.autoChooser.setDefaultOption(defaultAuto.toString(), defaultAuto.apply(robot));
		
		for(AutoT auto : autos) {
			if(auto != defaultAuto) autoChooser.addOption(auto.toString(), auto.apply(robot));
		}

		Shuffleboard.getTab(DASHBOARD_TAB).add(selectionKey, autoChooser);
	}
	
	public void startAuto() {
		Actionable auto = autoChooser.getSelected();
		startAuto(auto == null ? Actionable.DO_NOTHING() : auto);
	}
	
	public void startAuto(Actionable auto) {
		stopAuto();
		conductor.add(auto);
	}
	
	public void stopAuto() {
		conductor.interrupt();
	}
	
	public boolean isRunning() {
		return conductor.hasActions();
	}

	@Override
	public void run() {
		conductor.run();
	}
}
