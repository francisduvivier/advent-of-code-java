package day8;

import java.util.*;

public class BinaryTree {
    final boolean DEBUG = true;
    final BinaryNode[] nodes;
    final Map<String, BinaryNode> nodeMap;
    final String leftId;
    final String rightId;
    final String id;

    private BinaryTree(List<BinaryNode> nodeList) {
        this.nodes = new BinaryNode[nodeList.size()];
        nodeList.toArray(this.nodes);
        this.nodeMap = createMap(this.nodes);
        this.id = nodes[0].id;
        this.leftId = nodes[0].leftId;
        this.rightId = nodes[0].rightId;

    }

    public static BinaryTree parse(List<String> strings) {
        return new BinaryTree(strings.stream().map(BinaryNode::parse).toList());
    }

    private Map<String, BinaryNode> createMap(BinaryNode[] nodes) {
        HashMap<String, BinaryNode> map = new HashMap<>();
        for (var node : nodes) {
            map.put(node.id, node);
        }
        for (var node : nodes) {
            node.leftNode = map.get(node.leftId);
            node.rightNode = map.get(node.rightId);
        }
        return map;
    }

    public long getSteps(String destinationId, String instructions) {
        if (DEBUG) {
            System.out.println("getSteps to " + destinationId + " for(" + instructions.length() + "): " + instructions);
        }
        var currNode = this.nodes[0];
        long steps = 0;
        int instructionIndex = 0;
        char[] instructionChars = instructions.toCharArray();
        while (!currNode.id.equals(destinationId)) {
            if (DEBUG) {
                System.out.println("STEP [" + steps + "]: inst index [" + instructionIndex + "]-> " + instructionChars[instructionIndex] + " is being tried on [" + currNode.id + "]");
            }
            if (currNode.isTried(instructionIndex)) {
                throw new IllegalArgumentException("STEP [" + steps + "]: inst index [" + instructionIndex + "]-> " + instructionChars[instructionIndex] + " is already tried on [" + currNode.id + "]");
            }
            currNode.markTried(instructionIndex);
            if (instructionChars[instructionIndex] == 'L') {
                currNode = currNode.leftNode;
            } else {
                currNode = currNode.rightNode;
            }
            steps++;
            instructionIndex = (instructionIndex + 1) % instructionChars.length;
        }

        return steps;
    }

    private static class BinaryNode {
        final String leftId;
        final String rightId;
        final String id;
        BinaryNode rightNode;
        BinaryNode leftNode;
        Set<Integer> triedValues = new HashSet<>();

        public BinaryNode(String id, String leftId, String rightId) {
            this.id = id;
            this.leftId = leftId;
            this.rightId = rightId;
        }

        public static BinaryNode parse(String line) {
            String[] firstParts = line.split("[^A-Z]+");
            String id = firstParts[0];
            String leftId = firstParts[1];
            String rightId = firstParts[2];
            return new BinaryNode(id, leftId, rightId);
        }

        public void markTried(int instructionIndex) {
            triedValues.add(instructionIndex);
        }

        public boolean isTried(int instructionIndex) {
            return triedValues.contains(instructionIndex);
        }
    }
}