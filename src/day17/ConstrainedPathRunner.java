package day17;

import util.DIR;
import util.Grid;
import util.Tile;
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
        PathPossibilitiesGrid pathCache = new PathPossibilitiesGrid(grid.lines);
        PathTile bestTile = new PathTile(0, -1, 0, null);
        PathPossibilitiesTile destination = pathCache.getTile(grid.rows - 1, grid.cols - 1);
        do {
            for (var dir : DIR.DIRS) {
                PathTile next = grid.getNext(bestTile, dir);
                if (next != null) {
                    String prevKey = bestTile.prev == null ? null : bestTile.prev.key;
                    if (!next.key.equals(prevKey) && pathCache.getTile(next.key).insertIfBetter(next)) {
                        prioQueue.add(next);
                    }
                }
            }
            bestTile = prioQueue.poll();
        } while (bestTile != null && !bestTile.key.equals(destination.key));


        PathTile best = destination.getBest();
        return best;
    }

    String getPathString(PathTile path) {
        var printGrid = new Grid(grid.lines);
        var prev = path;
        while (prev != null && prev != prev.prev && prev.dir != null) {
            printGrid.setTile(new Tile(prev.row, prev.col, prev.dir.toString()));
            prev = prev.prev;
        }
        return printGrid.toString();
    }
}