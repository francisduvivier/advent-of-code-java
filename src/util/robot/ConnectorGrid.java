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
            public Connector<String> create(int row, int col, String val) {
                return new Connector<>(row, col, val, null);
            }
        });
    }

    static boolean findTilesRec(ConnectorGrid grid, Set<Connector<String>> tilesLeft, Connector<String> tile, DIR dir) {

        Connector<String> loopTile = grid.getNext(tile, dir);
        if (loopTile != null && loopTile.value != null) {
            return false;
        }
        Connector<String> newTile = createNext(tile, dir);
        if (grid.isOutSide(newTile.row, newTile.col)) {
            return true;
        }
        tilesLeft.add(newTile);

        var foundOutSide = false;
        DIR[] dirsToTry = new DIR[]{dir};
        for (var newDir : dirsToTry) {
            if (findTilesRec(grid, tilesLeft, newTile, newDir)) {
                foundOutSide = true;
            }
        }
        return foundOutSide;
    }

    public static Connector<String> createNext(Connector<String> tile, DIR dir) {
        return new Connector<>(tile.row + dir.rowDiff, tile.col + +dir.colDiff, tile.value, tile);
    }

    public int findTilesInside(Connector start) {
        Set<Connector<String>> tilesInside = new HashSet<>();
        var curr = start;
        var foundOutside = false;
        while (curr.prev != start) {
            if (!curr.isCornerTile()) {
                DIR leftHandDir = DIR.calcDir(curr, curr.prev).getLeftHandDir();
                if (findTilesRec(this, tilesInside, curr, leftHandDir)) {
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
                    if (findTilesRec(this, tilesInside, curr, rightHandDir)) {
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
                    findTilesRec(this, tilesInside, tile, dir);
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
            assert curr.value != null;
            curr = curr.prev;
        }
        setTile(curr);
    }
}