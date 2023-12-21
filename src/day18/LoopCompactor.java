package day18;

import day18.robot.Connector;
import util.DIR;

import java.util.*;
import java.util.function.Function;

public class LoopCompactor {

    public static Function<? super String, ?> createInstruction;
    private final INSTRUCT[] instructions;

    LoopCompactor(INSTRUCT[] instructions) {
        this.instructions = instructions;
    }

    long run() {

        Connector<Integer> start = executeInstructions();
        CompactedGrid compactedGrid = getCompactedGrid(start);

        System.out.println("--- GRID START---");
        System.out.println();
        System.out.println(compactedGrid);
        System.out.println();
        System.out.println("--- GRID DONE---");

        return compactedGrid.findTilesInside();
    }

    private CompactedGrid getCompactedGrid(Connector<Integer> start) {
        System.out.println("Checking original loop");
        Set<Connector<Integer>> nodeList = CompactedGrid.createNodeList(start);
        assert nodeList.size() == instructions.length;
        System.out.println("Done Checking original loop");

        // So what we want to do is: add the original connectors to the compacted grid with splitters in between them on all the other values of connector nodes
        // So we will first gather all the rows of connectors
        // Then for every vertical edge, we will split it for all of these x values and put multiplier edges in between
        var nodeRows = new TreeSet<>(nodeList.stream().map(n -> n.row).toList());
        var nodeCols = new TreeSet<>(nodeList.stream().map(n -> n.col).toList());
        assert nodeRows.first() < nodeRows.last();
        assert nodeCols.first() < nodeCols.last();
        for (var node : new ArrayList<>(nodeList)) {
            if (node.getDir().isHorizontal()) {
                addHorizontalMultiplierEdges(node, nodeCols);
            } else {
                addVerticalMultiplierEdges(node, nodeRows);
            }
        }
        System.out.println("Checking expanded loop from original start");
        Set<Connector<Integer>> bridgedNodeList = CompactedGrid.createNodeList(start);
        System.out.println("Done Checking expanded loop from original start");
        CompactedGrid compactedGrid = new CompactedGrid(bridgedNodeList);
        CompactedGrid.createNodeList(compactedGrid.tiles.values().stream().findFirst().get());
        return compactedGrid;
    }

    public Connector<Integer> executeInstructions() {
        var start = new Connector<>(0, 0, -1, null);
        var curr = start;
        for (INSTRUCT instruction : instructions) {
            Connector<Integer> next = CompactedGrid.createNext(curr, instruction.dir, instruction.amount);
            curr.next = next;
            curr = next;

        }
        assert curr.equals(start);
        Connector<Integer> nodeBeforeStart = curr.prev;
        start.prev = nodeBeforeStart;
        nodeBeforeStart.setNext(start);
        return start;
    }

    private void addVerticalMultiplierEdges(Connector<Integer> node, SortedSet<Long> nodeRows) {
        var min = Math.min(node.row, node.prev.row);
        var max = Math.max(node.row, node.prev.row);
        if (min == node.row) {
            nodeRows = nodeRows.reversed();
        }
        var dir = node.getDir();
        assert node.isCornerTile();
        for (var otherRow : nodeRows) {
            if (otherRow <= min || otherRow >= max) {
                continue;
            }
            var bridge = CompactedGrid.insertBefore(node, dir, otherRow);
            assert !bridge.isCornerTile();
            assert bridge.next == node;
            assert bridge.next.isCornerTile();
        }
    }

    private void addHorizontalMultiplierEdges(Connector<Integer> node, SortedSet<Long> nodeCols) {
        var min = Math.min(node.col, node.prev.col);
        var max = Math.max(node.col, node.prev.col);
        if (min == node.col) {
            nodeCols = nodeCols.reversed();
        }
        var dir = node.getDir();
        assert node.isCornerTile();
        for (var otherCol : nodeCols) {
            if (otherCol <= min || otherCol >= max) {
                continue;
            }
            var bridge = CompactedGrid.insertBefore(node, dir, otherCol);
            assert !bridge.isCornerTile();
            assert bridge.next == node;
            assert bridge.next.isCornerTile();
        }
    }

    public record INSTRUCT(DIR dir, int amount, String color) {
    }
}