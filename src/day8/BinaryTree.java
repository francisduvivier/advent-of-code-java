package day8;

import java.util.*;
import java.util.stream.Collectors;

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

        BinaryNode mainNode = nodeMap.get("AAA");
        if (mainNode == null) {
            mainNode = new BinaryNode("", "", "");
        }
        this.id = mainNode.id;
        this.leftId = mainNode.leftId;
        this.rightId = mainNode.rightId;

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

    public long getSteps(String startMatcher, String destinationMatcher, String instructions) {
        var startNodes = Arrays.stream(nodes).filter(node -> node.id.matches(startMatcher)).collect(Collectors.toSet());

        if (DEBUG) {
            System.out.println("getSteps to " + destinationMatcher + " for(" + instructions.length() + "): " + instructions);
        }
        var currNodes = new ArrayList<>(startNodes);
        long steps = 0;
        int opIndex = 0;
        char[] instructionChars = instructions.toCharArray();
        while (!currNodes.stream().allMatch(n -> n.id.matches(destinationMatcher))) {
            char op = instructionChars[opIndex];
            var i = 0;
            final var opi = opIndex;
            if (currNodes.stream().allMatch(n -> n.isTried(opi))) {
                throw new IllegalArgumentException("STEP [" + steps + "]: inst index [" + opIndex + "]-> " + op + " is already tried on [" + currNodes.get(0).id + "]");
            }
            for (final var currNode : currNodes) {

                BinaryNode next;
                if (op == 'L') {
                    next = (currNode.leftNode);
                } else {
                    next = (currNode.rightNode);
                }
                if (DEBUG) {
                    System.out.println("STEP [" + steps + "]: inst index [" + opIndex + "]-> " + op + " is being tried on [" + currNode.id + "] => [" + next.id + "]");
                }

                currNode.markTried(opIndex);
                currNodes.set(i++, next);
            }
            steps++;
            opIndex = (opIndex + 1) % instructionChars.length;
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
            String[] firstParts = line.split("[^A-Z0-9]+");
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