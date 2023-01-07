package arachne4.lib.units.concats;

import java.util.function.DoubleFunction;
import java.util.function.DoubleUnaryOperator;

import arachne4.lib.units.Distance;
import arachne4.lib.units.Metres;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;

public class Position2d {
    private final Distance2d location;
    private final Rotation2d heading;

    public Position2d(Distance2d location, Rotation2d heading) {
        this.location = location;
        this.heading = heading;
    }

    public Position2d(Distance x, Distance y, Rotation2d heading) {
        this(new Distance2d(x, y), heading);
    }

    public Position2d(Pose2d raw, DoubleFunction<Distance> conversion) {
        this(new Distance2d(raw.getTranslation(), conversion), raw.getRotation());
    }

    public Pose2d asRaw(DoubleUnaryOperator conversion) {
        return new Pose2d(location.asRaw(Metres::convert), heading);
    }

    public Distance2d getLocation() {
        return location;
    }

    public Rotation2d getHeading() {
        return heading;
    }

    @Override
    public String toString() {
        return "Position2d(location: ("
            + location.getX().asRaw(Metres::convert) + "m, "
            + location.getY().asRaw(Metres::convert) + "m), heading: "
            + heading.getDegrees() + " degrees)";
    }
}
