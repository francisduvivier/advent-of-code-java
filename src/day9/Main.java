package day9;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
    }

    static String solve(String sampleInput) {
        String[] lines = sampleInput.split("\n");

        long result = 0;
        for (var line : lines) {
            var seq = Sequence.parse(line);
            result += seq.extrapolateRightRec();
        }
        return "" + result;
    }

    static String solve2(String sampleInput) {
        String[] lines = sampleInput.split("\n");

        long result = 0;
        for (var line : lines) {
            var seq = Sequence.parse(line);
            result += seq.extrapolateLeftRec();
        }
        return "" + result;
    }

}