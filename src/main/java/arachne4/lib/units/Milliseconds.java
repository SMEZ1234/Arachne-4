package arachne4.lib.units;

public class Milliseconds extends Duration {
    public Milliseconds(double milliseconds) {
        super(milliseconds / 1000);
    }

    public static double convert(double seconds) {
        return seconds * 1000;
    }
}
