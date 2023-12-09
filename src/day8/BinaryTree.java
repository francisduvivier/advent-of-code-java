package day8;

import java.util.List;

public class BinaryTree {
    final BinaryNode[] nodes;
    final String leftId;
    final String rightId;
    final String id;

    public BinaryTree(BinaryNode[] nodes) {
        this.nodes = nodes;
        this.id = nodes[0].id;
        this.leftId = nodes[0].leftId;
        this.rightId = nodes[0].rightId;

    }

    public static BinaryTree parse(List<String> strings) {
        return new BinaryTree(new BinaryNode[]{BinaryNode.parse(strings.getFirst())});
    }

    public long getStepsRec(String destinationId, String instructions) {
        return 0; //TODO
    }

    private static class BinaryNode {
        final String leftId;
        final String rightId;
        final String id;

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

    }
}