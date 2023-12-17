package day17;

import org.junit.jupiter.api.Test;
import util.astar.PathGrid;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RunnerTest {

    @Test
    void getNbWinners() {
        // Arrange
        // Act
        // Assert
        assertEquals(0, new ConstrainedPathRunner(new PathGrid(new String[]{""})).run(3));
    }
}