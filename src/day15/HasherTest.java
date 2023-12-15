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
        assertEquals(30, hasher.run("rn=1"));
        assertEquals(253, hasher.run("cm-"));
        assertEquals(97, hasher.run("qp=3"));
        assertEquals(47, hasher.run("cm=2"));
        assertEquals(14, hasher.run("qp-"));
        assertEquals(180, hasher.run("pc=4"));
        assertEquals(9, hasher.run("ot=9"));
        assertEquals(197, hasher.run("ab=5"));
        assertEquals(48, hasher.run("pc-"));
        assertEquals(214, hasher.run("pc=6"));
        assertEquals(231, hasher.run("ot=7"));
        assertEquals(1320, hasher.getTotal());
    }
}