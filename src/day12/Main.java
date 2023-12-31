package day12;

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
            result += new Runner(line.split(" ")[0], Runner.createMatcher(line.split(" ")[1])).getOptions(0);
        }
        return "" + result;
    }

    static String solve2(String sampleInput) {
        String[] lines = sampleInput.split("\n");
        long result = 0;
        int i = 0;
        for (String line : lines) {
            result += solveLine(line, i);
            i++;
        }
        return "" + result;
    }

    public static long solveLine(String line, int i) {
        String matcherNumbers = line.split(" ")[1];
        String matcher = Runner.createMatcher(matcherNumbers, 5);
        String toMatch = extend(line.split(" ")[0], "?", 5);
        Runner.countMap.clear();
        System.out.println(" ***************** Doing line " + i + ": " + line + ", matcher:\n" + matcher);
        long result = new Runner(toMatch, matcher).getOptions(0);
        return result;
    }
}