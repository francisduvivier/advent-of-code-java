package day4;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        var sampleInput = "";
        var input = "";
        solve(sampleInput);
        solve(input);
    }

    static String solve(String sampleInput) {
        var lines = sampleInput.split("\n");
        System.out.println("Read lines: " + lines.length);
        return "0";
    }
}