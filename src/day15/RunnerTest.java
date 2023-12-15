package day15;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RunnerTest {

    @Test
    void getNbWinners() {
        // Arrange
        // Act
        // Assert
        Runner runner = new Runner();
        assertEquals(30, runner.run("rn=1"));
        assertEquals(253, runner.run("cm-"));
        assertEquals(97, runner.run("qp=3"));
        assertEquals(47, runner.run("cm=2"));
        assertEquals(14, runner.run("qp-"));
        assertEquals(180, runner.run("pc=4"));
        assertEquals(9, runner.run("ot=9"));
        assertEquals(197, runner.run("ab=5"));
        assertEquals(48, runner.run("pc-"));
        assertEquals(214, runner.run("pc=6"));
        assertEquals(231, runner.run("ot=7"));
        assertEquals(1320, runner.getTotal());
    }
}