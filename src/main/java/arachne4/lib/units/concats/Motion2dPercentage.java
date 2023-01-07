package arachne4.lib.units.concats;

public class Motion2dPercentage {
    public final double vX, vY, vTheta;

    public Motion2dPercentage(double vX, double vY, double vTheta) {
        this.vX = vX;
        this.vY = vY;
        this.vTheta = vTheta;
    }

    public double getVelocityPercentageX() {
        return vX;
    }

    public double getVelocityPercentageY() {
        return vY;
    }

    public double getRotationalVelocityPercentage() {
        return vTheta;
    }
}
