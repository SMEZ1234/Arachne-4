package arachne4.lib.units.concats;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import arachne4.lib.units.Metres;
import edu.wpi.first.math.geometry.Rotation2d;

public class TestPosition2d {
    @Test
    void readableForm() {
        assertEquals("Position2d(location: (-3.14, 2.71), heading: 30 degrees)", new Position2d(
            new Metres(-3.14),
            new Metres(2.71),
            Rotation2d.fromDegrees(30)
        ).toString());
    }
}
