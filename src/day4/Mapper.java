package day4;

import java.util.ArrayList;
import java.util.List;

public class Mapper {
    private List<Mapping> mappings = new ArrayList<>();

    void addMapping(int source, int dest, int range) {
        mappings.add(new Mapping(source, dest, range));
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