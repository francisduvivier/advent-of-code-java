package util.robot;

import util.DIR;
import util.TGrid;
import util.TileFactory;

import java.util.HashSet;
import java.util.Set;

import static util.DIR.DIRS;

public class ConnectorGrid extends TGrid<String, Connector<String>> {
    public ConnectorGrid() {
        super(new String[]{}, new TileFactory<>() {
            @Override
            public Connector<String> create(long row, long col, String val) {
                return new Connector<>(row, col, val, null);
            }
        });
    }

    static boolean findTilesInDir(ConnectorGrid grid, Set<Connector<String>> tilesLeft, Connector<String> tile, DIR dir) {
        Boolean result = null;
        while (result == null) {
            Connector<String> loopTile = grid.getNext(tile, dir);
            if (loopTile != null && loopTile.value != null) {
                result = false;
                break;
            }
            Connector<String> newTile = createNext(tile, dir);
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

    public static Connector<String> createNext(Connector<String> tile, DIR dir) {
        return new Connector<>(tile.row + dir.rowDiff, tile.col + dir.colDiff, tile.value, tile);
    }

    public long findTilesInside(Connector<String> start) {
        Set<Connector<String>> tilesInside = new HashSet<>();
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
                tile.setValue("#");
                setTile(tile);
                for (var dir : DIRS) {
                    findTilesInDir(this, tilesInside, tile, dir);
                }
            }
            foundNewTiles = startAmount != tilesInside.size();
        }
        return tiles.size();
    }

    public void fillFromConnector(Connector<String> start) {
        setTile(start);
        var curr = start;
        while (curr.prev != start) {
            setTile(curr);
//            assert curr.value != null;
            curr = curr.prev;
        }
        setTile(curr);
    }
}