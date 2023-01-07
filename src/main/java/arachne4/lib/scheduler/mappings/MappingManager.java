package arachne4.lib.scheduler.mappings;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

import arachne4.lib.scheduler.Scheduler;
import arachne4.lib.scheduler.SchedulerProvider;

public class MappingManager<ControllersT> {
    protected final Scheduler scheduler;
    protected final ControllersT controllers;

    public MappingManager(SchedulerProvider schedulerProvider, ControllersT controllers) {
        this.scheduler = schedulerProvider.getScheduler();
        this.controllers = controllers;
    }

    protected ButtonMapping mapping(BooleanSupplier button) {
        return new ButtonMapping(scheduler, button);
    }

    protected AxisMapping mapping(DoubleSupplier axis) {
        return new AxisMapping(scheduler, axis);
    }

    protected <DataT> ComplexMapping<DataT> mapping(Supplier<DataT> dataProvider) {
        return new ComplexMapping<>(scheduler, dataProvider);
    }
}
