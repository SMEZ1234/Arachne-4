package arachne4.lib.units;

public abstract class Duration extends Unit<Duration> {
    public static final Duration ZERO = new Seconds(0);

    protected Duration(double seconds) {
        super(seconds);
    }

    @Override
    protected Duration fromRaw(double raw) {
        return new Seconds(raw);
    }

    @Override
    public String toString() {
        return "Duration(" + raw + "s)";
    }
}
