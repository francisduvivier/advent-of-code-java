package day8;

import java.util.*;
import java.util.stream.Collectors;

public class BinaryTree {
    final boolean DEBUG = false;
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
        var startNodes = Arrays.stream(nodes).filter(node -> node.id.matches(startMatcher)).collect(Collectors.toList());

        if (DEBUG) {
            System.out.println("getSteps to " + destinationMatcher + " for(" + instructions.length() + "): " + instructions);
        }
        updateZAndRepeatDataForStartNodes(destinationMatcher, instructions, startNodes);
        System.out.println("Z indexes and repeats found for all startNodes(" + startNodes.size() + ")");
        return findZMatch(new ArrayList<>(startNodes));
    }

    long findZMatch(List<Repeater> startNodes) {
        var start = startNodes.get(0);
        List<Repeater> otherNodes = startNodes.subList(1, startNodes.size());
        for (long multiplier = 0; multiplier < Long.MAX_VALUE; multiplier++) {
            if (DEBUG || multiplier % 10000_000 == 0) {
                System.out.println("Trying multiplier: " + multiplier);
            }
            for (var zOption : start.getZIndexes()) {
                long steps = zOption + (start.getModulo() * multiplier);
                if (DEBUG) {
                    System.out.println("Trying steps: " + multiplier);
                }
                if (otherNodes.stream().allMatch(o -> hasZMatchAt(o, steps))) {
                    return steps;
                }
            }
        }
        return -1;
    }

    private boolean hasZMatchAt(Repeater o, long l) {
        for (var zOption : o.getZIndexes()) {
            if (l % o.getModulo() == zOption) {
                return true;
            }
        }
        return false;
    }

    private void updateZAndRepeatDataForStartNodes(String destinationMatcher, String instructions, List<BinaryNode> startNodes) {
        var currNodes = new ArrayList<>(startNodes);
        long steps = 0;
        int opIndex = 0;
        char[] instructionChars = instructions.toCharArray();
        while (!currNodes.stream().allMatch(n -> n == null)) {
            char op = instructionChars[opIndex];
            var i = 0;
            final var opi = opIndex;
            for (final var currNode : new ArrayList<>(currNodes)) {
                if (currNode == null) {
                    i++;
                    continue;
                }
                BinaryNode startNode = startNodes.get(i++);

                BinaryNode next;
                if (op == 'L') {
                    next = (currNode.leftNode);
                } else {
                    next = (currNode.rightNode);
                }
                if (currNode.isTried(opi)) {
                    startNode.modulo = steps + 1;
                    currNodes.set(currNodes.indexOf(currNode), null);
                } else {
                    currNodes.set(currNodes.indexOf(currNode), next);
                }

                if (DEBUG) {
                    System.out.println("STEP [" + steps + "]: inst index [" + opIndex + "]-> " + op + " is being tried on [" + currNode.id + "] => [" + next.id + "]");
                }

                currNode.markTried(opIndex);
                if (next.id.matches(destinationMatcher)) {
                    startNode.zIndexes.add(steps + 1);
                }
            }
            steps++;
            opIndex = (opIndex + 1) % instructionChars.length;
        }
    }

    private static class BinaryNode implements Repeater {
        final String leftId;
        final String rightId;
        final String id;
        public List<Long> zIndexes = new ArrayList<>();
        long modulo = -1;
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

        @Override
        public List<Long> getZIndexes() {
            return this.zIndexes;
        }

        @Override
        public long getModulo() {
            return this.modulo;
        }
    }
}