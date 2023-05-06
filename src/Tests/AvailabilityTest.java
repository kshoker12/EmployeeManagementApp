package Tests;

import Model.Availability;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AvailabilityTest {
    Availability availability;

    @BeforeEach
    void init() {
        availability = new Availability(0, 8, 7);
    }

    @Test
    void testConstructor() {
        assertEquals(availability.getDay(), 0);
        assertEquals(availability.getHours(), 8);
        assertEquals(availability.getStart(), 7);
    }
}
