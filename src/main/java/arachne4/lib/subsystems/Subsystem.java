package arachne4.lib.subsystems;

import arachne4.lib.scheduler.Scheduler;
import arachne4.lib.scheduler.SchedulerProvider;

public class Subsystem<MappingT> implements SchedulerProvider {
    private final Scheduler scheduler;
    public final MappingT mappings;

    public Subsystem(SchedulerProvider schedulerProvider, MappingT mappings) {
        this.scheduler = schedulerProvider.getScheduler();
        this.mappings = mappings;
    }

    @Override
    public Scheduler getScheduler() {
        return scheduler;
    }
}
