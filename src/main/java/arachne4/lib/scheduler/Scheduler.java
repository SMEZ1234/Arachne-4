package arachne4.lib.scheduler;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;

import arachne4.lib.game.GameState;
import arachne4.lib.scheduler.EventTypeSystem.BooleanDataEvent;
import arachne4.lib.scheduler.EventTypeSystem.DataEvent;
import arachne4.lib.scheduler.EventTypeSystem.DoubleDataEvent;
import arachne4.lib.scheduler.EventTypeSystem.Event;
import arachne4.lib.scheduler.data.ValueChange;
import edu.wpi.first.util.function.BooleanConsumer;

public class Scheduler {
    public static final Event
        INITIALIZE = new Event(),
        END = new Event();

    public static final DataEvent<GameState>
        PRE_EXECUTE = new DataEvent<>(),
        EXECUTE = new DataEvent<>(),
        POST_EXECUTE = new DataEvent<>();

    public static final DataEvent<ValueChange<GameState>> GAME_STATE_CHANGE = new DataEvent<>();

    private final Map<Event, List<Runnable>> handlers = new HashMap<>();
    private final Map<DataEvent<?>, List<Consumer<?>>> dataHandlers = new HashMap<>();
    private final Map<BooleanDataEvent, List<BooleanConsumer>> booleanDataHandlers = new HashMap<>();
    private final Map<DoubleDataEvent, List<DoubleConsumer>> doubleDataHandlers = new HashMap<>();

    public void fire(Event event) {
        if(!handlers.containsKey(event)) return;

        for(var handler : handlers.get(event)) handler.run();
    }

    @SuppressWarnings("unchecked")
    public <DataT> void fire(DataEvent<DataT> event, DataT data) {
        if(!dataHandlers.containsKey(event)) return;

        for(var handler : dataHandlers.get(event)) ((Consumer<DataT>) handler).accept(data);
    }

    public void fire(BooleanDataEvent event, boolean data) {
        if(!booleanDataHandlers.containsKey(event)) return;

        for(var handler : booleanDataHandlers.get(event)) handler.accept(data);
    }

    public void fire(DoubleDataEvent event, double data) {
        if(!doubleDataHandlers.containsKey(event)) return;

        for(var handler : doubleDataHandlers.get(event)) handler.accept(data);
    }

    public void registerHandler(Event trigger, Runnable handler) {
        if(!handlers.containsKey(trigger)) handlers.put(trigger, new LinkedList<>());

        handlers.get(trigger).add(handler);
    }

    public <DataT> void registerHandler(DataEvent<DataT> trigger, Consumer<DataT> handler) {
        if(!dataHandlers.containsKey(trigger)) dataHandlers.put(trigger, new LinkedList<>());

        dataHandlers.get(trigger).add(handler);
    }

    public void registerHandler(BooleanDataEvent trigger, BooleanConsumer handler) {
        if(!booleanDataHandlers.containsKey(trigger)) booleanDataHandlers.put(trigger, new LinkedList<>());

        booleanDataHandlers.get(trigger).add(handler);
    }

    public void registerHandler(DoubleDataEvent trigger, DoubleConsumer handler) {
        if(!doubleDataHandlers.containsKey(trigger)) doubleDataHandlers.put(trigger, new LinkedList<>());

        doubleDataHandlers.get(trigger).add(handler);
    }
}
