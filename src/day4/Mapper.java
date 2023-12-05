package day4;

import java.util.ArrayList;
import java.util.List;

public class Mapper {
    private List<Mapping> mappings = new ArrayList<>();

    void addMappingLine(String mappingLine) {
        addMapping(Mapping.parseMapping(mappingLine));
    }

    void addMapping(Mapping mapping) {
        mappings.add(mapping);
    }

    int getMapping(int value) {
        for (var mapping : mappings) {
            if (value >= mapping.source && value < (mapping.source + mapping.range)) {
                int mappDiff = mapping.dest - mapping.source;
                return value + mappDiff;
            }
        }
        return value;
    }
}