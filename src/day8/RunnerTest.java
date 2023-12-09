package day8;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RunnerTest {

    private String[] sampleInput;
    private String[] sampleInput2;

    {
        try {
            sampleInput = Files.readString(Path.of(getInputDir() + "/sample.txt")).split("\n");
            sampleInput2 = Files.readString(Path.of(getInputDir() + "/sample2.txt")).split("\n");
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
        assertEquals("AAA", fistSampleParsed.startNode.id);
        assertEquals("BBB", fistSampleParsed.startNode.leftId);
        assertEquals("CCC", fistSampleParsed.startNode.rightId);
    }

    @Test
    void stepsTo() {
        Runner fistSampleParsed = Runner.parse(sampleInput);
        Runner secondSampleParsed = Runner.parse(sampleInput2);

        assertEquals(2, fistSampleParsed.stepsTo("ZZZ"));
        assertEquals(6, secondSampleParsed.stepsTo("ZZZ"));
    }
}