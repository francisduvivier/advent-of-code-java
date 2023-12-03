package com.gildedrose.day2;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Solution {

    private int solvePart1(String input) {
        String[] lines = input.split("\n");
        List<Game> games = Arrays.stream(lines).map(Game::parseHand).collect(Collectors.toList());
        List<Game> okGames = games.stream().filter(Game::isOkGamePart1).collect(Collectors.toList());
        int sum = 0;
        for (Game okGame : okGames) {
            sum += okGame.id;
        }
        return sum;
    }

    /**
     * Being a legendary item, never has to be sold or decreases in Quality
     */
    @Test
    public void runExample1() {
        String input = "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green\n" +
            "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue\n" +
            "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red\n" +
            "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red\n" +
            "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green";
        int solution = solvePart1(input);
        assertEquals(solution, 8);
    }

    /**
     * Being a legendary item, never has to be sold or decreases in Quality
     */
    @Test
    public void solution1() throws IOException {
        String content = Files.readString(Path.of("src/test/java/com/gildedrose/day2/input.txt"));
        System.out.println(solvePart1(content));
    }

    private int solvePart2(String input) {
        String[] lines = input.split("\n");
        List<Game> okGames = Arrays.stream(lines).map(Game::parseHand).collect(Collectors.toList());
        int sum = 0;
        for (Game okGame : okGames) {
            sum += okGame.getPower();
        }
        return sum;
    }

    /**
     * Being a legendary item, never has to be sold or decreases in Quality
     */
    @Test
    public void runExample2() {
        String input = "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green\n" +
            "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue\n" +
            "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red\n" +
            "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red\n" +
            "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green";
        int solution = solvePart2(input);
        assertEquals(solution, 2286);
    }

    /**
     * Being a legendary item, never has to be sold or decreases in Quality
     */
    @Test
    public void solution2() throws IOException {
        String content = Files.readString(Path.of("src/test/java/com/gildedrose/day2/input.txt"));
        System.out.println(solvePart2(content));

    }
}
