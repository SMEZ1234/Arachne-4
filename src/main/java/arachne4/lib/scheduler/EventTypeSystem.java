package arachne4.lib.scheduler;

public class EventTypeSystem {
    public static interface _Event{}
    public static class Event implements _Event{}
    public static class DataEvent<DataT> implements _Event{}
    public static class BooleanDataEvent implements _Event{}
    public static class DoubleDataEvent implements _Event{}
}
