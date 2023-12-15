package day15;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HasherTest {

    @Test
    void getNbWinners() {
        // Arrange
        // Act
        // Assert
        Hasher hasher = new Hasher();
        assertEquals(30, hasher.hash("rn=1"));
        assertEquals(253, hasher.hash("cm-"));
        assertEquals(97, hasher.hash("qp=3"));
        assertEquals(47, hasher.hash("cm=2"));
        assertEquals(14, hasher.hash("qp-"));
        assertEquals(180, hasher.hash("pc=4"));
        assertEquals(9, hasher.hash("ot=9"));
        assertEquals(197, hasher.hash("ab=5"));
        assertEquals(48, hasher.hash("pc-"));
        assertEquals(214, hasher.hash("pc=6"));
        assertEquals(231, hasher.hash("ot=7"));
        assertEquals(1320, hasher.getTotal());
    }
}