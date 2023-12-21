package day18.robot;

import util.DIR;
import util.TGrid;

import java.util.HashSet;
import java.util.Set;

import static util.DIR.DIRS;

public class ConnectorGrid extends TGrid<Integer, Connector<Integer>> {
    public ConnectorGrid() {
        super(null, null);
    }

    static boolean findTilesInDir(ConnectorGrid grid, Set<Connector<Integer>> tilesLeft, Connector<Integer> tile, DIR dir) {
        Boolean result = null;
        while (result == null) {
            Connector<Integer> loopTile = grid.getNext(tile, dir);
            if (loopTile != null && loopTile.value != null) {
                result = false;
                break;
            }
            Connector<Integer> newTile = createNext(tile, dir, 1);
            if (grid.isOutSide(newTile.row, newTile.col)) {
                result = true;
                break;
            }
            if (!tilesLeft.add(newTile)) {
                result = false;
                break;
            }
            tile = newTile;
        }
        return result;
    }

    public static Connector<Integer> insertNext(Connector<Integer> tile, DIR dir, int amountToArrive) {
        var origNext = tile.next;
        tile.setValue(amountToArrive);
        Connector<Integer> next = createNext(tile, dir, amountToArrive);
        tile.next = next;
        next.setNext(origNext);
        if (origNext != null) {
            origNext.setValue(origNext.value - amountToArrive);
            origNext.prev = next;
        }
        return next;
    }

    public static Connector<Integer> createNext(Connector<Integer> tile, DIR dir, int amountToArrive) {
        Connector<Integer> next = new Connector<>(tile.row + amountToArrive * dir.rowDiff, tile.col + amountToArrive * dir.colDiff, amountToArrive, tile);
        return next;
    }

    public long findTilesInside() {
        Connector<Integer> start = tiles.values().stream().findAny().get();
        Set<Connector<Integer>> tilesInside = new HashSet<>();
        var curr = start;
        var foundOutside = false;
        while (curr.prev != start) {
            if (!curr.isCornerTile()) {
                DIR leftHandDir = DIR.calcDir(curr, curr.prev).getLeftHandDir();
                if (findTilesInDir(this, tilesInside, curr, leftHandDir)) {
                    foundOutside = true;
                    break;
                }
            }
            curr = curr.prev;
        }
        curr = start;
        if (foundOutside) {
            foundOutside = false;
            tilesInside.clear();
            while (curr.prev != start) {
                if (!curr.isCornerTile()) {
                    DIR rightHandDir = DIR.calcDir(curr, curr.prev).getRightHandDir();
                    if (findTilesInDir(this, tilesInside, curr, rightHandDir)) {
                        foundOutside = true;
                        break;
                    }
                }
                curr = curr.prev;
            }
        }
        assert !foundOutside;
        var foundNewTiles = true;
        while (foundNewTiles) {
            var startAmount = tilesInside.size();
            for (var tile : new HashSet<>(tilesInside)) {
                setTile(tile);
                for (var dir : DIRS) {
                    findTilesInDir(this, tilesInside, tile, dir);
                }
            }
            foundNewTiles = startAmount != tilesInside.size();
        }
        return tiles.size();
    }

    public void fillFromConnector(Connector<Integer> start) {
        setTile(start);
        var curr = start;
        while (curr.prev != start) {
            setTile(curr);
            curr = curr.prev;
        }
        setTile(curr);
    }
}