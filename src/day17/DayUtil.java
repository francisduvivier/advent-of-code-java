package day17;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DayUtil {

    public static String sampleInput;
    public static String sampleInput2;
    public static String input;

    static {
        try {
            sampleInput = Files.readString(Path.of(getInputDir() + "/sample.txt"));
            sampleInput2 = Files.readString(Path.of(getInputDir() + "/sample2.txt"));
            input = Files.readString(Path.of(getInputDir() + "/input.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getInputDir() {
        return "src/" + DayUtil.class.getPackageName();
    }
}