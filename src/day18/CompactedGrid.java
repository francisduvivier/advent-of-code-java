package day18;

import day18.robot.Connector;
import day18.robot.ConnectorGrid;
import util.DIR;

import java.util.*;

import static util.DIR.UP;

public class CompactedGrid extends ConnectorGrid<Connector> {
    public CompactedGrid(Set<Connector<Integer>> nodeList) {
        buildCompactedGrid(nodeList);
    }

    static List<Connector<Connector>> findOppositeLoopTile(CompactedGrid grid, Connector<Connector> start, DIR dir) {
        Connector<Connector> tile = start;
        List<Connector<Connector>> connectorsInLine = new ArrayList<>();
        while (true) {
            Connector<Connector> loopTile = grid.getNext(tile, dir);
            if (loopTile != null) {
                connectorsInLine.add(loopTile);
                if (loopTile.next.getDir().isOpposite(start.getDir())) {
                    System.out.println("from [" + start.key + "]: " + start + ", FOUND opposite looptile [" + loopTile.key + "]: " + loopTile + ": opposite dir:" + loopTile.next.getDir().isOpposite(start.getDir()));
                    return connectorsInLine;
                } else {
                    System.out.println("from [" + start.key + "]: " + start + ", skipping looptile because of wrong dir [" + loopTile.key + "]: " + loopTile);
                }
            }
            Connector<Connector> newTile = createNext(tile, dir, 1);
            if (grid.isOutSide(newTile.row, newTile.col)) {
                return null;
            }
            tile = newTile;
        }
    }

    private int getInsideDirOffset(Connector<Connector> curr) {
        int tryOffset = 1;
        DIR insideDir = curr.getDir().getOtherDir(tryOffset);
        if (findOppositeLoopTile(this, curr, insideDir) == null) {
            return tryOffset + 2;
        }
        System.out.println("--- getInsideDirOffset done");
        return tryOffset;
    }

    private void buildCompactedGrid(Set<Connector<Integer>> nodeList) {
        System.out.println("Building compacted grid with [" + nodeList.size() + "] elements");
        var rowVals = new TreeSet<>(nodeList.stream().map(n -> n.row).toList()).stream().toList();
        var colVals = new TreeSet<>(nodeList.stream().map(n -> n.col).toList()).stream().toList();
        assert colVals.getFirst() < colVals.getLast();
        assert rowVals.getFirst() < rowVals.getLast();
        var startNode = nodeList.stream().toList().get(0);
        System.out.println("Checking expanded loop");
        assert nodeList.size() == createNodeList(startNode).size();
        System.out.println("Done Checking expanded loop");
        Connector<Connector> startCompacted = new Connector<>(rowVals.indexOf(startNode.row), colVals.indexOf(startNode.col), startNode, null);
        Connector<Connector> lastCompacted = startCompacted;
        setTile(startCompacted);

        var curr = startNode.next;
        while (curr != startNode) {
            var compactedNode = new Connector<>(rowVals.indexOf(curr.row), colVals.indexOf(curr.col), curr, lastCompacted);
            lastCompacted.next = compactedNode;
            this.setTile(compactedNode);
            lastCompacted = compactedNode;
            curr = curr.next;
        }
        startCompacted.prev = lastCompacted;
        assert (startCompacted.prev.col != startCompacted.col) != (startCompacted.prev.row != startCompacted.row);// Straight line
        lastCompacted.next = startCompacted;
        System.out.println("Checking compacted loop");
        createNodeList(startCompacted);
        System.out.println("Done Checking compacted loop");
    }

    public long findTilesInside() {
        Connector<Connector> start = tiles.values().stream().findAny().get();
        var curr = start;
        long surface = 0;
        int dirOffset = getInsideDirOffset(curr);
        Set<Connector<Connector>> countedLineStarts = new HashSet<>();
        while (curr.prev != start) {
            if (!curr.getDir().isHorizontal()) {
                DIR insideDir = curr.getDir().getOtherDir(dirOffset);
                System.out.println("----");
                List<Connector<Connector>> oppositeLoopTiles = findOppositeLoopTile(this, curr, insideDir);

                if (oppositeLoopTiles == null) {
                    throw new IllegalStateException("Looks like we are going the wrong direction.");
                }
                if (curr.getDir() == UP) {
                    surface += getInsideSurface(curr, oppositeLoopTiles);
                }

                surface += getAllLineTiles(curr, oppositeLoopTiles, countedLineStarts);
            }
            curr = curr.prev;
        }
        return surface;
    }

    private long countTilesInLineFrom(Connector<Connector> lineStart) {
        var inside = false;
        var tiles = 0L;
        Connector<Connector> currStart = null;
        Connector<Connector> lastVertical = null;
        assert !lineStart.getConnectedVerticalDir().isHorizontal();

        for (var tryCol = lineStart.col; tryCol < this.cols; tryCol++) {
            Connector<Connector> tile = getTile(lineStart.row, tryCol);
            if (tile == null || tile.getConnectedVerticalDir().isHorizontal()) {
                continue;
            }
            var shape = lastVertical == null ? tile.toString() : lastVertical.toString() + tile.toString();
            lastVertical = tile;
            var shouldDoCount = false;
            switch (shape) {
                case "^":
                case "v":
                case "FJ":
                case "L7": {
                    inside = !inside;
                    shouldDoCount = !inside;
                    System.out.println("LINE " + tile.row + ": found " + shape + " shape");
                    lastVertical = null;
                    break;
                }
                case "LJ":
                case "F7": {
                    shouldDoCount = !inside;
                    System.out.println("LINE " + tile.row + ": found " + shape + " shape");
                    lastVertical = null;
                    break;
                }
            }
            if (shouldDoCount) {
                long lineTiles = Math.abs(currStart.value.col - tile.value.col) + 1;
                System.out.println("LINE " + tile.row + ": shouldDoCount " + currStart.key + " to " + tile.key + ": " + lineTiles);
                tiles += lineTiles;
                currStart = null;
                lastVertical = null;
            } else if (currStart == null) {
                currStart = tile;
            }
        }
        return tiles;
    }

    private long getAllLineTiles(Connector<Connector> curr, List<Connector<Connector>> oppositeLoopTiles, Set<Connector<Connector>> countedLineStarts) {
        var lineStart = getLineStart(curr);
        boolean alreadyAdded = !countedLineStarts.add(lineStart);
        if (alreadyAdded) {
            return 0;
        }
        return countTilesInLineFrom(lineStart);
    }

    private Connector<Connector> getLineStart(Connector<Connector> curr) {
        var row = curr.row;
        for (var tryCol = this.minCol; tryCol < this.cols; tryCol++) {
            Connector<Connector> tile = getTile(row, tryCol);
            if (tile != null) {
                return tile;
            }
        }
        throw new IllegalArgumentException("Could not find tile in given row.");
    }

    public boolean isOutSide(Connector<Connector> next) {
        return isOutSide(next.row, next.col);
    }

    private boolean isFurtherFromOtherThen(Connector<Connector> next, Connector<Connector> oppositeLoopTile, Connector<Connector> curr) {
        return Math.abs(next.col - curr.col) > Math.abs(oppositeLoopTile.col - curr.col);
    }

    private long getInsideSurface(Connector<Connector> curr, List<Connector<Connector>> oppositeLoopTiles) {
        var oppositeLoopTile = oppositeLoopTiles.getLast();
        long verticalLength = Math.abs(curr.value.row - curr.prev.value.row) - 1;
        long horizontalLength = Math.abs(oppositeLoopTile.value.col - curr.value.col) + 1;
        long newSurface = verticalLength * horizontalLength;
        System.out.println("surface = " + verticalLength + " * " + horizontalLength + " = " + newSurface);
        return newSurface;
    }
}