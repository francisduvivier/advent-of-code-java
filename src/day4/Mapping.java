package day4;

public class Mapping {
    public final int source;
    public final int dest;
    public final int range;

    public Mapping(int source, int dest, int range) {

        this.source = source;
        this.dest = dest;
        this.range = range;
    }

    public static Mapping parseMapping(String mappingLine) {
        // EG 3078006360 2182201339 30483272
        var matches = mappingLine.split(" ");

        return new Mapping(
            Integer.parseInt(matches[0]),
            Integer.parseInt(matches[1]),
            Integer.parseInt(matches[2])
        );
    }
}