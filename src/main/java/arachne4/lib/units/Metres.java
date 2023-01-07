package arachne4.lib.units;

public class Metres extends Distance {
    public Metres(double metres) {
        super(metres);
    }

    public static double convert(double metres) {
        return metres;
    }
}
