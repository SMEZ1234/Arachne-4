package arachne4.lib.units;

public class RotationsPerSec extends RotationalVelocity {
    public RotationsPerSec(double rotationsPerSec) {
        super(rotationsPerSec);
    }

    public static double convert(double rotationsPerSec) {
        return rotationsPerSec;
    }
}
