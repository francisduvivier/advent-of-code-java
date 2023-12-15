package day8;

import java.util.Arrays;

public class Runner {

    final String instructions;
    final BinaryTree tree;

    Runner(String instructions, BinaryTree tree) {

        this.instructions = instructions;
        this.tree = tree;
    }

    public static Runner parse(String[] lines) {
        return new Runner(lines[0], BinaryTree.parse(Arrays.stream(lines).toList().subList(2, lines.length)));
    }

    public long stepsTo(String destinationMatcher) {
        return tree.getSteps("AAA", destinationMatcher, instructions);
    }

    public long multiStepsTo(String startMatcher, String destinationMatcher) {
        return tree.getSteps(startMatcher, destinationMatcher, instructions);
    }
}