package day12;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Runner {
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

    long getOptions() {
        if (!toMatchLine.matches(matcher)) {
            return 0;
        }
        if (!toMatchLine.matches(".*[?].*")) {
            return 1;
        }
        return
            new Runner(toMatchLine.replaceFirst("[?]", "#"), matcher).getOptions()
                +
                new Runner(toMatchLine.replaceFirst("[?]", "."), matcher).getOptions();
    }
    public static String extend(String origMatchNumbers, String delimiter) {
        Stream<String> stream = Arrays.stream(new String[5]);
        String matcherNumbers = stream.map((s) -> origMatchNumbers).collect(Collectors.joining(delimiter));
        return matcherNumbers;
    }
}