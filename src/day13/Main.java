package day13;


import util.Grid;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    private static final boolean DEBUG = true;

    public static void main(String[] args) {
    }

    static String solve(String sampleInput) {
        String[] patterns = sampleInput.split("\n\n");
        long total = 0;

        for (var pattern : patterns) {
            String[] lines = pattern.split("\n");
            Grid grid = new Grid(lines);
            // LEFT SIDE
            for (var row = 1; row < lines.length; row++) {
                var points = 100 * tryRow(grid, row);
                total += points;
            }

            // TOP SIDE
            for (var col = 1; col < lines[0].length(); col++) {
                var points = tryCol(grid, col);
                total += points;
            }
        }
        return "" + total;
    }

    static String solve2(String sampleInput) {
        // TODO
        return "";
    }

    private static int tryRow(Grid grid, int row) {
        for (var offset = 1; offset < grid.rows; offset++) {
            if (grid.isOutSide(row + (offset - 1), 0)) {
                break;
            }
            if (grid.isOutSide(row - offset, 0)) {
                break;
            }
            for (var col = 0; col < grid.cols; col++) {
                if (!grid.getTile(row + (offset - 1), col).equals(grid.getTile(row - offset, col))) {
                    return 0;
                }
            }
        }
        return row;
    }

    private static int tryCol(Grid grid, int col) {
        for (var offset = 1; offset < grid.cols; offset++) {
            if (grid.isOutSide(0, col + (offset - 1))) {
                break;
            }
            if (grid.isOutSide(0, col - offset)) {
                break;
            }
            for (var row = 0; row < grid.rows; row++) {
                if (!grid.getTile(row, col + (offset - 1)).equals(grid.getTile(row, col - offset))) {
                    return 0;
                }
            }
        }
        return col;
    }

}