package day20;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static day20.DayUtil.input;
import static day20.DayUtil.sampleInput;
import static day20.DayUtil.sampleInput2;
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
        assertEquals("32000000", Main.solvePart1(sampleInput));
        assertEquals("11687500", Main.solvePart1(sampleInput2));
        assertEquals("PART 1 SOLUTION IS", Main.solvePart1(input));
    }

    @Test
    void solvePart2() {
        assertEquals("TODO", Main.solvePart2(sampleInput));
        assertEquals("PART 2 SOLUTION IS", Main.solvePart2(input));
    }
}