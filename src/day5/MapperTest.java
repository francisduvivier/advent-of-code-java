package day5;

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
        assertEquals(9, mapper.getMapping(9));
        assertEquals(20, mapper.getMapping(10));
        assertEquals(21, mapper.getMapping(11));
        assertEquals(12, mapper.getMapping(12));
    }

    @Test
    void getMappingLower() {
        // Arrange
        var mapper = new Mapper();
        // Act
        mapper.addMapping(new Mapping(20, 10, 2));
        // Assert
        assertEquals(19, mapper.getMapping(19));
        assertEquals(10, mapper.getMapping(20));
        assertEquals(11, mapper.getMapping(21));
        assertEquals(22, mapper.getMapping(22));
    }

    @Test
    void addMappingLine() {
        // Arrange
        var mapper = new Mapper();
        // Act
        mapper.addMappingLine("20 10 2");
        // Assert
        assertEquals(19, mapper.getMapping(19));
        assertEquals(10, mapper.getMapping(20));
        assertEquals(11, mapper.getMapping(21));
        assertEquals(22, mapper.getMapping(22));
    }
}