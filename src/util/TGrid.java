package util;

import java.util.HashMap;
import java.util.Map;

import static util.Tile.toKey;

public class TGrid<V, T extends VTile<V>> {
    public final Map<String, T> tiles = new HashMap<>();
    public final int rows;
    public final int cols;
    protected final String[] lines;
    private final TileFactory<T> tileFactory;

    public TGrid(String[] lines, TileFactory<T> tileFactory) {
        this.lines = lines;
        this.tileFactory = tileFactory;
        var row = 0;
        for (var line : lines) {
            var col = 0;
            for (var el : line.split("")) {
                T tile = tileFactory.create(row, col, el);
                tiles.put(tile.key, tile);
                col++;
            }
            row++;
        }
        this.rows = lines.length;
        this.cols = lines[0].length();
    }

    @Override
    public TGrid<V, T> clone() {
        return new TGrid<>(lines, tileFactory);
    }

    public T getNext(T tile, DIR dir) {
        return getTile(tile.row + dir.rowDiff, tile.col + +dir.colDiff);
    }

    public void setTile(T tile) {
        this.tiles.put(tile.key, tile);
    }

    public T getTile(int row, int col) {
        return tiles.get(toKey(row, col));
    }

    public T getTile(T tile) {
        return tiles.get(tile.key);
    }

    public T getTile(String key) {
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