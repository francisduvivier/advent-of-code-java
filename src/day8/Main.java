package day8;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
    }

    static String solve(String sampleInput) {
        String[] lines = sampleInput.split("\n");

        Runner runner = Runner.parse(lines);
        long result = runner.stepsTo("ZZZ");
        return "" + result;
    }

    static String solve2(String sampleInput) {
        String[] lines = sampleInput.split("\n");

        Runner runner = Runner.parse(lines);
        long result = runner.multiStepsTo("..A", "..Z");
        // find repeat number, with this modulo, all the z matches are, ok, so then you take 1 of the sequences and then keep multiplying till all of the others have a
        return "" + result;
    }

}