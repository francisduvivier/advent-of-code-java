package day17;


import util.TGrid;
import util.TileFactory;
import util.astar.PathTile;

public class PathPossibilitiesGrid extends TGrid<Integer, PathPossibilitiesTile> {
    public PathPossibilitiesGrid(String[] lines) {
        super(lines, new TileFactory<>() {
            @Override
            public PathPossibilitiesTile create(int row, int col, String val) {
                return new PathPossibilitiesTile(row, col, Integer.parseInt(val));
            }
        });
    }
}