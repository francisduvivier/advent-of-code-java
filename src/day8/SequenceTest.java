package day8;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SequenceTest {

    @Test
    void getDiffs() {
        assertArrayEquals(new long[]{1, 1}, new Sequence(new long[]{1, 2, 3}).getDiffs());
        assertArrayEquals(new long[]{2, 2, 2, 2, 2}, new Sequence(new long[]{-2, 0, 2, 4, 6, 8}).getDiffs());
        assertArrayEquals(new long[]{-2, -2}, new Sequence(new long[]{-2, -4, -6}).getDiffs());
        assertArrayEquals(new long[]{3, -1}, new Sequence(new long[]{1, 4, 3}).getDiffs());
        assertArrayEquals(new long[]{2, 2, 2, 2, 4}, new Sequence(new long[]{-2, 0, 2, 4, 6, 10}).getDiffs());
        assertArrayEquals(new long[]{2, -2, -4}, new Sequence(new long[]{-4, -2, -4, -8}).getDiffs());
    }

    @Test
    void sameDiffs() {
        assertEquals(true, new Sequence(new long[]{1, 2, 3}).allSameDiff());
        assertEquals(true, new Sequence(new long[]{-2, 0, 2, 4, 6, 8}).allSameDiff());
        assertEquals(true, new Sequence(new long[]{-2, -4, -6}).allSameDiff());
        // Assert
        assertEquals(false, new Sequence(new long[]{1, 4, 3}).allSameDiff());
        assertEquals(false, new Sequence(new long[]{-2, 0, 2, 4, 6, 10}).allSameDiff());
        assertEquals(false, new Sequence(new long[]{-4, -2, -4, -8}).allSameDiff());
    }

    @Test
    void extrapolateRec() {
        assertEquals(68, new Sequence(new long[]{10, 13, 16, 21, 30, 45}).extrapolateRec());
        assertEquals(23, new Sequence(new long[]{3, 3, 5, 9, 15}).extrapolateRec());
        assertEquals(2, new Sequence(new long[]{2, 2, 2}).extrapolateRec());
        assertEquals(28, new Sequence(new long[]{1, 3, 6, 10, 15, 21}).extrapolateRec());
    }
}