package arachne4.lib.units;

public class Microseconds extends Duration {
    public Microseconds(double microseconds) {
        super(microseconds * 1e-6);
    }

    public static double convert(double seconds) {
        return seconds * 1e6;
    }
}
