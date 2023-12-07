package day7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
    }

    static String solve(String sampleInput) {
        String[] lines = sampleInput.split("\n");
        long result = 0;
        List<HandWithBid> hands = Arrays.stream(lines).map((line) -> {
            return new HandWithBid(line);
        }).toList();
        var sortedHands = new ArrayList<HandWithBid>(hands);
        sortedHands.sort(Comparator.naturalOrder());
        int rank = 1;
        for (var hand : sortedHands) {
            result += rank * hand.bid;
            rank++;
        }
        return "" + result;
    }

    static String solve2(String sampleInput) {
        String[] lines = sampleInput.split("\n");
        long result = 0;
        // TODO
        return "" + result;
    }

}