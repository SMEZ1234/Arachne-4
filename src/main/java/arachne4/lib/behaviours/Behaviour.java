package arachne4.lib.behaviours;

public interface Behaviour {
    default void onEnterMode() {}
    default void run() {}
    default void onLeaveMode() {}

    public static <ModeT extends Behaviour> ModeT thatRunsOnce(Runnable onEnterMode) {
        return null;
    }

    public static <ModeT extends Behaviour> ModeT thatRunsRepeatedly(Runnable run) {
        return null;
    }
}
