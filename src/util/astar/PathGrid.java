package util.astar;


import util.TGrid;
import util.TileFactory;

public class PathGrid extends TGrid<Integer, PathTile> {
    public PathGrid(String[] lines) {
        super(lines, new TileFactory<>() {
            @Override
            public PathTile create(int row, int col, String val) {
                return null;
            }
        });
    }
}