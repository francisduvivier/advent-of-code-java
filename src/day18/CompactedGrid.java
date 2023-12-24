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

                surface += getLineTiles(curr, oppositeLoopTiles, countedLineStarts);
            }
            curr = curr.prev;
        }
        return surface;
    }

    private long getLineTiles(Connector<Connector> curr, List<Connector<Connector>> oppositeLoopTiles, Set<Connector<Connector>> countedLineStarts) {
        var oppositeLoopTile = oppositeLoopTiles.getLast();
        long tiles = 0;
        if (countedLineStarts.contains(oppositeLoopTile)) {
            tiles--;
        }
        if (countedLineStarts.contains(curr)) {
            tiles--;
        }
        boolean currAlreadyCounted = !countedLineStarts.add(curr);
        boolean oppositeAlreadyCounted = !countedLineStarts.add(oppositeLoopTile);
        if (oppositeAlreadyCounted && currAlreadyCounted) {
            System.out.println("LINE: from [" + curr.key + "]: " + curr + ", skipping oppositeLoopTile because already counted [" + oppositeLoopTile.key + "]: " + oppositeLoopTile);
            return 0;
        }
        while (oppositeLoopTile.next.row == curr.row
            && isFurtherFromOtherThen(oppositeLoopTile.next, oppositeLoopTile, curr)
            && oppositeLoopTile.next.getDir() != UP
            && countedLineStarts.add(oppositeLoopTile.prev)
        ) {
            oppositeLoopTile = oppositeLoopTile.next;
        }

        while (oppositeLoopTile.prev.row == curr.row
            && isFurtherFromOtherThen(oppositeLoopTile.prev, oppositeLoopTile, curr)
            && oppositeLoopTile.prev.getDir() != UP
            && countedLineStarts.add(oppositeLoopTile.prev)
        ) {
            oppositeLoopTile = oppositeLoopTile.prev;
        }

        var outwardLineTile = curr;
        while (outwardLineTile.next.row == curr.row && isFurtherFromOtherThen(outwardLineTile.next, outwardLineTile, oppositeLoopTile) && countedLineStarts.add(outwardLineTile.next)) {
            outwardLineTile = outwardLineTile.next;
        }
        while (outwardLineTile.prev.row == curr.row && isFurtherFromOtherThen(outwardLineTile.prev, outwardLineTile, oppositeLoopTile) && countedLineStarts.add(outwardLineTile.prev)) {
            outwardLineTile = outwardLineTile.prev;
        }


        tiles += Math.abs(outwardLineTile.value.col - oppositeLoopTile.value.col) + 1;
        System.out.println("Line = " + outwardLineTile.key + " - " + curr.key + " - " + oppositeLoopTile.key + " = " + tiles);

        return tiles;
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