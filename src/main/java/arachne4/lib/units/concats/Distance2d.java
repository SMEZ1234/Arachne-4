package arachne4.lib.units.concats;

import java.util.function.DoubleFunction;
import java.util.function.DoubleUnaryOperator;

import arachne4.lib.units.Distance;
import arachne4.lib.units.Metres;
import edu.wpi.first.math.geometry.Translation2d;

public class Distance2d {
    private final Distance x, y;

    public Distance2d(Distance x, Distance y) {
        this.x = x;
        this.y = y;
    }

    public Distance2d(Translation2d raw, DoubleFunction<Distance> conversion) {
        this(conversion.apply(raw.getX()), conversion.apply(raw.getY()));
    }

    public Translation2d asRaw(DoubleUnaryOperator conversion) {
        return new Translation2d(
            conversion.applyAsDouble(x.asRaw(Metres::convert)),
            conversion.applyAsDouble(y.asRaw(Metres::convert))
        );
    }

    public Distance getX() {
        return x;
    }

    public Distance getY() {
        return y;
    }

    public Distance getMagnitude() {
        double xMetres = x.asRaw(Metres::convert);
        double yMetres = y.asRaw(Metres::convert);

        return new Metres(Math.sqrt(xMetres * xMetres + yMetres * yMetres));
    }

    @Override
    public String toString() {
        return "Distance2d("
            + x.asRaw(Metres::convert) + "m, "
            + y.asRaw(Metres::convert) + "m)";
    }
}
