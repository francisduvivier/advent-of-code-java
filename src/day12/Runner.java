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
        String matcher = "(^|[.?]+)" + String.join("[.?]+", matchers) + "([.?]+|$)";
        return matcher;
    }

    static String createMatcher(String matcherLine, int multiplier) {
        Stream<String> amounts = Arrays.stream(matcherLine.split(","));
        List<String> matchers = amounts.map(amount -> "[#?]{" + amount + "}").toList();
        String matcher = "[.?]*(" + String.join("[.?]+", matchers) + "([.?]+|$)){" + multiplier + "}";
        return matcher;
    }

    public static String extend(String origMatchNumbers, String delimiter, int multiplier) {
        Stream<String> stream = Arrays.stream(new String[multiplier]);
        String matcherNumbers = stream.map((s) -> origMatchNumbers).collect(Collectors.joining(delimiter));
        return matcherNumbers;
    }

    long getOptions(int depth) {
        System.out.println("depth " + depth+"");
        if (!isPossible()) {
            System.out.println("+ No Match at depth " + depth + ": " + toMatchLine);
            return 0;
        }
        if (!toMatchLine.matches(".*[?].*")) {
            System.out.println("+ Match: " + toMatchLine);
            return 1;
        }
        int charBeforeQIndex = toMatchLine.indexOf('?') - 1;
        boolean hasDotBeforeFirstQ = charBeforeQIndex >= 0 && toMatchLine.charAt(charBeforeQIndex) == '.';
        String key = null;
        if (hasDotBeforeFirstQ) {
            int alreadyMatched = toMatchLine.substring(0, charBeforeQIndex + 1).split("#+").length;
            key = depth + "," + alreadyMatched;
            if (countMap.containsKey(key)) {
                Long result = countMap.get(key);
                System.out.println("- Using Cached Result" + key + " , " + result + ": " + toMatchLine);
                return result;
            }
        }
        long result =
            new Runner(toMatchLine.replaceFirst("[?]", "."), matcher).getOptions(depth + 1)
                + new Runner(toMatchLine.replaceFirst("[?]", "#"), matcher).getOptions(depth + 1);
        if (hasDotBeforeFirstQ) {
            countMap.put(key, result);
            System.out.println("- Adding Count: " + key + " , " + result + ": " + toMatchLine);
        }
        return result;
    }

    private boolean isPossible() {
        return toMatchLine.matches(matcher);
    }
}