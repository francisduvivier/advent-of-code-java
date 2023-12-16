package day16;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static day16.DayUtil.input;
import static day16.DayUtil.sampleInput;
import static day16.Main.solve;
import static day16.Main.solve2;
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
        assertEquals("46", solve(sampleInput));
        assertEquals("6622", solve(input));
    }

    @Test
    void solvePart2() {
        assertEquals("51", solve2(sampleInput));
        assertEquals("PART 2 SOLUTION IS", solve2(input));
    }
}