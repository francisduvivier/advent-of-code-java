package day10;

import java.util.HashMap;
import java.util.Map;

public class Runner {
    private final String[] lines;

    public Runner(String[] lines) {
        this.lines = lines;
    }

    public long getLoopSize() {
        return getLoopSize('S');
    }

    public long getLoopSize(char startChar) {
        for (var sy = 0; sy < lines.length; sy++) {
            for (var sx = 0; sx < lines[0].length(); sx++) {
                var tryConnector = new Connector(this.lines, sx, sy);
                if (tryConnector.letter == startChar) {

                    return followLoop(tryConnector);
                }

            }
        }
        throw new IllegalArgumentException("StartChar [" + startChar + "] Not found");
    }

    private long followLoop(Connector tryConnector) {
        Map<String, Connector> cMap = new HashMap<>();
        var currConnector = tryConnector;
        while (!cMap.containsKey(currConnector.id)) {
            cMap.put(currConnector.id, currConnector);
            var connectees = currConnector.updateConnectees();
            for (var c : connectees) {
                if (!cMap.containsKey(c.id)) {
                    currConnector = c;
                    break;
                }
            }
        }
        return cMap.size();
    }

    long getStepsToFarthestPoint() {
        return getLoopSize() / 2;
    }
}