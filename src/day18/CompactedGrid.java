package day18;

import day18.robot.Connector;
import day18.robot.ConnectorGrid;

import java.util.Set;

public class CompactedGrid extends ConnectorGrid {
    public CompactedGrid(Set<Connector<Integer>> nodeList) {
        var rowVals = nodeList.stream().map(n -> n.row).toList();
        var colVals = nodeList.stream().map(n -> n.col).toList();
        var startNode = nodeList.stream().toList().get(0);
        System.out.println("Checking expanded loop");
        checkLoop(startNode);
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
        checkLoop(startCompacted);
        System.out.println("Done Checking compacted loop");
    }

    private void checkLoop(Connector<Integer> startCompacted) {
        var curr = startCompacted.next;
        while (curr != startCompacted) {
            curr = startCompacted.next;
        }
    }
}