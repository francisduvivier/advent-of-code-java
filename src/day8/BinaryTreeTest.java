package day8;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BinaryTreeTest {

    @Test
    void solveEuclid() {
        int TODO = 4000;
        // Solve for x: (M1 *x + Z1) mod M2 = Z2
        assertEquals(TODO, BinaryTree.solveEuclid(List.of(
            new ZWithMod(20803, 20808),
            new ZWithMod(17873, 17880)
        )));

        assertEquals(TODO, BinaryTree.solveEuclid(List.of(
            new ZWithMod(20803, 20808),
            new ZWithMod(17873, 17880),
            new ZWithMod(21389, 21395)
        )));

    }
}