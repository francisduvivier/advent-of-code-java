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
        Grid grid = new Grid(lines);
        markTilesRec(grid, new Tile(0, -1, ""), RIGHT);
        var energized = grid.tiles.values().stream().filter(t -> t.getMarks() > 0).count();
        return "" + energized;
    }

    private static void markTilesRec(Grid grid, Tile tile, DIR dir) {
        if (tile.value.equals(".")) {
            tile.mark();
        }
        if (DEBUG) {
            System.out.println("mark Tile[" + tile.value + "], with dir [" + dir + "]");
        }
        var nextTile = grid.getNext(tile, dir);
        if (nextTile == null) {
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
                    markTilesRec(grid, nextTile, UP);
                    markTilesRec(grid, nextTile, DOWN);
                }
                break;
            }
            case "|": {
                if (dir == UP || dir == DOWN) {
                    markTilesRec(grid, nextTile, dir);
                } else {
                    markTilesRec(grid, nextTile, LEFT);
                    markTilesRec(grid, nextTile, RIGHT);
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
        long result = 0;
        // TODO
        return "" + result;
    }

}