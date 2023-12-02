package com.gildedrose.day2;

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
        int red = calcSum(cubeSets.stream().map(cubeSet -> cubeSet.red).collect(Collectors.toList()));
        if (red > 12) {
            return false;
        }
        int green = calcSum(cubeSets.stream().map(cubeSet -> cubeSet.green).collect(Collectors.toList()));
        if (green > 13) {
            return false;
        }
        int blue = calcSum(cubeSets.stream().map(cubeSet -> cubeSet.blue).collect(Collectors.toList()));
        if (blue > 14) {
            return false;
        }
        return true;
    }

    private int calcSum(List<Integer> intList) {
        int sum = 0;
        for (int item : intList) {
            sum += item;
        }
        return sum;
    }
}

