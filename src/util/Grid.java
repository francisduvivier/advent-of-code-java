package util;

import java.util.HashMap;
import java.util.Map;

import static util.Tile.toKey;

public class Grid {
    public final Map<String, Tile> tiles = new HashMap<>();
    public final int rows;
    public final int cols;

    public Grid(String[] lines) {
        var row = 0;
        for (var line : lines) {
            var col = 0;
            for (var el : line.split("")) {
                Tile tile = new Tile(row, col, el);
                tiles.put(tile.key, tile);
                col++;
            }
            row++;
        }
        this.rows = lines.length;
        this.cols = lines[0].length();
    }

    public Tile getNext(Tile tile, DIR dir) {
        switch (dir) {
            case UP -> {
                return getTile(tile.row - 1, tile.col);
            }
            case DOWN -> {
                return getTile(tile.row + 1, tile.col);
            }
            case RIGHT -> {
                return getTile(tile.row, tile.col + 1);
            }
            case LEFT -> {
                return getTile(tile.row, tile.col - 1);
            }
        }
        return null;
    }

    public void setTile(Tile tile) {
        this.tiles.put(tile.key, tile);
    }

    public Tile getTile(int row, int col) {
        return tiles.get(toKey(row, col));
    }

    public Tile getTile(Tile tile) {
        return tiles.get(tile.key);
    }

    public Tile getTile(String key) {
        return tiles.get(key);
    }

    public boolean isOutSide(int row, int col) {
        return col < 0
            || col >= this.cols
            || row < 0
            || row >= this.rows;
    }

    public String toString() {
        String[] rowStrings = new String[rows];
        for (var row = 0; row < rows; row++) {
            var rowEls = new String[cols];
            for (var col = 0; col < cols; col++) {
                rowEls[col] = getTile(row, col).toString();
            }
            rowStrings[row] = String.join("", rowEls);
        }
        return String.join("\n", rowStrings);
    }
}