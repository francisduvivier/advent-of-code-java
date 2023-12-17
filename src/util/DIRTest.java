package util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DIRTest {

    @Test
    void calcDir() {
        Tile first = new Tile(1, 1, "10");
        Tile down = new Tile(2, 1, "8");
        assertEquals(DIR.calcDir(first, down), DIR.DOWN);
        assertEquals(DIR.calcDir(down, first), DIR.UP);
        Tile up = new Tile(0, 1, "2");
        assertEquals(DIR.calcDir(first, up), DIR.UP);
        assertEquals(DIR.calcDir(up, first), DIR.DOWN);
        Tile right = new Tile(1, 2, "6");
        assertEquals(DIR.calcDir(first, right), DIR.RIGHT);
        assertEquals(DIR.calcDir(right, first), DIR.LEFT);
        Tile left = new Tile(1, 0, "4");
        assertEquals(DIR.calcDir(first, left), DIR.LEFT);
        assertEquals(DIR.calcDir(left, first), DIR.RIGHT);
    }
}