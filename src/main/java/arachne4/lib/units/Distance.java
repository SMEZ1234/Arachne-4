package arachne4.lib.units;

public abstract class Distance extends Unit<Distance> {
    public static final Distance ZERO = new Metres(0);

    protected Distance(double metres) {
        super(metres);
    }

    @Override
    protected Distance fromRaw(double raw) {
        return new Metres(raw);
    }

    @Override
    public String toString() {
        return "Distance(" + raw + "m)";
    }
}
