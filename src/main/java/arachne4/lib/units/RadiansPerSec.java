package arachne4.lib.units;

public class RadiansPerSec extends RotationalVelocity {
    public RadiansPerSec(double radiansPerSec) {
        super(radiansPerSec / (2 * Math.PI));
    }

    public static double convert(double rotationsPerSec) {
        return rotationsPerSec * 2 * Math.PI;
    }
}
