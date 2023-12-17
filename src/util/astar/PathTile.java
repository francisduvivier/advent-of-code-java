package util.astar;

import util.VTile;

public class PathTile extends VTile<Integer> {
    public final PathTile prev;

    public PathTile(int row, int col, Integer value, PathTile prev) {
        super(row, col, value);
        this.prev = prev;
    }
}