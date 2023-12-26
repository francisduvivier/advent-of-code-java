package day19;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class XmasTest {

    @Test
    void parseAndGetLetter() {
        //Arrange
        var result = Xmas.parse("{x=2127,m=1623,a=2188,s=1013}");

        //Assert

        assertEquals(2127, result.getLetter("x"));
        assertEquals(1623, result.getLetter("m"));
        assertEquals(2188, result.getLetter("a"));
        assertEquals(1013, result.getLetter("s"));
    }
}