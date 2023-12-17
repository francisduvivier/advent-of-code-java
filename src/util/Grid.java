package util;

public class Grid extends TGrid<Tile> {
    public Grid(String[] lines) {
        super(lines, new TileFactory<>() {
            @Override
            public Tile create(int row, int col, String val) {
                return new Tile(row, col, val);
            }
        });
    }

    @Override
    public Grid clone() {
        return new Grid(lines);
    }
}