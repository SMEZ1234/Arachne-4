package arachne4.lib.units;

public class Millimetres extends Distance {
    public Millimetres(double millimetres) {
        super(millimetres / 1000);
    }

    public static double convert(double metres) {
        return metres * 1000;
    }
}
