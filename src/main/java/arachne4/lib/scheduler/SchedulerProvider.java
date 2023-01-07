package arachne4.lib.scheduler;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.DoublePredicate;
import java.util.function.Predicate;

import arachne4.lib.function.BooleanPredicate;
import arachne4.lib.scheduler.EventTypeSystem.BooleanDataEvent;
import arachne4.lib.scheduler.EventTypeSystem.DataEvent;
import arachne4.lib.scheduler.EventTypeSystem.DoubleDataEvent;
import arachne4.lib.scheduler.EventTypeSystem.Event;
import arachne4.lib.scheduler.data.BooleanValueChange;
import arachne4.lib.scheduler.data.DoubleValueChange;
import arachne4.lib.scheduler.data.ValueChange;
import edu.wpi.first.util.function.BooleanConsumer;

public interface SchedulerProvider {
    Scheduler getScheduler();

    default void fire(Event event) {
        getScheduler().fire(event);
    }

    default <DataT> void fire(DataEvent<DataT> event, DataT data) {
        getScheduler().fire(event, data);
    }

    default void registerHandler(Event trigger, Runnable handler) {
        getScheduler().registerHandler(trigger, handler);
    }

    // Generic data events

    default <DataT> void registerHandler(DataEvent<DataT> trigger, Consumer<DataT> handler) {
        getScheduler().registerHandler(trigger, handler);
    }

    default <DataT> void registerHandler(DataEvent<DataT> trigger, Runnable handler) {
        getScheduler().registerHandler(trigger, (data) -> handler.run());
    }

    default <DataT> void registerHandler(DataEvent<DataT> trigger, DataT requiredValue, Runnable handler) {
        getScheduler().registerHandler(trigger, (data) -> {
            if(Objects.equals(data, requiredValue)) handler.run();
        });
    }

    default <DataT> void registerHandler(DataEvent<DataT> trigger, DataT[] requiredValues, Runnable handler) {
        for(DataT requiredValue : requiredValues) registerHandler(trigger, requiredValue, handler);
    }

    default <DataT> void registerHandler(DataEvent<DataT> trigger, Predicate<DataT> requiredValueFilter, Runnable handler) {
        getScheduler().registerHandler(trigger, (data) -> {
            if(requiredValueFilter.test(data)) handler.run();
        });
    }

    // Numeric data events

    default void registerHandler(DoubleDataEvent trigger, DoubleConsumer handler) {
        getScheduler().registerHandler(trigger, handler);
    }

    default void registerHandler(DoubleDataEvent trigger, Runnable handler) {
        getScheduler().registerHandler(trigger, (data) -> handler.run());
    }

    default void registerHandler(DoubleDataEvent trigger, DoublePredicate requiredValueFilter, Runnable handler) {
        getScheduler().registerHandler(trigger, (data) -> {
            if(requiredValueFilter.test(data)) handler.run();
        });
    }

    // Boolean data events

    default void registerHandler(BooleanDataEvent trigger, BooleanConsumer handler) {
        getScheduler().registerHandler(trigger, handler);
    }

    default void registerHandler(BooleanDataEvent trigger, Runnable handler) {
        getScheduler().registerHandler(trigger, (data) -> handler.run());
    }

    default void registerHandler(BooleanDataEvent trigger, BooleanPredicate requiredValueFilter, Runnable handler) {
        getScheduler().registerHandler(trigger, (data) -> {
            if(requiredValueFilter.test(data)) handler.run();
        });
    }

    // Change to and from modifiers
    // TODO Add predicates and double specialisations

    default <DataT> Predicate<ValueChange<DataT>> to(DataT requiredValue) {
        return (changeEvent) -> Objects.equals(changeEvent.to, requiredValue);
    }

    default Predicate<BooleanValueChange> to(boolean requiredValue) {
        return (changeEvent) -> changeEvent.to == requiredValue;
    }

    default <DataT> Predicate<ValueChange<DataT>> from(DataT requiredValue) {
        return (changeEvent) -> Objects.equals(changeEvent.from, requiredValue);
    }

    default Predicate<BooleanValueChange> from(boolean requiredValue) {
        return (changeEvent) -> changeEvent.from == requiredValue;
    }

    default <DataT> Consumer<ValueChange<DataT>> usingToValue(Consumer<DataT> handler) {
        return (changeEvent) -> handler.accept(changeEvent.to);
    }

    default Consumer<DoubleValueChange> usingToDouble(DoubleConsumer handler) {
        return (changeEvent) -> handler.accept(changeEvent.to);
    }

    default Consumer<BooleanValueChange> usingToBoolean(BooleanConsumer handler) {
        return (changeEvent) -> handler.accept(changeEvent.to);
    }

    default <DataT> Consumer<ValueChange<DataT>> usingFromValue(Consumer<DataT> handler) {
        return (changeEvent) -> handler.accept(changeEvent.from);
    }

    default Consumer<DoubleValueChange> usingFromDouble(DoubleConsumer handler) {
        return (changeEvent) -> handler.accept(changeEvent.from);
    }

    default Consumer<BooleanValueChange> usingFromBoolean(BooleanConsumer handler) {
        return (changeEvent) -> handler.accept(changeEvent.from);
    }
}
