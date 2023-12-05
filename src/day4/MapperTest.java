package day4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapperTest {

    @Test
    void getMappingHigher() {
        // Arrange
        var mapper = new Mapper();
        // Act
        mapper.addMapping(new Mapping(10, 20, 2));
        // Assert
        assertEquals(mapper.getMapping(9), 9);
        assertEquals(mapper.getMapping(10), 20);
        assertEquals(mapper.getMapping(11), 21);
        assertEquals(mapper.getMapping(12), 12);
    }

    @Test
    void getMappingLower() {
        // Arrange
        var mapper = new Mapper();
        // Act
        mapper.addMapping(new Mapping(20, 10, 2));
        // Assert
        assertEquals(mapper.getMapping(19), 19);
        assertEquals(mapper.getMapping(20), 10);
        assertEquals(mapper.getMapping(21), 11);
        assertEquals(mapper.getMapping(22), 22);
    }

    @Test
    void addMappingLine() {
        // Arrange
        var mapper = new Mapper();
        // Act
        mapper.addMappingLine("20 10 2");
        // Assert
        assertEquals(mapper.getMapping(19), 19);
        assertEquals(mapper.getMapping(20), 10);
        assertEquals(mapper.getMapping(21), 11);
        assertEquals(mapper.getMapping(22), 22);
    }
}