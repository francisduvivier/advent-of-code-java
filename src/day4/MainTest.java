package day4;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import static day4.Main.solve;
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
        assertEquals(solve(sampleInput), "35");
        System.out.println(solve(input));
    }
}