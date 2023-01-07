package arachne4.lib.math;

public class ArachneMath {
	public static double signedPow(double value, double exponent) {
		if(value < 0) return -Math.pow(-value, exponent);
		return Math.pow(value, exponent);
	}
}
