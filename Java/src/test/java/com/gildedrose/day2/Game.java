package com.gildedrose.day2;

import java.util.ArrayList;
import java.util.List;

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
}

