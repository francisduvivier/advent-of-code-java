package day10;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RunnerTest {
    String sampleInput;
    String sampleInput2;
    String input;

    @BeforeEach
    void setUp() throws IOException {
        sampleInput = Files.readString(Path.of(getInputDir() + "/sample.txt"));
        sampleInput2 = Files.readString(Path.of(getInputDir() + "/sample2.txt"));
        input = Files.readString(Path.of(getInputDir() + "/input.txt"));
    }

    @Test
    void getLoopSize() {
        assertEquals(4, new Runner(new String[]{"S7", "LJ"}).getLoopSize());
        assertEquals(4, new Runner(new String[]{".S7.", ".LJ."}).getLoopSize());
        assertEquals(8, new Runner(sampleInput2.split("\n")).getLoopSize());
    }

    @Test
    void getStepsToFarthestPoint() {
        assertEquals(2, new Runner(new String[]{"S7", "LJ"}).getStepsToFarthestPoint());
        assertEquals(2, new Runner(new String[]{".S7.", ".LJ."}).getStepsToFarthestPoint());
        assertEquals(4, new Runner(sampleInput2.split("\n")).getStepsToFarthestPoint());
    }

    private String getInputDir() {
        return "src/" + getClass().getPackageName();
    }

}