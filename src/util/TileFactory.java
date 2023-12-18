package util;

public abstract class TileFactory<T> {
    public abstract T create(long row, long col, String val);
}