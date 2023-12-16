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
            total += getPoints(grid);
        }
        return "" + total;
    }

    private static long getPoints(Grid grid) {
        // LEFT SIDE
        for (var row = 1; row < grid.rows; row++) {
            var points = tryRow(grid, row);
            if (points != 0) {
                return 100L * points;
            }
        }

        // TOP SIDE
        for (var col = 1; col < grid.cols; col++) {
            var points = tryCol(grid, col);
            if (points != 0) {
                return points;
            }
        }
        return 0;
    }

    static String solve2(String sampleInput) {
        String[] patterns = sampleInput.split("\n\n");
        long total = 0;

        for (var pattern : patterns) {
            String[] lines = pattern.split("\n");
            Grid grid = new Grid(lines);
            total += getPointsModded(grid);
        }
        return "" + total;
    }

    private static long getPointsModded(Grid grid) {
        return 0; //TODO
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