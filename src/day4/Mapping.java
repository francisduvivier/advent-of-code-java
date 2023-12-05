package day4;

public class Mapping {
    public final long source;
    public final long dest;
    public final long range;

    public Mapping(long dest, long source, long range) {
        this.dest = dest;
        this.source = source;
        this.range = range;
    }

    public static Mapping parseMapping(String mappingLine) {
        // EG 3078006360 2182201339 30483272
        var matches = mappingLine.split(" ");

        return new Mapping(
            Long.parseLong(matches[0]),
            Long.parseLong(matches[1]),
            Long.parseLong(matches[2])
        );
    }
}