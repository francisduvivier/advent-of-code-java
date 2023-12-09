package day9;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static day9.Main.solve;
import static day9.Main.solve2;
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
        assertEquals("114", solve(sampleInput));
        String solution = solve(input);
        assertEquals("1930746032", solution);
    }

    @Test
    void solvePart2() {
        assertEquals("2", solve2(sampleInput));
        System.out.println(solve2(input));
    }
}