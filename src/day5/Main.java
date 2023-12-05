package day5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        var sampleInput = "";
        var input = "";
        solve(sampleInput);
        solve(input);
    }

    static String solve(String sampleInput) {
        var lines = sampleInput.split("\n");
        System.out.println("Read lines: " + lines.length);
        var mappers = new ArrayList<Mapper>();
        var i = 0;
        List<Long> seeds = new ArrayList<>();
        for (var line : lines) {
            if (i++ == 0) {
                seeds = Arrays.stream(line.split(": ")[1].split(" ")).map(Long::parseLong).collect(Collectors.toList());
            } else if (line.matches(".*map:$")) {
                mappers.add(new Mapper());
            } else if (line.matches("^[0-9 ]+$")) {
                mappers.getLast().addMappingLine(line);
            }
        }
        var lowestMapping = Long.MAX_VALUE;
        for (var seed : seeds) {
            long mapping = getMapping(seed, mappers);
            if (mapping < lowestMapping) {
                lowestMapping = mapping;
            }
        }
        return "" + lowestMapping;
    }

    static String solve2(String sampleInput) {
        var lines = sampleInput.split("\n");
        System.out.println("Read lines: " + lines.length);
        var mappers = new ArrayList<Mapper>();
        var currLine = 0;
        List<Long> seeds = new ArrayList<>();
        for (var line : lines) {
            if (currLine++ == 0) {
                seeds = Arrays.stream(line.split(": ")[1].split(" ")).map(Long::parseLong).collect(Collectors.toList());
            } else if (line.matches(".*map:$")) {
                mappers.add(new Mapper());
            } else if (line.matches("^[0-9 ]+$")) {
                mappers.getLast().addMappingLine(line);
            }
        }
        var lowestMapping = Long.MAX_VALUE;
        for (var seedIndex = 0; seedIndex < seeds.size(); seedIndex += 2) {
            Long maxSeedOffset = seeds.get(seedIndex + 1);
            for (var seedOffset = 0; seedOffset < maxSeedOffset; seedOffset++) {
                var seed = seeds.get(seedIndex) + seedOffset;
                long mapping = getMapping(seed, mappers);
                if (mapping < lowestMapping) {
                    lowestMapping = mapping;
                }
            }
        }
        return "" + lowestMapping;
    }

    private static long getMapping(Long seed, List<Mapper> mapperList) {
        var val = seed;
        for (var mapper : mapperList) {
//            System.out.printf("mapping: val: " + val);
            val = mapper.getMapping(val);
//            System.out.println(" -> " + val);
        }
        return val;
    }
}