package day15;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
    }

    static String solve(String sampleInput) {
        String[] lines = sampleInput.split("\n");
        var runner = new Runner();
        for (var part : lines[0].split(",")) {
            runner.run(part);
        }
        return "" + runner.getTotal();
    }

    static String solve2(String sampleInput) {
        String[] lines = sampleInput.split("\n");
        // SO we need to have a list of 256 box elements
        // Then every box should have a list containing lenses
        // lens objects have a label and a number
        // we need to be able to look up lenses by their label so we can remove them from the list

        long result = 0;
        // TODO
        return "" + result;
    }

}