package util;

import java.util.HashSet;
import java.util.Set;

public class VTile<V> {
    public final String key;
    public final int row;
    public final int col;
    private final Set<DIR> markDirs = new HashSet<>();
    public V value;

    public VTile(int row, int col, V value) {
        this.row = row;
        this.col = col;
        this.value = value;
        this.key = toKey(row, col);
    }

    public static String toKey(long row, long col) {
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
        return getMarks() > 0 ? "#" : this.value.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof VTile)) {
            return false;
        }
        VTile otherTile = (VTile) obj;
        if (otherTile.value == null) {
            return value == null;
        }
        return otherTile.value.equals(value);
    }
}