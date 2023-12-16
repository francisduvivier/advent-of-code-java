package day13;


import util.Grid;
import util.Tile;

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
            total += getPoints(grid, null);
        }
        return "" + total;
    }

    private static long getPoints(Grid grid, Tile moddedTile) {
        // LEFT SIDE
        for (var row = 1; row < grid.rows; row++) {
            var points = tryRow(grid, row, moddedTile);
            if (points != 0) {
                return 100L * points;
            }
        }

        // TOP SIDE
        for (var col = 1; col < grid.cols; col++) {
            var points = tryCol(grid, col, moddedTile);
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
        for (var row = 0; row < grid.rows; row++) {
            for (var col = 0; col < grid.cols; col++) {
                Grid moddedGrid = grid.clone();
                String currVal = grid.getTile(row, col).value;
                var otherVal = currVal.equals("#") ? "." : "#";
                Tile moddedTile = new Tile(row, col, otherVal);
                moddedGrid.setTile(moddedTile);
                long points = getPoints(moddedGrid, moddedTile);
                if (points != 0) {
                    return points;
                }
            }
        }
        throw new IllegalArgumentException("Could not find reflect line for this grid:\n" + grid);
    }

    private static int tryRow(Grid grid, int row, Tile moddedTile) {
        boolean usedMod = false;
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
                if (moddedTile == null) {
                    usedMod = true;
                } else if (moddedTile.key.equals(Tile.toKey(row, row + (offset - 1)))) {
                    usedMod = true;
                } else if (moddedTile.key.equals(Tile.toKey(row, row - (offset)))) {
                    usedMod = true;
                }
            }
        }
        return usedMod ? row : 0;
    }

    private static int tryCol(Grid grid, int col, Tile moddedTile) {
        boolean usedMod = false;

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
                if (moddedTile == null) {
                    usedMod = true;
                } else if (moddedTile.key.equals(Tile.toKey(row, col + (offset - 1)))) {
                    usedMod = true;
                } else if (moddedTile.key.equals(Tile.toKey(row, col - offset))) {
                    usedMod = true;
                }
            }
        }
        return usedMod ? col : 0;
    }

}