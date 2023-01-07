package arachne4.lib.units;

public abstract class Velocity extends Unit<Velocity> {
    public static final Velocity ZERO = new MetresPerSec(0);

    protected Velocity(double metresPerSec) {
        super(metresPerSec);
    }

    @Override
    protected Velocity fromRaw(double raw) {
        return new MetresPerSec(raw);
    }

    @Override
    public String toString() {
        return "Velocity(" + raw + "m/s)";
    }
}
