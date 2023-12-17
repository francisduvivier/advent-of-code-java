package util;

public enum DIR {
    UP(-1, 0, "^"),
    DOWN(1, 0, "v"),
    RIGHT(0, 1, ">"),
    LEFT(0, -1, "<");

    public static final DIR[] DIRS = new DIR[]{UP, DOWN, RIGHT, LEFT};
    public final int rowDiff;
    public final int colDiff;
    private final String print;

    DIR(int rowDiff, int colDiff, String print) {
        this.rowDiff = rowDiff;
        this.colDiff = colDiff;
        this.print = print;
    }

    public static DIR calcDir(VTile from, VTile to) {
        int rowDiff = to.row - from.row;
        int colDiff = to.col - from.col;
        for (var dir : new DIR[]{UP, DOWN, RIGHT, LEFT}) {
            if (rowDiff == dir.rowDiff && colDiff == dir.colDiff) {
                return dir;
            }
        }
        throw new IllegalArgumentException("Cannot find direction from tile: [" + from + "] to [" + to + "]");
    }

    @Override
    public String toString() {
        return this.print;
    }
}