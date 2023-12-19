package day18;

import day18.robot.Connector;
import util.DIR;

import java.util.ArrayList;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Function;

public class Runner {

    public static Function<? super String, ?> createInstruction;
    private final INSTRUCT[] instructions;

    Runner(INSTRUCT[] instructions) {
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
        return compactedGrid;
    }

    public Connector<Integer> executeInstructions() {
        var start = new Connector<>(0, 0, 1, null);
        var curr = start;
        for (INSTRUCT instruction : instructions) {
            curr = CompactedGrid.createNext(curr, instruction.dir, instruction.amount);
        }
        assert curr.equals(start);
        start.prev = curr.prev;
        curr.prev.setNext(start);
        return start;
    }

    private void addVerticalMultiplierEdges(Connector<Integer> node, SortedSet<Long> nodeRows) {
        var min = Math.min(node.row, node.next.row);
        var max = Math.max(node.row, node.next.row);
        if (min == node.row) {
            nodeRows = nodeRows.reversed();
        }
        var currConnector = node;
        DIR dir = node.getDir();
        var origNext = node.next;
        for (var otherRow : nodeRows) {
            if (otherRow <= min || otherRow >= max) {
                continue;
            }
            var between = Math.abs(node.row - otherRow);
            currConnector = insertBridges(currConnector, origNext, dir, between);
        }
    }

    private void addHorizontalMultiplierEdges(Connector<Integer> node, SortedSet<Long> nodeCols) {
        var min = Math.min(node.col, node.next.col);
        var max = Math.max(node.col, node.next.col);
        if (min == node.col) {
            nodeCols = nodeCols.reversed();
        }
        var currConnector = node;
        var origNext = node.next;
        var dir = node.getDir();
        for (var otherCol : nodeCols) {
            if (otherCol <= min || otherCol >= max) {
                continue;
            }
            var between = Math.abs(node.col - otherCol);
            currConnector = insertBridges(currConnector, origNext, dir, between);
        }
    }

    private Connector<Integer> insertBridges(Connector<Integer> currConnector, Connector<Integer> origNext, DIR dir, long between) {
        int inBetweenOnly = (int) (between - 1);
        var edge = inBetweenOnly > 0 ? CompactedGrid.createNext(currConnector, dir, inBetweenOnly) : currConnector;
        var bridge = CompactedGrid.createNext(edge, dir, 1);
        bridge.setNext(origNext);
        currConnector.setValue(1);
        currConnector = bridge;
        return currConnector;
    }

    public record INSTRUCT(DIR dir, int amount, String color) {
    }
}