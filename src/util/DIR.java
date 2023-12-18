package util;

public enum DIR {
    UP(-1, 0, "^"),
    DOWN(1, 0, "v"),
    RIGHT(0, 1, ">"),
    LEFT(0, -1, "<");

    public static final DIR[] DIRS = new DIR[]{RIGHT, DOWN, LEFT, UP};
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

    public static DIR valueOfLetter(String dirLetter) {
        switch (dirLetter) {
            case "L": {
                return DIR.LEFT;
            }
            case "U": {
                return DIR.UP;
            }
            case "D": {
                return DIR.DOWN;
            }
            case "R": {
                return DIR.RIGHT;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.print;
    }

    public DIR getLeftHandDir() {
        switch (this) {
            case UP -> {
                return LEFT;
            }
            case DOWN -> {
                return RIGHT;
            }
            case RIGHT -> {
                return UP;
            }
            case LEFT -> {
                return DOWN;
            }
        }
        return null;
    }

    public DIR getRightHandDir() {
        switch (this) {
            case UP -> {
                return RIGHT;
            }
            case DOWN -> {
                return LEFT;
            }
            case RIGHT -> {
                return DOWN;
            }
            case LEFT -> {
                return UP;
            }
        }
        return null;
    }
}