package day18;

import util.DIR;
import util.robot.Connector;
import util.robot.ConnectorGrid;

public class Runner {

    private final String[] inputLines;

    Runner(String[] inputLines) {
        this.inputLines = inputLines;
    }

    long run() {
        ConnectorGrid grid = new ConnectorGrid();
        var start = new Connector<>(0, 0, "", null);
        var curr = start;
        for (var line : inputLines) {
            var dir = DIR.valueOfLetter(line.split(" ")[0]);
            assert dir != null;
            var amount = Integer.parseInt(line.split(" ")[1]);
            var color = line.split(" ")[2].split("[()#]+")[1];
            if (curr == start) {
                curr.setValue(color);
            }
            for (int step = 1; step <= amount; step++) {
                Connector<String> next = ConnectorGrid.createNext(curr, dir);
                curr.setNext(next);
                next.setValue(color);
                curr = next;
            }
        }
        assert curr.equals(start);
        start.prev = curr.prev;
        curr.prev.setNext(start);
        grid.fillFromConnector(start);
        System.out.println(grid.toString());
        return grid.findTilesInside(start);
    }
}