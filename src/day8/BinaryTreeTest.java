package day8;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BinaryTreeTest {

    @Test
    void solveEuclid() {
        assertEquals(1268983, BinaryTree.solveEuclid(List.of(
            20803L,
            17873L
        )));

        assertEquals(92635759, BinaryTree.solveEuclid(List.of(
            20803L,
            17873L,
            21389L
        )));
    }
}