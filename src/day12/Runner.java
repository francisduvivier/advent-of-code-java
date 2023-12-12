package day12;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Runner {
    public static Map<String, Long> countMap = new HashMap<>();
    private final String toMatchLine;
    private final String matcher;

    public Runner(String toMatchLine, String matcher) {
        this.toMatchLine = toMatchLine;
        this.matcher = matcher;
    }

    static String createMatcher(String matcherLine) {
        Stream<String> amounts = Arrays.stream(matcherLine.split(","));
        List<String> matchers = amounts.map(amount -> "[#?]{" + amount + "}").toList();
        String matcher = "^[.?]*" + String.join("[.?]+", matchers) + "[.?]*$";
        return matcher;
    }

    public static String extend(String origMatchNumbers, String delimiter) {
        Stream<String> stream = Arrays.stream(new String[5]);
        String matcherNumbers = stream.map((s) -> origMatchNumbers).collect(Collectors.joining(delimiter));
        return matcherNumbers;
    }

    long getOptions() {
        if (!toMatchLine.matches(matcher)) {
            return 0;
        }
        if (!toMatchLine.matches(".*[?].*")) {
            return 1;
        }
        String[] parts = toMatchLine.split("[?].*");
        boolean hasDotBeforeFirstQ = parts.length > 0 && parts[0].endsWith(".");
        String key = null;
        if (hasDotBeforeFirstQ) {
            String first = parts[0];
            key = first.length() + "," + first.split("#+").length;
            if (countMap.containsKey(key)) {
                return countMap.get(key);
            }
        }
        long result = new Runner(toMatchLine.replaceFirst("[?]", "#"), matcher).getOptions()
            +
            new Runner(toMatchLine.replaceFirst("[?]", "."), matcher).getOptions();
        if (hasDotBeforeFirstQ) {
            countMap.put(key, result);
        }
        return result;
    }
}