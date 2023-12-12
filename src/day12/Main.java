package day12;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static day12.Runner.extend;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
    }

    static String solve(String sampleInput) {
        String[] lines = sampleInput.split("\n");
        long result = 0;
        for (String line : lines) {
            result += new Runner(line.split(" ")[0], Runner.createMatcher(line.split(" ")[1])).getOptions();
        }
        return "" + result;
    }

    static String solve2(String sampleInput) {
        String[] lines = sampleInput.split("\n");
        long result = 0;
        for (String line : lines) {
            String matcherNumbers = extend(line.split(" ")[1], ",");
            String toMatch = extend(line.split(" ")[0], "?");
            Runner.countMap.clear();
            result += new Runner(toMatch, Runner.createMatcher(matcherNumbers)).getOptions();
        }
        return "" + result;
    }
}