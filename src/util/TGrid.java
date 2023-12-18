package util;

import java.util.HashMap;
import java.util.Map;

import static util.Tile.toKey;

public class TGrid<V, T extends VTile<V>> {
    public final Map<String, T> tiles = new HashMap<>();
    public final String[] lines;
    private final TileFactory<T> tileFactory;
    public long rows;
    public long cols;
    public long minRow;
    public long minCol;

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
        this.cols = lines.length > 0 ? lines[0].length() : 0;
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
        this.cols = Math.max(this.cols, tile.col + 1);
        this.rows = Math.max(this.rows, tile.row + 1);
        this.minRow = Math.min(this.minRow, tile.row);
        this.minCol = Math.min(this.minCol, tile.col);
//        if (tile.row < 0 || tile.col < 0) {
//            throw new IllegalArgumentException("NOK tile coordinates: " + tile.key);
//        }
    }

    public T getTile(long row, long col) {
        return tiles.get(toKey(row, col));
    }

    public T getTile(T tile) {
        return tiles.get(tile.key);
    }

    public T getTile(String key) {
        return tiles.get(key);
    }

    public boolean isOutSide(long row, long col) {
        return col < this.minCol
            || col >= this.cols
            || row < this.minRow
            || row >= this.rows;
    }

    public String toString() {
        if (
            this.minRow < -Integer.MAX_VALUE ||
                this.minCol < -Integer.MAX_VALUE ||
                this.rows > Integer.MAX_VALUE ||
                this.cols > Integer.MAX_VALUE
        ) {
            return "TOO BIG";
        }
        String[] rowStrings = new String[(int) (rows - this.minRow)];
        for (var row = this.minRow; row < rows; row++) {
            var rowEls = new String[(int) (cols - this.minCol)];
            for (var col = this.minCol; col < cols; col++) {
                T tile = getTile((int) row, (int) col);
                if (tile == null) {
                    rowEls[(int) (col - this.minCol)] = ".";
                } else {
                    rowEls[(int) (col - this.minCol)] = tile.toString();
                }
            }
            rowStrings[(int) (row - this.minRow)] = String.join("", rowEls);
        }
        return String.join("\n", rowStrings);
    }
}