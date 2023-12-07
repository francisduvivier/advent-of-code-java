package day7;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static day7.Main.solve;
import static day7.Main.solve2;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {
    String sampleInput;
    String sampleInput2;
    String input;

    @BeforeEach
    void setUp() throws IOException {
        sampleInput = Files.readString(Path.of(getInputDir() + "/sample.txt"));
        sampleInput2 = Files.readString(Path.of(getInputDir() + "/sample2.txt"));
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
        assertEquals("6440", solve(sampleInput));
        System.out.println(solve(input));
    }

    @Test
    void solvePart2() {
        assertEquals("5905", solve2(sampleInput2));
        System.out.println(solve2(input));
    }
}