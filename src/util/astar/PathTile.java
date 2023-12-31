package util.astar;

import util.DIR;
import util.VTile;

import java.util.Set;

public class PathTile extends VTile<Integer> implements Comparable<PathTile> {
    public final PathTile prev;
    public final long cost;
    public final DIR dir;
    public final int amountSameDir;
    public final long gscore;

    public PathTile(long row, long col, Integer value, PathTile prev) {
        super(row, col, value);
        this.prev = prev;
        if (prev != null) {
            this.cost = this.value + prev.cost;
            dir = DIR.calcDir(prev, this);
        } else {
            this.cost = this.value;
            this.dir = null;
        }
        this.amountSameDir = calcAmountSameDir();
        this.gscore = -row - col;
    }

    private int calcAmountSameDir() {

        if (dir == null) {
            return 0;
        }
        var ancestor = this.prev;
        if (ancestor == null) {
            return -1;
        }
        if (ancestor.isStartTile()) {
            // From the start tile, the dir is not defined
            return 0;
        }
        if (ancestor.dir != null
            && ancestor != this
            && ancestor.dir == dir
        ) {
            return ancestor.amountSameDir + 1;
        }
        return 0;
    }

    public boolean isStartTile() {
        return this.prev != null && this.prev.prev == null;
    }

    public DIR getDir() {
        return dir;
    }

    @Override
    public int compareTo(PathTile o) {
        long gScoreDiff = gscore - o.gscore;
        long result = gScoreDiff + (cost - o.cost);
        if (result == 0) {
            return 0;
        }
        if (result < 0) {
            return -1;
        }
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof PathTile)) {
            return false;
        }
        PathTile otherTile = (PathTile) obj;
        return otherTile.dir == this.dir &&
            otherTile.row == row &&
            otherTile.col == col &&
            otherTile.amountSameDir == amountSameDir &&
            otherTile.cost == this.cost;
    }

    @Override
    public String toString() {
        return "PathTile{" +
            " key='" + key + '\'' +
            ", value=" + value +
            ", cost=" + cost +
            ", dir=" + dir +
            ", amountSameDir=" + amountSameDir +
            '}';
    }
}