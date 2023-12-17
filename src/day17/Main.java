package day17;

import util.astar.PathGrid;
import util.astar.PathTile;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
    }

    static String solve(String sampleInput) {
        String[] lines = sampleInput.split("\n");
        var grid = new PathGrid(lines);
        ConstrainedPathRunner constrainedPathRunner = new ConstrainedPathRunner(grid);
        PathTile result = constrainedPathRunner.run(3);
        System.out.println(constrainedPathRunner.getPathString(result));
        Integer firstTile = grid.getTile(0, 0).value;
        return "" + (result.cost - firstTile);
    }

    static String solve2(String sampleInput) {
        String[] lines = sampleInput.split("\n");
        var grid = new PathGrid(lines);
        ConstrainedPathRunner constrainedPathRunner = new ConstrainedPathRunner(grid);
        PathTile result = constrainedPathRunner.run(10);
        System.out.println(constrainedPathRunner.getPathString(result));
        Integer firstTile = grid.getTile(0, 0).value;
        return "" + (result.cost - firstTile);
    }

}