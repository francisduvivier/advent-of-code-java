package day12;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static day12.Main.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {
    String sampleInput;
    String input;

    @BeforeEach
    void setUp() throws IOException {
        sampleInput = Files.readString(Path.of(getInputDir() + "/sample.txt"));
        input = Files.readString(Path.of(getInputDir() + "/input.txt"));
    }

    private String getInputDir() {
        return "src/" + getClass().getPackageName();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void solvePart1() {
        assertEquals("21", solve(sampleInput));
        assertEquals("7221", solve(input));
    }

    @Test
    void solvePart2Sample() {
        assertEquals("525152", solve2(sampleInput));
    }

    @Test
    void solvePart2Line66() {
        assertEquals("LINE 66 FOUND", solveLine(input.split("\n")[66], 66));
    }

    @Test
    void solvePart2Retry0() {
        assertEquals("525152", solve2(sampleInput));
        assertEquals("", solve2(input));
    }

    @Test
    void solvePart2() {
        assertEquals("525152", solve2(sampleInput));
        assertEquals("", solve2(input));
    }
}