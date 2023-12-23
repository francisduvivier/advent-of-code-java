package day18;

import day18.robot.Connector;
import day18.robot.ConnectorGrid;
import util.DIR;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class CompactedGrid extends ConnectorGrid<Connector> {
    public CompactedGrid(Set<Connector<Integer>> nodeList) {
        buildCompactedGrid(nodeList);
    }

    static <T> Set<Connector<T>> createNodeList(Connector<T> startNode) {
        Set<Connector<T>> nodeList = new HashSet<>();

        nodeList.add(startNode);
        var curr = startNode.next;
        while (curr != startNode) {
            System.out.println(curr.getDir() + "" + curr.next.getDir() + ":" + curr.key + ":" + curr.value);
            nodeList.add(curr);
            curr = curr.next;
        }
        return nodeList;
    }

    static Connector<Connector> findOppositeLoopTile(CompactedGrid grid, Connector<Connector> start, DIR dir) {
        Connector<Connector> tile = start;
        while (true) {
            Connector<Connector> loopTile = grid.getNext(tile, dir);
            if (loopTile != null) {
                if (loopTile.next.getDir().isOpposite(start.getDir())) {
                    System.out.println("from [" + start.key + "]: " + start + ", FOUND opposite looptile [" + loopTile.key + "]: " + loopTile + ": opposite dir:" + loopTile.next.getDir().isOpposite(start.getDir()));
                    return loopTile;
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
        var foundOutside = false;
        long surface = 0;
        var countedTiles = new HashSet<String>();
        while (curr.prev != start) {
            if (!curr.getDir().isHorizontal() && !countedTiles.contains(curr.key)) {
                DIR leftHandDir = DIR.calcDir(curr, curr.prev).getLeftHandDir();
                Connector<Connector> oppositeLoopTile = findOppositeLoopTile(this, curr, leftHandDir);
                if (oppositeLoopTile == null) {
                    foundOutside = true;
                    break;
                }
                long verticalLength = 1 + Math.abs(curr.value.row - curr.prev.value.row);
                long horizontalLength = 1 + Math.abs(oppositeLoopTile.value.col - curr.value.col);
                surface += verticalLength * horizontalLength;
                countedTiles.add(oppositeLoopTile.next.key);
            }
            curr = curr.prev;
        }
        curr = start;
        if (foundOutside) {
            while (curr.prev != start) {
                if (!curr.getDir().isHorizontal() && !countedTiles.contains(curr.key)) {
                    DIR rightHandDir = DIR.calcDir(curr, curr.prev).getRightHandDir();
                    Connector<Connector> oppositeLoopTile = findOppositeLoopTile(this, curr, rightHandDir);
                    if (oppositeLoopTile == null) {
                        throw new IllegalStateException("There should be an opposite tile");
                    }
                    long verticalLength = 1 + Math.abs(curr.value.row - curr.prev.value.row);
                    long horizontalLength = 1 + Math.abs(oppositeLoopTile.value.col - curr.value.col);
                    surface += verticalLength * horizontalLength;
                    countedTiles.add(oppositeLoopTile.next.key);
                }
                curr = curr.prev;
            }
        }
        return surface;
    }
}