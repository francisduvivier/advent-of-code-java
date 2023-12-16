package util;

public class Tile {
    final int row;
    final int col;
    final String key;
    public String value;
    private int marks;

    public Tile(int row, int col, String value) {
        this.row = row;
        this.col = col;
        this.value = value;
        this.key = toKey(row, col);
    }

    public static String toKey(int row, int col) {
        return row + "," + col;
    }

    public void mark() {
        this.marks++;
    }


    public int getMarks() {
        return marks;
    }
}