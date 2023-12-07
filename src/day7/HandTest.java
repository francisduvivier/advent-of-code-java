package day7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HandTest {

    @Test
    void rank() {
        assertEquals(Rank.FIVE_OF_A_KIND, new Hand("AAAAA").getRANK());
        assertEquals(Rank.FOUR_OF_A_KIND, new Hand("AA8AA").getRANK());
        assertEquals(Rank.FULL_HOUSE, new Hand("23332").getRANK());
        assertEquals(Rank.THREE_OF_A_KIND, new Hand("TTT98").getRANK());
        assertEquals(Rank.TWO_PAIR, new Hand("23432").getRANK());
        assertEquals(Rank.ONE_PAIR, new Hand("A23A4").getRANK());
        assertEquals(Rank.HIGH_CARD, new Hand("23456").getRANK());
    }

    @Test
    void secondaryRank() {
        assertEquals(new Hand("23456").getSecondaryRANK(), new Hand("23456").getSecondaryRANK());
        assertTrue(new Hand("23556").getSecondaryRANK() > new Hand("23456").getSecondaryRANK());
        assertTrue(new Hand("A2222").getSecondaryRANK() > new Hand("9AAAA").getSecondaryRANK());
        assertTrue(new Hand("A2223").getSecondaryRANK() > new Hand("A2222").getSecondaryRANK());
    }

    @Test
    void compareTo() {
        assertTrue(new Hand("23556").compareTo(new Hand("23456")) > 0);
        assertTrue(new Hand("A2223").compareTo(new Hand("92223")) > 0);
        assertTrue(new Hand("98989").compareTo(new Hand("91929")) > 0);
        assertTrue(new Hand("QQQQ2").compareTo(new Hand("JKKK2")) > 0);
        assertTrue(new Hand("JKKK2").compareTo(new Hand("QQQ22")) > 0);
    }
}