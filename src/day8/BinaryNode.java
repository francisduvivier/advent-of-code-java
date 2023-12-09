package day8;

import java.util.List;

public class BinaryNode {
    final String leftId;
    final String rightId;
    final String id;

    public BinaryNode(String id, String leftId, String rightId) {

        this.id = id;
        this.leftId = leftId;
        this.rightId = rightId;
    }

    public static BinaryNode parse(List<String> strings) {
        String[] firstParts = strings.getFirst().split("[^A-Z]+");
        String id = firstParts[0];
        String leftId = firstParts[1];
        String rightId = firstParts[2];
        return new BinaryNode(id, leftId, rightId); //TODO
    }

    public long getStepsRec(String destinationId, String instructions) {
        return 0; //TODO
    }
}