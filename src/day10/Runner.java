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
        return getLoopMap('S').size();
    }

    private Map<String, Connector> followLoop(Connector start) {
        Map<String, Connector> cMap = new HashMap<>();
        var currConnector = start;
        while (!cMap.containsKey(currConnector.id)) {
            cMap.put(currConnector.id, currConnector);
            var connectees = currConnector.updateConnectees(cMap);
            for (var c : connectees) {
                if (!cMap.containsKey(c.id)) {
                    c.prev = currConnector;
                    currConnector = c;
                    break;
                }
            }
        }
        return cMap;
    }

    long getStepsToFarthestPoint() {
        return getLoopSize() / 2;
    }

    public long findTilesInside() {
        char startLetter = 'S';
        var loopMap = getLoopMap(startLetter);
        Connector start = loopMap.values().stream().filter(v -> v.letter == startLetter).findFirst().get();
        var currConnector = start.connectees[0];

        return loopMap.size();
    }

    private Map<String, Connector> getLoopMap(char startChar) {
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
}