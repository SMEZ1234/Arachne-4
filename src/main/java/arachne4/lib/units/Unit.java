package arachne4.lib.units;

import java.util.function.DoubleUnaryOperator;

public abstract class Unit<UnitT extends Unit<UnitT>> {
    protected final double raw;

    public Unit(double raw) {
        this.raw = raw;
    }

    protected abstract UnitT fromRaw(double raw);

    public final double asRaw(DoubleUnaryOperator conversion) {
        return conversion.applyAsDouble(raw);
    }

    public final UnitT half() {
        return fromRaw(raw / 2);
    }

    public final UnitT twice() {
        return fromRaw(raw * 2);
    }

    public final UnitT negative() {
        return fromRaw(-raw);
    }

    public final UnitT plus(UnitT other) {
        return fromRaw(this.raw + other.raw);
    }

    public final UnitT minus(UnitT other) {
        return fromRaw(this.raw - other.raw);
    }

    public final UnitT multipliedBy(double scalar) {
        return fromRaw(raw * scalar);
    }

    public final UnitT dividedBy(double scalar) {
        return fromRaw(raw / scalar);
    }

    public final boolean isZero() {
        return raw == 0;
    }

    public final boolean isNegative() {
        return raw < 0;
    }

    public final boolean isLessThan(UnitT other) {
        return raw < other.raw;
    }

    public final boolean isGreaterThan(UnitT other) {
        return raw > other.raw;
    }
}
