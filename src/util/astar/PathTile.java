package util.astar;

import util.DIR;
import util.VTile;

public class PathTile extends VTile<Integer> implements Comparable<PathTile> {
    public final PathTile prev;
    public final long cost;
    public final DIR dir;
    public final int amountSameDir;
    public final int gscore;

    public PathTile(int row, int col, Integer value, PathTile prev) {
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
        if (this.dir == null) {
            return 0;
        }
        var amount = 0;
        var ancestor = this.prev;
        while (ancestor != null && ancestor != this && ancestor.dir == dir) {
            ancestor = ancestor.prev;
            amount++;
        }
        return amount;
    }

    public DIR getDir() {
        return dir;
    }

    @Override
    public int compareTo(PathTile o) {
        int gScoreDiff = gscore - o.gscore;
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