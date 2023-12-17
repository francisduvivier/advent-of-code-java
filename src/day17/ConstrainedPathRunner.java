package day17;

import util.astar.PathGrid;
import util.astar.PathTile;

import java.util.PriorityQueue;

public class ConstrainedPathRunner {
    final PathGrid grid;

    public ConstrainedPathRunner(PathGrid grid) {
        this.grid = grid;
    }

    PathTile run() {
        // So we will generate options at ever turn, then these options will be added to the PrioQueue.
        // But so then we need to be able to check whether an option is "possibly better"
        // Or rather we need to be able to exclude options.
        // So important here is that options have the property DIR and amountSameDir.
        //  - DIR is the direction compared to the previous tile.
        // - amountSameDir is the amount of previous tiles that have that same dir.
        // amountSameDir is allowed to range from 0 to 2, at 2 the next tile is obligated to be a new dir.
        // Now the point is that in the grid, we cannot simply just hold 1 best value. Instead we have a best value per DIR+amountSameDir.
        // Then the rule becomes if there is any options for this location that has
        //  - the same dir AND the same or less amountSameDir AND the same or better cost, then we don't go there.
        // So algo:
        // generate options and add them to the queue
        // also add each of these options to the PathPossibilitiesGrid
        // then take the smallest val from th queue and repeat
        var prioQueue = new PriorityQueue<PathTile>();
        return null; // TODO
    }
}