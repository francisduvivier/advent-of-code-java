package util;

public class Grid extends TGrid<String, Tile> {
    public Grid(String[] lines) {
        super(lines, new TileFactory<>() {
            @Override
            public Tile create(long row, long col, String val) {
                return new Tile(row, col, val);
            }
        });
    }

    @Override
    public Grid clone() {
        return new Grid(lines);
    }
}