package util.astar;


import util.DIR;
import util.TGrid;
import util.TileFactory;

public class PathGrid extends TGrid<Integer, PathTile> {
    public PathGrid(String[] lines) {
        super(lines, new TileFactory<>() {
            @Override
            public PathTile create(int row, int col, String val) {
                return new PathTile(row, col, Integer.parseInt(val), null);
            }
        });
    }

    @Override
    public PathTile getNext(PathTile tile, DIR dir) {
        var origNext = super.getNext(tile, dir);
        return new PathTile(origNext.row, origNext.col, origNext.value, tile);
    }
}