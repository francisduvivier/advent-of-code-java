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
        List<String> sortedChars = Arrays.stream(characters.split("")).sorted().toList();
        var stringBuilder = new StringBuilder();
        for (var sortedChar : sortedChars) {
            stringBuilder.append(sortedChar);
        }
        String sortedString = stringBuilder.toString();
        return sortedString.matches(this.matcher);
    }
}