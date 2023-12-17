package util.astar;


import util.TGrid;
import util.TileFactory;

public class PathGrid extends TGrid<Integer, PathTile> {
    public PathGrid(String[] lines) {
        super(lines, new TileFactory<PathTile>() {
            @Override
            public PathTile create(int row, int col, String val) {
                return new PathTile(row, col, Integer.parseInt(val), null);
            }
        });
    }
}