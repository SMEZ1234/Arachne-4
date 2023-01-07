package arachne4.lib.scheduler;

public class SchedulerProviderBase implements SchedulerProvider {
    private final Scheduler scheduler;

    public SchedulerProviderBase(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public Scheduler getScheduler() {
        return scheduler;
    }
}
