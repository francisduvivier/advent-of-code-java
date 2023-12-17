package util;

public enum DIR {
    UP(-1, 0),
    DOWN(1, 0),
    RIGHT(0, 1),
    LEFT(0, -1);

    public final int rowDiff;
    public final int colDiff;

    DIR(int rowDiff, int colDiff) {
        this.rowDiff = rowDiff;
        this.colDiff = colDiff;
    }

    public static DIR calcDir(VTile from, VTile to) {
        int rowDiff = to.row - from.row;
        int colDiff = to.col - from.col;
        for (var dir : getAllDirs()) {
            if (rowDiff == dir.rowDiff && colDiff == dir.colDiff) {
                return dir;
            }
        }
        throw new IllegalArgumentException("Cannot find direction from tile: [" + from + "] to [" + to + "]");
    }

    public static DIR[] getAllDirs() {
        return new DIR[]{UP, DOWN, RIGHT, LEFT};
    }
}