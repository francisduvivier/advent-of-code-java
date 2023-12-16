package day16;

import util.DIR;
import util.Grid;
import util.Tile;

import static util.DIR.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    private static final boolean DEBUG = true;

    public static void main(String[] args) {
    }

    static String solve(String sampleInput) {
        String[] lines = sampleInput.split("\n");
        int startRow = 0;
        int startCol = -1;
        DIR dir = RIGHT;

        var energized = getEnergized(lines, startRow, startCol, dir);
        return "" + energized;
    }

    private static long getEnergized(String[] lines, int startRow, int startCol, DIR dir) {
        Grid grid = new Grid(lines);
        markTilesRec(grid, new Tile(startRow, startCol, ""), dir);
        var energized = grid.tiles.values().stream().filter(t -> t.getMarks() > 0).count();
        System.out.println(grid);
        return energized;
    }

    private static void markTilesRec(Grid grid, Tile tile, DIR dir) {
        var nextTile = grid.getNext(tile, dir);
        if (nextTile == null) {
            if (DEBUG) {
                System.out.println("HIT WALL Tile [" + tile.key + "]:" + tile.value + ", with dir [" + dir + "]");
            }
            return;
        }
        if (DEBUG) {
            System.out.println("mark Tile [" + nextTile.key + "]:" + nextTile.value + ", with dir [" + dir + "]");
        }
        if (!nextTile.addMark(dir)) {
            if (DEBUG) {
                System.out.println("STOP LOOP Tile [" + nextTile.key + "]:" + nextTile.value + ", with dir [" + dir + "]");
            }
            // tile was already marked with this dir, returning to avoid loop
            return;
        }
        switch (nextTile.value) {
            case ".": {
                markTilesRec(grid, nextTile, dir);
                break;
            }
            case "-": {
                if (dir == RIGHT || dir == LEFT) {
                    markTilesRec(grid, nextTile, dir);
                } else {
                    markTilesRec(grid, nextTile, RIGHT);
                    markTilesRec(grid, nextTile, LEFT);
                }
                break;
            }
            case "|": {
                if (dir == UP || dir == DOWN) {
                    markTilesRec(grid, nextTile, dir);
                } else {
                    markTilesRec(grid, nextTile, UP);
                    markTilesRec(grid, nextTile, DOWN);
                }
                break;
            }
            case "/": {
                switch (dir) {
                    case UP -> markTilesRec(grid, nextTile, RIGHT);
                    case DOWN -> markTilesRec(grid, nextTile, LEFT);
                    case RIGHT -> markTilesRec(grid, nextTile, UP);
                    case LEFT -> markTilesRec(grid, nextTile, DOWN);
                }
                break;
            }
            case "\\": {
                switch (dir) {
                    case UP -> markTilesRec(grid, nextTile, LEFT);
                    case DOWN -> markTilesRec(grid, nextTile, RIGHT);
                    case RIGHT -> markTilesRec(grid, nextTile, DOWN);
                    case LEFT -> markTilesRec(grid, nextTile, UP);
                }
                break;
            }
            default:
                throw new IllegalStateException("Invalid tile value " + nextTile.value);
        }
    }


    static String solve2(String sampleInput) {
        String[] lines = sampleInput.split("\n");
        long max = 0;

        // LEFT SIDE
        for (var row = 0; row < lines.length; row++) {
            var energized = getEnergized(lines, row, -1, RIGHT);
            if (max < energized) {
                max = energized;
            }
        }
        // RIGHT SIDE
        for (var row = 0; row < lines.length; row++) {
            var energized = getEnergized(lines, row, lines[0].length(), LEFT);
            if (max < energized) {
                max = energized;
            }
        }

        // TOP SIDE
        for (var col = 0; col < lines[0].length(); col++) {
            var energized = getEnergized(lines, -1, col, DOWN);
            if (max < energized) {
                max = energized;
            }
        }
        // BOT SIDE
        for (var col = 0; col < lines[0].length(); col++) {
            var energized = getEnergized(lines, lines.length, col, UP);
            if (max < energized) {
                max = energized;
            }
        }
        return "" + max;
    }

}