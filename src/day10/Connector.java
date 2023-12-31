package day10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public class Connector {

    static final int[][] DIRXY_OPTIONS;
    static int[] UP = {0, -1};
    static int[] RIGHT = {1, 0};
    static int[] LEFT = {-1, 0};
    static int[] DOWN = {0, 1};

    static {
        DIRXY_OPTIONS = new int[][]{UP, RIGHT, LEFT, DOWN};
    }

    final int x;
    final int y;
    final char letter;
    final Connector[] connectees = new Connector[2];
    final String id;
    final String[] grid;
    public Connector prev;

    public Connector(String[] grid, int x, int y) {
        this.x = x;
        this.y = y;
        this.grid = grid;
        this.letter = this.grid[y].charAt(x);
        this.id = createKey(this.x, this.y);
    }

    static String createKey(int x1, int y1) {
        return x1 + "," + y1;
    }

    static boolean findTilesRec(Map<String, Connector> loopMap, Set<String> tilesLeft, int x, int y) {
        String id = Connector.createKey(x, y);
        if (loopMap.containsKey(id)) {
            return false;
        }
        String[] grid = loopMap.values().stream().findAny().get().grid;
        if (isOutSide(x, y, grid)) {
            return true;
        }
        if (tilesLeft.contains(id)) {
            return false;
        }

        tilesLeft.add(id);
        var foundOutSize = false;
        for (var xy : DIRXY_OPTIONS) {
            if (findTilesRec(loopMap, tilesLeft, x + xy[0], y + xy[1])) {
                foundOutSize = true;
            }
        }
        return foundOutSize;
    }

    private static boolean isOutSide(int x, int y, String[] grid) {
        return x < 0
            || x >= grid[0].length()
            || y < 0
            || y >= grid.length;
    }

    Connector[] updateConnectees(Map<String, Connector> cMap) {
        var connecteeList = new ArrayList<Connector>();
        int[][] directions = new int[][]{};
        switch (this.letter) {
            case 'S': {
                for (var xYOffset : DIRXY_OPTIONS) {
                    try {
                        var tryConnector = new Connector(this.grid, x + xYOffset[0], y + xYOffset[1]);
                        if (tryConnector.letter != '.') {
                            Connector[] secondOrderConnectees = tryConnector.updateConnectees(cMap);
                            if (Arrays.stream(secondOrderConnectees).anyMatch(connector -> connector.id.equals(this.id))) {
                                connecteeList.add(tryConnector);
                            }
                        }
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("Nok x offset for " + this + ": " + xYOffset[0] + "," + xYOffset[1]);
                        // do nothing, is ok
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Nok y offset for " + this + ": " + xYOffset[0] + "," + xYOffset[1]);
                        // do nothing, is ok
                    }
                }
                break;
            }
            case 'J': {
                directions = new int[][]{UP, LEFT};
                break;
            }
            case 'L': {
                directions = new int[][]{UP, RIGHT};
                break;
            }
            case 'F': {
                directions = new int[][]{DOWN, RIGHT};
                break;
            }
            case '7': {
                directions = new int[][]{DOWN, LEFT};
                break;
            }
            case '-': {
                directions = new int[][]{LEFT, RIGHT};
                break;
            }
            case '|': {
                directions = new int[][]{DOWN, UP};
                break;
            }
            default:
                break;
        }
        try {
            Arrays.stream(directions).forEach(xy -> connecteeList.add(new Connector(grid, x + xy[0], y + xy[1])));
        } catch (Exception e) {
            throw e; //Should not happen;
        }
        assert connecteeList.size() == 2;
        connecteeList.stream().map(c -> {
            if (cMap.containsKey(c.id)) {
                return cMap.get(c.id);
            }
            return c;
        }).toList().toArray(connectees);
        return connectees;
    }

    @Override
    public String toString() {
        return "Connector{" +
            "letter=" + letter +
            ", id='" + id + '\'' +
            '}';
    }
}