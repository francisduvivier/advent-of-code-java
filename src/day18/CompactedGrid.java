package day18;

import day18.robot.Connector;
import day18.robot.ConnectorGrid;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class CompactedGrid extends ConnectorGrid {
    public CompactedGrid(Set<Connector<Integer>> nodeList) {
        buildCompactedGrid(nodeList);
    }

    static Set<Connector<Integer>> createNodeList(Connector<Integer> startNode) {
        Set<Connector<Integer>> nodeList = new HashSet<>();

        nodeList.add(startNode);
        var curr = startNode.next;
        while (curr != startNode) {
            System.out.println(curr.prev.getDir() + "" + curr.getDir() + ":" + curr.key + ":" + curr.value);
            nodeList.add(curr);
            curr = curr.next;
        }
        return nodeList;
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
        Connector<Integer> startCompacted = new Connector<>(rowVals.indexOf(startNode.row), colVals.indexOf(startNode.col), startNode.value, null);
        Connector<Integer> lastCompacted = startCompacted;
        setTile(startCompacted);

        var curr = startNode.next;
        while (curr != startNode) {
            var compactedNode = new Connector<>(rowVals.indexOf(curr.row), colVals.indexOf(curr.col), curr.value, lastCompacted);
            lastCompacted.next = compactedNode;
            this.setTile(compactedNode);
            lastCompacted = compactedNode;
            curr = curr.next;
        }
        startCompacted.prev = lastCompacted;
        lastCompacted.next = startCompacted;
        System.out.println("Checking compacted loop");
        createNodeList(startCompacted);
        System.out.println("Done Checking compacted loop");
    }
}