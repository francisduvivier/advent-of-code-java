package day12;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Runner {
    public static Map<String, Long> countMap = new HashMap<>();
    private static boolean DEBUG;
    private final String toMatchLine;
    private final String matcher;
    private int maxDepth;

    public Runner(String toMatchLine, String matcher) {
        this.toMatchLine = toMatchLine;
        this.maxDepth = toMatchLine.split("[?]").length - 1;
        this.matcher = matcher;
    }

    static String createMatcher(String matcherLine) {
        Stream<String> amounts = Arrays.stream(matcherLine.split(","));
        List<String> matchers = amounts.map(amount -> "[#?]{" + amount + "}").toList();
        String matcher = "[.?]*" + String.join("[.?]+", matchers) + "[.?]*";
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

    public static boolean isPossible1(String matcher1, String toMatchLine1) {
        return Pattern.matches(matcher1, toMatchLine1);
    }

    public static boolean isPossible(String matcher1, String toMatchLine1) {
        boolean result = false;
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "if [ $(echo '" + toMatchLine1 + "' | egrep '^" + matcher1 + "$') ]; then exit 1; fi"});
            process.waitFor();
            result = process.exitValue() == 1;
        } catch (IOException e) {
            result = false;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//        System.out.println(process);
        return result;
    }

    long getOptions(int depth) {
        if (DEBUG) System.out.println("depth " + depth + "");
        if (
//            depth > maxDepth - 10 &&
            !isPossible(matcher, toMatchLine)) {
            if (DEBUG) System.out.println("+ No Match at depth " + depth + ": " + toMatchLine);
            return 0;
        }
        if (!toMatchLine.matches(".*[?].*")) {
            if (DEBUG) System.out.println("+ Match: " + toMatchLine);
            return 1;
        }
        int charBeforeQIndex = toMatchLine.indexOf('?') - 1;
        boolean hasDotBeforeFirstQ = charBeforeQIndex >= 0 && toMatchLine.charAt(charBeforeQIndex) == '.';
        String key = null;
        if (hasDotBeforeFirstQ) {
            long alreadyMatched = Arrays.stream(toMatchLine.substring(0, charBeforeQIndex + 1).split("[.]+")).filter(el -> !el.isEmpty()).count();
            key = depth + "," + alreadyMatched;
            if (countMap.containsKey(key)) {
                Long result = countMap.get(key);
                if(DEBUG) System.out.println("- Using Cached Result: " + key + " , " + result + ": " + toMatchLine);
                return result;
            }
        }
        long result =
            new Runner(toMatchLine.replaceFirst("[?]", "."), matcher).getOptions(depth + 1)
                + new Runner(toMatchLine.replaceFirst("[?]", "#"), matcher).getOptions(depth + 1);
        if (hasDotBeforeFirstQ) {
            countMap.put(key, result);
            if(DEBUG) System.out.println("- Adding Count: " + key + " , " + result + ": " + toMatchLine);
        }
        return result;
    }
}