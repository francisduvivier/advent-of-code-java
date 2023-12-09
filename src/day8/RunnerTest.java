package day8;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RunnerTest {

    private String[] sampleInput;
    private String[] sampleInput2;
    private String[] loopSample;

    {
        try {
            sampleInput = Files.readString(Path.of(getInputDir() + "/sample.txt")).split("\n");
            sampleInput2 = Files.readString(Path.of(getInputDir() + "/sample2.txt")).split("\n");
            loopSample = Files.readString(Path.of(getInputDir() + "/loop-sample.txt")).split("\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getInputDir() {
        return "src/" + getClass().getPackageName();
    }

    @Test
    void parse() {
        Runner fistSampleParsed = Runner.parse(sampleInput);
        assertEquals("RL", fistSampleParsed.instructions);
        assertEquals("AAA", fistSampleParsed.tree.id);
        assertEquals("BBB", fistSampleParsed.tree.leftId);
        assertEquals("CCC", fistSampleParsed.tree.rightId);
    }

    @Test
    void stepsTo() {
        Runner fistSampleParsed = Runner.parse(sampleInput);
        Runner secondSampleParsed = Runner.parse(sampleInput2);
        Runner loopSampleParsed = Runner.parse(loopSample);

        assertEquals(2, fistSampleParsed.stepsTo("ZZZ"));
        assertEquals(6, secondSampleParsed.stepsTo("ZZZ"));
        assertThrows(IllegalArgumentException.class, () -> loopSampleParsed.stepsTo("ZZZ"));
    }
}