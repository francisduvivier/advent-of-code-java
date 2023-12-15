package day15;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static day15.DayUtil.input;
import static day15.DayUtil.sampleInput;
import static day15.Main.solve;
import static day15.Main.solve2;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {

    @BeforeEach
    void setUp() throws IOException {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void solvePart1() {
        assertEquals("1320", solve(sampleInput));
        assertEquals("520500", solve(input));
    }

    @Test
    void solvePart2() {
        assertEquals("145", solve2(sampleInput));
        assertEquals("PART 2 SOLUTION IS", solve2(input));
    }
}