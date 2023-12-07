package day7;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public enum Rank {
    NO_RANK(""),
    HIGH_CARD(".*"),
    ONE_PAIR(".*(.)\\1.*"),
    TWO_PAIR(".*(.)\\1.*(.)\\2.*"),
    THREE_OF_A_KIND(".*(.)(.*\\1.*){2}.*"),
    FULL_HOUSE("(.)\\1{1,2}(.)\\2{1,2}"),
    FOUR_OF_A_KIND(".*(.)(.*\\1.*){3}.*"),
    FIVE_OF_A_KIND("(.)\\1*");

    private final String matcher;

    Rank(String matcher) {

        this.matcher = matcher;
    }

    boolean matches(Hand hand) {
        String characters = hand.getCharacters();
        for (var replacement : Hand.order) {
            var replaceChars = characters.replaceAll("J", String.valueOf(replacement));
            List<String> sortedChars = Arrays.stream(replaceChars.split("")).sorted().toList();
            var stringBuilder = new StringBuilder();
            for (var sortedChar : sortedChars) {
                if (sortedChar == "J") {
                    stringBuilder.append(replacement);
                }

                stringBuilder.append(sortedChar);
            }
            String sortedString = stringBuilder.toString();
            boolean matches = sortedString.matches(this.matcher);
            if (matches) {
                return true;
            }
        }
        return false;
    }
}