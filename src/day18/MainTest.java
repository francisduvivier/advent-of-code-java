package day18;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static day18.DayUtil.*;
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
        assertEquals("" + (4*6), solve(squareSampleInput));
        System.out.println("Square Sample ok");
        assertEquals("62", solve(sampleInput));
        System.out.println("Sample ok");
        assertEquals("48503", solve(input));
    }

    @Test
    void solvePart2() {
        assertEquals("952408144115", solve2(sampleInput));
        System.out.println("Part 2 Sample ok");
        assertEquals("148442153147147", solve2(input));
    }
}