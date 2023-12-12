package day12;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RunnerTest {

    @Test
    void getOptions() {
        // Arrange
        // Act
        // Assert
        Runner.countMap.clear();
        assertEquals(1, new Runner(".?.", Runner.createMatcher("1")).getOptions(0));
        Runner.countMap.clear();
        assertEquals(10, new Runner("?###????????", Runner.createMatcher("3,2,1")).getOptions(0));
    }

    @Test
    void createMatcher() {
        // Arrange
        // Act
        // Assert
        assertEquals("^[.?]*[#?]{1}[.?]*$", Runner.createMatcher("1"));
        assertEquals(true, "..#..".matches("^[.?]*[#?]{1}[.?]*$"));
    }

    @Test
    void extend() {
        // Arrange
        // Act
        // Assert
        assertEquals("???.###????.###????.###????.###????.###", Runner.extend("???.###", "?"));
        assertEquals("1,1,3,1,1,3,1,1,3,1,1,3,1,1,3", Runner.extend("1,1,3", ","));
    }
}