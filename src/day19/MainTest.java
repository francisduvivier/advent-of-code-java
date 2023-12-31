package day19;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static day19.DayUtil.input;
import static day19.DayUtil.sampleInput;
import static day19.Main.solve;
import static day19.Main.solve2;
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
        assertEquals("19114", solve(sampleInput));
        assertEquals("263678", solve(input));
    }

    @Test
    void solvePart2() {
        assertEquals("167409079868000", solve2(sampleInput));
        assertEquals("125455345557345", solve2(input));
    }
}