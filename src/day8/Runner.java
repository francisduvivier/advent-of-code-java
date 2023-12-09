package day8;

import java.util.Arrays;

public class Runner {

    final String instructions;
    final BinaryTree tree;

    public Runner(String instructions, BinaryTree tree) {

        this.instructions = instructions;
        this.tree = tree;
    }

    public static Runner parse(String[] lines) {
        return new Runner(lines[0], BinaryTree.parse(Arrays.stream(lines).toList().subList(2, lines.length)));
    }

    public long stepsTo(String destinationId) {
        return tree.getSteps(destinationId, instructions); // TODO
    }
}