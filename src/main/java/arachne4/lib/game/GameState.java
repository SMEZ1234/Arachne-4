package arachne4.lib.game;

import edu.wpi.first.hal.HAL;

public enum GameState {
	PRE_INIT(() -> {}), // Default non-null state the robot transitions from when initialising into DISABLED
	DISABLED(HAL::observeUserProgramDisabled),
	AUTO(HAL::observeUserProgramAutonomous),
	TELEOP(HAL::observeUserProgramTeleop),
	TEST(HAL::observeUserProgramTest);

	public static final GameState[] DRIVER_CONTROLLED_STATES = {TELEOP, TEST};

	public final Runnable halObserver;

	private GameState(Runnable halObserver) {
		this.halObserver = halObserver;
	}
}
