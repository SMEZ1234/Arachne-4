package arachne4.lib.units;

public abstract class RotationalVelocity extends Unit<RotationalVelocity> {
    public static final RotationalVelocity ZERO = new RotationsPerSec(0);

    protected RotationalVelocity(double rotationsPerSec) {
        super(rotationsPerSec);
    }

    @Override
    protected RotationalVelocity fromRaw(double raw) {
        return new RotationsPerSec(raw);
    }

    @Override
    public String toString() {
        return "RotationalVelocity(" + raw + " rot/s)";
    }
}
