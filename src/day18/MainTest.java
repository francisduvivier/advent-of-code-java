package day18;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static day18.DayUtil.input;
import static day18.DayUtil.sampleInput;
import static day18.Main.solve;
import static day18.Main.solve2;
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
        assertEquals("62", solve(sampleInput));
        System.out.println("Sample ok");
        assertEquals("PART 1 SOLUTION IS", solve(input));
    }

    @Test
    void solvePart2() {
        assertEquals("TODO", solve2(sampleInput));
        assertEquals("PART 2 SOLUTION IS", solve2(input));
    }
}