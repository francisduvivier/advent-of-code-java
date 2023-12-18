package day17;

import util.VTile;
import util.astar.PathTile;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class PathPossibilitiesTile extends VTile<Integer> {
    private final PriorityQueue<PathTile> possibilities;

    public PathPossibilitiesTile(long row, long col, Integer value) {
        super(row, col, value);
        this.possibilities = new PriorityQueue<>();
    }

    private static boolean isStrictlyBetterThan(PathTile first, PathTile other) {
        if (other.dir != first.dir) {
            return false;
        }
        if (first.amountSameDir != other.amountSameDir) {
            return false;
        }
        return first.cost < other.cost;
    }

    boolean insertIfBetter(PathTile newPossibility, PriorityQueue prioQueue, int maxStraight, int minStraight, PathPossibilitiesTile destination) {
        if (newPossibility.amountSameDir >= maxStraight) {
            return false;
        }
        if (newPossibility.amountSameDir == 0 && newPossibility.prev.amountSameDir < minStraight - 1) {
            if (!newPossibility.isStartTile() && !newPossibility.prev.isStartTile()) {
                return false;
            }
        }
        if (newPossibility.key.equals(destination.key) && newPossibility.amountSameDir < minStraight - 1) {
            return false;
        }
        var sameOrBetterFound = false;
        ArrayList<PathTile> clone = new ArrayList<>(this.possibilities);
        for (var curr : clone) {
            if (curr.equals(newPossibility) || PathPossibilitiesTile.isStrictlyBetterThan(curr, newPossibility)) {
                sameOrBetterFound = true;
                break;
            }
            if (PathPossibilitiesTile.isStrictlyBetterThan(newPossibility, curr)) {
                this.possibilities.remove(curr);
                prioQueue.remove(curr);
            }
        }
        if (!sameOrBetterFound) {
            this.possibilities.add(newPossibility);
        }
        return !sameOrBetterFound;
    }

    public PathTile getBest() {
        return this.possibilities.peek();
    }
}