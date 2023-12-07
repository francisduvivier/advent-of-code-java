package day0;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RunnerTest {

    @Test
    void getNbWinners() {
        // Arrange
        // Act
        var nbWinners = new Runner(7, 9).getNbWinners();
        // Assert
        assertEquals(4, new Runner(7, 9).getNbWinners());
        assertEquals(8, new Runner(15, 40).getNbWinners());
        assertEquals(9, new Runner(30, 200).getNbWinners());
    }
}