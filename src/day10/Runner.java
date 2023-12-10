package day10;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Runner {
    private final String[] lines;

    public Runner(String[] lines) {
        this.lines = lines;
    }

    public long getLoopSize() {
        long sum = 0;
        for (var line : lines) {
            var filtered = Arrays.stream(line.split("")).filter(v -> !v.equals("."));
            sum += filtered.count();
        }
        return sum;
    }

    long getStepsToFarthestPoint() {
        return getLoopSize() / 2;
    }
}