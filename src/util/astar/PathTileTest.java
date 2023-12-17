package util.astar;

import org.junit.jupiter.api.Test;
import util.DIR;

import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PathTileTest {

    @Test
    void getDir() {
        var grid = new PathGrid(new String[]{"123", "456", "789"});
        //Arrange
        PathTile first = new PathTile(1, 1, 10, null);

        //Assert
        assertEquals(grid.getNext(first, DIR.DOWN).getDir(), DIR.DOWN);
        assertEquals(grid.getNext(first, DIR.UP).getDir(), DIR.UP);
        assertEquals(grid.getNext(first, DIR.RIGHT).getDir(), DIR.RIGHT);
        assertEquals(grid.getNext(first, DIR.LEFT).getDir(), DIR.LEFT);
    }

    @Test
    void getNext() {
        var grid = new PathGrid(new String[]{"123", "456", "789"});
        //Arrange
        PathTile first = new PathTile(1, 1, 10, null);
        PathTile down = new PathTile(2, 1, 8, first);
        PathTile up = new PathTile(0, 1, 2, first);
        PathTile right = new PathTile(1, 2, 6, first);
        PathTile left = new PathTile(1, 0, 4, first);

        //Assert
        assertEquals(grid.getNext(first, DIR.DOWN), down);
        assertEquals(grid.getNext(first, DIR.UP), up);
        assertEquals(grid.getNext(first, DIR.RIGHT), right);
        assertEquals(grid.getNext(first, DIR.LEFT), left);
    }

    @Test
    void compareTo() {
        var ordered = new PriorityQueue<PathTile>();
        PathTile worst = new PathTile(1, 1, 10, null);
        ordered.add(worst);
        PathTile best = new PathTile(10, 5, 10, null);
        ordered.add(best);
        PathTile middle = new PathTile(9, 5, 10, null);
        ordered.add(middle);
        assertEquals(ordered.remove(), best);
        assertEquals(ordered.remove(), middle);
        assertEquals(ordered.remove(), worst);
    }
}