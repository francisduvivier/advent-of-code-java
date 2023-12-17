package day17;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static day17.DayUtil.*;
import static day17.Main.solve;
import static day17.Main.solve2;
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
        assertEquals("102", solve(sampleInput));
        System.out.println("SAMPLE OK!");
        assertEquals("1076", solve(input));
    }

    @Test
    void solvePart2() {
        assertEquals("71", solve2(sampleInput2));
        System.out.println("SAMPLE 2 OK!");
        assertEquals("94", solve2(sampleInput));
        System.out.println("SAMPLE 1 OK!");
        assertEquals("PART 2 SOLUTION IS", solve2(input)); //Between 1224 and 1227
    }
}