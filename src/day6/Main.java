package day6;

import day5.Mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
    }

    static String solve(String sampleInput) {
        String[] lines = sampleInput.split("\n");
        List<Integer> times = Arrays.stream(lines[0].split(": +")[1].split(" +")).map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> dists = Arrays.stream(lines[1].split(": +")[1].split(" +")).map(Integer::parseInt).collect(Collectors.toList());
        long result = 1;
        for (int i = 0; i < times.size(); i++) {
            result *= new Runner(times.get(i), dists.get(i)).getNbWinners();
        }
        return "" + result;

    }

    static String solve2(String sampleInput) {
        String[] lines = sampleInput.split("\n");
        List<Long> times = new ArrayList<>();
        List<Long> dists = new ArrayList<>();
        times.add(Long.parseLong(Arrays.stream(lines[0].split(": +")[1].split(" +")).collect(Collectors.joining())));
        dists.add(Long.parseLong(Arrays.stream(lines[1].split(": +")[1].split(" +")).collect(Collectors.joining())));
        long result = 1;
        for (int i = 0; i < times.size(); i++) {
            result *= new Runner(times.get(i), dists.get(i)).getNbWinners();
        }
        return "" + result;
    }

}