package be.francisduvivier.aoc2023.day2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Game {
    private Game() {
    }

    public int id;
    public List<CubeSet> cubeSets = new ArrayList<>();

    public static Game parseHand(String line) {
        Game game = new Game();
        String[] gameAndSets = line.split(": ");
        game.id = Integer.parseInt(gameAndSets[0].split(" ")[1]);
        String[] setStrings = gameAndSets[1].split("; ");
        for (String setString : setStrings) {
            game.cubeSets.add(CubeSet.parseSet(setString));
        }
        return game;
    }

    public boolean isOkGamePart1() {
        int red = calcMax(cubeSets.stream().map(cubeSet -> cubeSet.red).collect(Collectors.toList()));
        if (red > 12) {
            return false;
        }
        int green = calcMax(cubeSets.stream().map(cubeSet -> cubeSet.green).collect(Collectors.toList()));
        if (green > 13) {
            return false;
        }
        int blue = calcMax(cubeSets.stream().map(cubeSet -> cubeSet.blue).collect(Collectors.toList()));
        if (blue > 14) {
            return false;
        }
        return true;
    }

    private int calcMax(List<Integer> intList) {
        int max = 0;
        for (int item : intList) {
            if (item > max) {
                max = item;
            }
        }
        return max;
    }

    private int calcSum(List<Integer> intList) {
        int sum = 0;
        for (int item : intList) {
            sum += item;
        }
        return sum;
    }

    public long getPower() {
        long power = 1;
        int minRed = calcMax(cubeSets.stream().map(cubeSet -> cubeSet.red).collect(Collectors.toList()));
        power *= minRed;

        int minGreen = calcMax(cubeSets.stream().map(cubeSet -> cubeSet.green).collect(Collectors.toList()));
        power *= minGreen;

        int minBlue = calcMax(cubeSets.stream().map(cubeSet -> cubeSet.blue).collect(Collectors.toList()));
        power *= minBlue;
        return power;
    }
}

