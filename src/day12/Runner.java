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

    long getOptions(int depth) {
        if (!toMatchLine.matches(matcher)) {
            return 0;
        }
        if (!toMatchLine.matches(".*[?].*")) {
            return 1;
        }
        int charBeforeQIndex = toMatchLine.indexOf('?') - 1;
        boolean hasDotBeforeFirstQ = charBeforeQIndex >= 0 && toMatchLine.charAt(charBeforeQIndex) == '.';
        String key = null;
        if (hasDotBeforeFirstQ) {
            int alreadyMatched = toMatchLine.substring(0, charBeforeQIndex + 1).split("#+").length;
            key = depth + "," + alreadyMatched;
            if (countMap.containsKey(key)) {
                return countMap.get(key);
            }
        }
        long result = new Runner(toMatchLine.replaceFirst("[?]", "#"), matcher).getOptions(depth + 1)
            +
            new Runner(toMatchLine.replaceFirst("[?]", "."), matcher).getOptions(depth + 1);
        if (hasDotBeforeFirstQ) {
            countMap.put(key, result);
        }
        return result;
    }
}