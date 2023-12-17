package day17;

import util.VTile;
import util.astar.PathTile;

import java.util.ArrayList;

public class PathPossibilitiesTile extends VTile<Integer> {
    private final ArrayList<PathTile> possibilities;

    public PathPossibilitiesTile(int row, int col, Integer value) {
        super(row, col, value);
        this.possibilities = new ArrayList<>();
    }

    private static boolean isStrictlyBetterThan(PathTile first, PathTile other) {
        if (other.getDir() != first.getDir()) {
            return false;
        }
        if (first.amountSameDir > other.amountSameDir) {
            return false;
        }
        return first.cost < other.cost;
    }

    boolean insertIfBetter(PathTile newPossibility) {
        var sameOrBetterFound = false;
        ArrayList<PathTile> clone = new ArrayList<>(this.possibilities);
        for (var curr : clone) {
            if (PathPossibilitiesTile.isStrictlyBetterThan(curr, newPossibility)) {
                sameOrBetterFound = true;
                break;
            }
            if (PathPossibilitiesTile.isStrictlyBetterThan(newPossibility, curr)) {
                this.possibilities.remove(curr);
            }
        }
        if (!sameOrBetterFound) {
            this.possibilities.add(newPossibility);
        }
        return sameOrBetterFound;
    }
}