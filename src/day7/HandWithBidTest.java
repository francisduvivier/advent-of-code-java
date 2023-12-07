package day7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HandWithBidTest {

    @Test
    void getWinnings() {
        assertEquals(765, new HandWithBid("32T3K 765").getWinnings(1));
        assertEquals(2 * 765, new HandWithBid("32T3K 765").getWinnings(2));
    }
}