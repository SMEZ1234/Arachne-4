package arachne4.lib.scheduler.data;

public class ValueChange<DataT> {
    public final DataT from, to;

    public ValueChange(DataT from, DataT to) {
        this.from = from;
        this.to = to;
    }
}
