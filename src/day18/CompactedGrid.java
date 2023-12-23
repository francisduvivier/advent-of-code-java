package day18;

import day18.robot.Connector;
import day18.robot.ConnectorGrid;
import util.DIR;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static util.DIR.DOWN;
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
                if (loopTile.next.getDir() == DOWN) {
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
        DIR insideDir = DIR.calcDir(curr, curr.prev).getOtherDir(tryOffset);
        if (findOppositeLoopTile(this, curr, insideDir) == null) {
            return tryOffset + 2;
        }
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

        while (curr.prev != start) {
            if (curr.getDir() == UP) {
                DIR insideDir = DIR.calcDir(curr, curr.prev).getOtherDir(dirOffset);
                List<Connector<Connector>> oppositeLoopTiles = findOppositeLoopTile(this, curr, insideDir);

                if (oppositeLoopTiles == null) {
                    throw new IllegalStateException("Looks like we are going the wrong direction.");
                }
                surface += getInsideSurface(curr, oppositeLoopTiles);
                surface += getLineTiles(curr, oppositeLoopTiles);
            }
            curr = curr.prev;
        }
        return surface;
    }

    private long getLineTiles(Connector<Connector> curr, List<Connector<Connector>> oppositeLoopTiles) {
        return 0; //TODO
    }

    private long getInsideSurface(Connector<Connector> curr, List<Connector<Connector>> oppositeLoopTiles) {
        var oppositeLoopTile = oppositeLoopTiles.getLast();
        long verticalLength = Math.abs(curr.value.row - curr.prev.value.row) - 1;
        long horizontalLength = Math.abs(oppositeLoopTile.value.col - curr.value.col) - 1;
        long newSurface = verticalLength * horizontalLength;
        System.out.println("surface = " + verticalLength + " * " + horizontalLength + " = " + newSurface);
        return newSurface;
    }
}