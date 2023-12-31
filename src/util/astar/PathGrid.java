package util.astar;


import util.DIR;
import util.TGrid;
import util.TileFactory;

public class PathGrid extends TGrid<Integer, PathTile> {
    public PathGrid(String[] lines) {
        super(lines, new TileFactory<>() {
            @Override
            public PathTile create(long row, long col, String val) {
                return new PathTile(row, col, Integer.parseInt(val), null);
            }
        });
    }

    @Override
    public PathTile getNext(PathTile tile, DIR dir) {
        var origNext = super.getNext(tile, dir);
        if (origNext == null) {
            return null; // Out of bounds
        }
        return new PathTile(origNext.row, origNext.col, origNext.value, tile);
    }
}