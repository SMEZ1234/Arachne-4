package arachne4.lib.scheduler.mappings;

import java.util.Objects;
import java.util.function.Supplier;

import arachne4.lib.game.GameState;
import arachne4.lib.scheduler.Scheduler;
import arachne4.lib.scheduler.EventTypeSystem.DataEvent;
import arachne4.lib.scheduler.data.ValueChange;

public class ComplexMapping<DataT> {
    private final Scheduler scheduler;
    private final Supplier<DataT> valueProvider;

    private DataEvent<DataT> consumerEvent;
    private DataEvent<ValueChange<DataT>> onChangeEvent;

    private DataT valueLastCycle = null;

    // FIXME add parameter to handlers to run in disabled
    public ComplexMapping(Scheduler scheduler, Supplier<DataT> dataSupplier) {
        this.scheduler = scheduler;
        this.valueProvider = dataSupplier;

        scheduler.registerHandler(Scheduler.EXECUTE, (gameState) -> {
            if(gameState == GameState.AUTO) return;

            var value = dataSupplier.get();

            if(!Objects.equals(value, valueLastCycle) && onChangeEvent != null) scheduler.fire(onChangeEvent, new ValueChange<DataT>(valueLastCycle, value));

            if(consumerEvent != null) scheduler.fire(consumerEvent, value);

            value = valueLastCycle;
        });
    }

    public DataEvent<DataT> consume() {
        if(consumerEvent == null) consumerEvent = new DataEvent<>();

        return consumerEvent;
    }

    public DataEvent<ValueChange<DataT>> onChange() {
        if(onChangeEvent == null) onChangeEvent = new DataEvent<>();

        return onChangeEvent;
    }

    public DataT get() {
        return valueProvider.get();
    }
}
