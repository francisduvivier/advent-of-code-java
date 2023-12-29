package day20;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static day20.DayUtil.input;
import static day20.DayUtil.sampleInput;
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
        assertEquals("TODO", Main.solvePart1(sampleInput));
        assertEquals("PART 1 SOLUTION IS", Main.solvePart1(input));
    }

    @Test
    void solvePart2() {
        assertEquals("TODO", Main.solvePart2(sampleInput));
        assertEquals("PART 2 SOLUTION IS", Main.solvePart2(input));
    }
}