package arachne4.lib.units;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestDistance {
    @Test
    void readableForm() {
        assertEquals("Distance(-3.14m)", new Metres(-3.14).toString());
    }
}
