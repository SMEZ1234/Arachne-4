package arachne4.lib.scheduler.mappings;

import java.util.function.DoubleSupplier;

import arachne4.lib.game.GameState;
import arachne4.lib.scheduler.Scheduler;
import arachne4.lib.scheduler.EventTypeSystem.DataEvent;
import arachne4.lib.scheduler.EventTypeSystem.DoubleDataEvent;
import arachne4.lib.scheduler.data.DoubleValueChange;

public class AxisMapping {
    private final Scheduler scheduler;
    private final DoubleSupplier axis;

    private DoubleDataEvent consumerEvent;
    private DataEvent<DoubleValueChange> onChangeEvent;

    private double valueLastCycle = Double.NaN;

    // FIXME add parameter to handlers to run in disabled
    public AxisMapping(Scheduler scheduler, DoubleSupplier axis) {
        this.scheduler = scheduler;
        this.axis = axis;

        scheduler.registerHandler(Scheduler.EXECUTE, (gameState) -> {
            if(gameState == GameState.AUTO) return;

            var value = axis.getAsDouble();

            if(value != valueLastCycle && onChangeEvent != null) scheduler.fire(onChangeEvent, new DoubleValueChange(valueLastCycle, value));

            if(consumerEvent != null) scheduler.fire(consumerEvent, value);

            value = valueLastCycle;
        });
    }

    public DoubleDataEvent consume() {
        if(consumerEvent == null) consumerEvent = new DoubleDataEvent();

        return consumerEvent;
    }

    public DataEvent<DoubleValueChange> onChange() {
        if(onChangeEvent == null) onChangeEvent = new DataEvent<>();

        return onChangeEvent;
    }

    public double get() {
        return axis.getAsDouble();
    }
}
