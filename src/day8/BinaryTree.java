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

    static long solveEuclid(List<Long> zVals) {
        var currVal = zVals.get(0);
        for (var val : zVals.subList(1, zVals.size())) {
            currVal = lcm(currVal, val);
        }
        return currVal;
    }

    private static long lcm(long currVal, long zVal) {
        return currVal * zVal / gcd(currVal, zVal);
    }

    private static long gcd(long currVal, long zVal) {
        while (currVal != 0 && zVal != 0) {
            if (zVal > currVal) {
                zVal %= currVal;
            } else {
                currVal %= zVal;
            }
        }
        return currVal + zVal;
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
        List<ZWithMod> zData = startNodes.stream().map(binaryNode -> new ZWithMod(binaryNode.zIndexes.get(0), binaryNode.modulo)).collect(Collectors.toList());
        return solveEuclid(zData.stream().mapToLong(zd -> zd.zVal).boxed().toList());
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
                    startNode.modulo -= startNode.modulo % instructionChars.length;
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