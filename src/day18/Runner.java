package day18;

import day18.robot.Connector;
import util.DIR;

import java.util.*;
import java.util.function.Function;

public class Runner {

    public static Function<? super String, ?> createInstruction;
    private final INSTRUCT[] instructions;

    Runner(INSTRUCT[] instructions) {
        this.instructions = instructions;
    }

    long run() {

        CompactedGrid compactedGrid = getCompactedGrid();

        System.out.println("--- GRID START---");
        System.out.println("");
        System.out.println(compactedGrid);
        System.out.println("");
        System.out.println("--- GRID DONE---");

        return compactedGrid.findTilesInside();
    }

    private CompactedGrid getCompactedGrid() {
        var start = new Connector<>(0, 0, 1, null);
        var curr = start;
        Set<Connector<Integer>> nodeList = new HashSet<>();
        nodeList.add(start);
        for (INSTRUCT instruction : instructions) {
            nodeList.add(curr);
            Connector<Integer> next = CompactedGrid.createNext(curr, instruction.dir, instruction.amount);
            curr = next;
        }
        assert curr.equals(start);
        assert nodeList.size() == instructions.length;
        start.prev = curr.prev;
        curr.prev.setNext(start);
        // So what we want to do is: add the original connectors to the compacted grid with splitters in between them on all the other values of connector nodes
        // So we will first gather all the rows of connectors
        // Then for every vertical edge, we will split it for all of these x values and put multiplier edges in between
        var nodeRows = new TreeSet<>(nodeList.stream().map(n -> n.row).toList());
        var nodeCols = new TreeSet<>(nodeList.stream().map(n -> n.col).toList());
        for (var node : new ArrayList<>(nodeList)) {
            if (node.getDir().isHorizontal()) {
                addHorizontalMultiplierEdges(node, nodeCols, nodeList);
            } else {
                addVerticalMultiplierEdges(node, nodeRows, nodeList);
            }
        }
        CompactedGrid compactedGrid = new CompactedGrid(nodeList);
        return compactedGrid;
    }

    private void addVerticalMultiplierEdges(Connector<Integer> node, SortedSet<Long> nodeRows, Set<Connector<Integer>> nodeList) {
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
            int inBetweenOnly = (int) (between - 1);
            var edge = inBetweenOnly > 0 ? CompactedGrid.createNext(currConnector, dir, inBetweenOnly) : currConnector;
            nodeList.add(edge);
            var connector = CompactedGrid.createNext(edge, dir, 1);
            connector.setNext(origNext);
            nodeList.add(connector);
            currConnector = connector;
        }
    }

    private void addHorizontalMultiplierEdges(Connector<Integer> node, SortedSet<Long> nodeCols, Set<Connector<Integer>> nodeList) {
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
            int inBetweenOnly = (int) (between - 1);
            var edge = inBetweenOnly > 0 ? CompactedGrid.createNext(currConnector, dir, inBetweenOnly) : currConnector;
            nodeList.add(edge);
            var connector = CompactedGrid.createNext(edge, dir, 1);
            connector.setNext(origNext);
            nodeList.add(connector);
            currConnector = connector;
        }
    }

    public record INSTRUCT(DIR dir, int amount, String color) {
    }
}