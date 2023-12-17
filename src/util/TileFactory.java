package util;

public abstract class TileFactory<T> {
    public abstract T create(int row, int col, String val);
}