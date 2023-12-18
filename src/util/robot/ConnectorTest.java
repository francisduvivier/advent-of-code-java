package util.robot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConnectorTest {

    @Test
    void testEquals() {
        Connector<Object> someConnector = new Connector<>(1, 1, null, null);
        Connector<Object> otherConnector = new Connector<>(1, 1, null, null);
        assertEquals(someConnector, otherConnector);
        assertEquals(someConnector, new Connector<>(1, 1, "test", otherConnector));

        assertNotEquals(someConnector, new Connector<>(2, 1, "test", otherConnector));
    }
}