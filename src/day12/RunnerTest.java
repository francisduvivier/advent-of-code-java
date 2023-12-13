package day12;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals("[.?]*[#?]{1}[.?]*", Runner.createMatcher("1"));
        assertEquals(true, "..#..".matches("[.?]*[#?]{1}[.?]*"));
    }

    @Test
    void extend() {
        // Arrange
        // Act
        // Assert
        assertEquals("???.###????.###????.###????.###????.###", Runner.extend("???.###", "?", 5));
        assertEquals("1,1,3,1,1,3,1,1,3,1,1,3,1,1,3", Runner.extend("1,1,3", ",", 5));
    }

    @Test
    void isPossible() {
        // Arrange
        // Act
        // Assert
        assertTrue(Runner.isPossible(
            "j.*s",
            "joooos"
        ));
        assertTrue(!Runner.isPossible(
            "klll.*s",
            "joooos"
        ));
        System.out.println("joooos ok");
        String matcher = Runner.createMatcher("1,1,1,1,3,1", 5);
        String extended = Runner.extend("??????#???.????.??", "?", 5);
        System.out.println("echo '" + extended + "' | egrep '" + matcher + "'");
        assertTrue(Runner.isPossible(
            matcher,
            extended
        ));
        assertFalse(Runner.isPossible(
            "j",
            "jos"
        ));
    }

}