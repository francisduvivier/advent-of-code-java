package day4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapperTest {

    @Test
    void getMapping() {
        // Arrange
        var mapper = new Mapper();
        // Act
        mapper.addMapping(10, 20, 2);
        // Assert
        assertEquals(mapper.getMapping(9), 9);
        assertEquals(mapper.getMapping(10), 20);
        assertEquals(mapper.getMapping(11), 21);
        assertEquals(mapper.getMapping(12), 12);
    }
}