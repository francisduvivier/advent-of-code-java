package util;

import java.util.Arrays;

public enum DIR {
    UP(-1, 0, "^"),
    DOWN(1, 0, "v"),
    RIGHT(0, 1, ">"),
    LEFT(0, -1, "<");

    public static final DIR[] DIRS = new DIR[]{RIGHT, DOWN, LEFT, UP};
    public final long rowDiff;
    public final long colDiff;
    private final String print;
    private int dirIndex;

    DIR(long rowDiff, long colDiff, String print) {
        this.rowDiff = rowDiff;
        this.colDiff = colDiff;
        this.print = print;
    }

    public static DIR calcDir(VTile from, VTile to) {
        long rowDiff = to.row - from.row;
        long colDiff = to.col - from.col;
        for (var dir : new DIR[]{UP, DOWN, RIGHT, LEFT}) {
            if (sign(rowDiff) == sign(dir.rowDiff) && sign(colDiff) == sign(dir.colDiff)) {
                return dir;
            }
        }
        throw new IllegalArgumentException("Cannot find direction from tile: [" + from.key + "] to [" + to.key + "]");
    }

    private static int sign(long val) {
        if (val > 0) {
            return 1;
        }
        if (val < 0) {
            return -1;
        }
        return 0;
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
        return getOtherDir(3);
    }

    public DIR getRightHandDir() {
        return getOtherDir(1);
    }

    public DIR getOtherDir(int offset) {
        return DIRS[(this.dirIndex() + offset) % DIRS.length];
    }

    private int dirIndex() {
        return Arrays.stream(DIRS).toList().indexOf(this);
    }

    public boolean isHorizontal() {
        return this.rowDiff == 0;
    }

    public boolean isOpposite(DIR dir) {
        return this.getOtherDir(2) == dir;
    }
}