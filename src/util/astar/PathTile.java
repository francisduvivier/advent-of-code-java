package util.astar;

import util.DIR;
import util.VTile;

public class PathTile extends VTile<Integer> {
    public final PathTile prev;
    public final long cost;

    public PathTile(int row, int col, Integer value, PathTile prev) {
        super(row, col, value);
        this.prev = prev;
        this.cost = prev.cost + prev.value;
    }

    DIR getDir() {
        return DIR.calcDir(prev, this);
    }
}