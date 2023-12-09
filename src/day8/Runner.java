package day8;

import java.util.Arrays;

public class Runner {

    final String instructions;
    final BinaryTree startNode;

    public Runner(String instructions, BinaryTree startNode) {

        this.instructions = instructions;
        this.startNode = startNode;
    }

    public static Runner parse(String[] lines) {
        return new Runner(lines[0], BinaryTree.parse(Arrays.stream(lines).toList().subList(2, lines.length)));
    }

    public long stepsTo(String destinationId) {
        return startNode.getSteps(destinationId, instructions); // TODO
    }
}