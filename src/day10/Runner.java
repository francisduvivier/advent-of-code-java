package day10;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static day10.Connector.findTilesRec;

public class Runner {
    private final String[] lines;

    public Runner(String[] lines) {
        this.lines = lines;
    }

    private static int getUnCountedJunk() {
        return 0;
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
        var tilesLeft = new HashSet<String>();
        Connector start = loopMap.values().stream().filter(v -> v.letter == startLetter).findFirst().get();
        var currConnector = start.connectees[0];
        currConnector.prev = start;
        var leftIsOutSide = false;
        while (currConnector != start) {
            var foundOutside = false;
            if (currConnector.letter == '|') {
                boolean prevIsDown = currConnector.prev.y > currConnector.y;
                if (prevIsDown) {
                    foundOutside = findTilesRec(loopMap, tilesLeft, currConnector.x - 1, currConnector.y);
                } else {
                    foundOutside = findTilesRec(loopMap, tilesLeft, currConnector.x + 1, currConnector.y);
                }
            } else if (currConnector.letter == '-') {
                boolean prevIsLeft = currConnector.prev.x < currConnector.x;
                if (prevIsLeft) {
                    foundOutside = findTilesRec(loopMap, tilesLeft, currConnector.x, currConnector.y - 1);
                } else {
                    foundOutside = findTilesRec(loopMap, tilesLeft, currConnector.x, currConnector.y + 1);
                }
            }
            if (foundOutside) {
                leftIsOutSide = true;
            }

            for (var other : currConnector.connectees) {
                if (other != currConnector.prev) {
                    other.prev = currConnector;
                    currConnector = other;
                    break;
                }
            }
        }
        int nbLeft = tilesLeft.size();
        int nbGridTiles = this.lines.length * this.lines[0].length();
        int totalNonLoopTiles = nbGridTiles - loopMap.size();
        if (leftIsOutSide) {
//            Set<String> uncountedJunk = findConnectorsIn(tilesLeft);
            int nbRight = totalNonLoopTiles - nbLeft;
            return nbRight
//                + uncountedJunk.size()
                ;
        } else {
//            HashSet<String> exclusions = new HashSet<>();
//            exclusions.addAll(tilesLeft);
//            exclusions.addAll(loopMap.keySet());
//            var unCountedJunk = findConnectorsNotIn(exclusions);
            return nbLeft
//                + unCountedJunk.size()
                ;
        }
    }

    private HashSet<String> findConnectorsNotIn(HashSet<String> exclusions) {
        HashSet<String> junk = new HashSet<>();
        for (var sy = 0; sy < lines.length; sy++) {
            for (var sx = 0; sx < lines[0].length(); sx++) {
                var tryConnector = new Connector(this.lines, sx, sy);
                if (!exclusions.contains(tryConnector.id)
                    && tryConnector.letter != '.') {
                    junk.add(tryConnector.id);
                }
            }
        }
        return junk;
    }

    private HashSet<String> findConnectorsIn(HashSet<String> inclusions) {
        HashSet<String> junk = new HashSet<>();
        for (var sy = 0; sy < lines.length; sy++) {
            for (var sx = 0; sx < lines[0].length(); sx++) {
                var tryConnector = new Connector(this.lines, sx, sy);
                if (tryConnector.letter != '.' && inclusions.contains(tryConnector.id)) {
                    junk.add(tryConnector.id);
                }
            }
        }
        return junk;
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