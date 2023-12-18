package day18;

import util.DIR;
import util.robot.Connector;
import util.robot.ConnectorGrid;

import java.util.function.Function;

public class Runner {

    public static Function<? super String, ?> createInstruction;
    private final INSTRUCT[] instructions;

    Runner(INSTRUCT[] instructions) {
        this.instructions = instructions;
    }

    long run() {
        ConnectorGrid grid = new ConnectorGrid();
        var start = new Connector<>(0, 0, "", null);
        var curr = start;
        for (INSTRUCT instruction : instructions) {
            if (curr == start) {
                curr.setValue(instruction.color());
            }
            for (long step = 1; step <= instruction.amount(); step++) {
                Connector<String> next = ConnectorGrid.createNext(curr, instruction.dir());
                curr.setNext(next);
                next.setValue(instruction.color());
                curr = next;
            }
        }
        assert curr.equals(start);
        start.prev = curr.prev;
        curr.prev.setNext(start);
        grid.fillFromConnector(start);

        System.out.println("--- GRID START---");
//        System.out.println("");
//        System.out.println(grid);
//        System.out.println("");
        System.out.println("--- GRID DONE---");

        return grid.findTilesInside(start);
    }

    public record INSTRUCT(DIR dir, long amount, String color) {
    }
}