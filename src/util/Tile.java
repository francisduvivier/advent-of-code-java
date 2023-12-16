package util;

import java.util.HashSet;
import java.util.Set;

public class Tile {
    public final String key;
    final int row;
    final int col;
    private final Set<DIR> markDirs = new HashSet<>();
    public String value;

    public Tile(int row, int col, String value) {
        this.row = row;
        this.col = col;
        this.value = value;
        this.key = toKey(row, col);
    }

    public static String toKey(int row, int col) {
        return row + "," + col;
    }

    public boolean addMark(DIR dir) {
        return this.markDirs.add(dir);
    }


    public int getMarks() {
        return markDirs.size();
    }

    @Override
    public String toString() {
        return getMarks() > 0 ? "#" : this.value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Tile)) {
            return false;
        }
        Tile otherTile = (Tile) obj;
        if (otherTile.value == null) {
            return value == null;
        }
        return otherTile.value.equals(value);
    }
}