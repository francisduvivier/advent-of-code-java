package day18.robot;

import util.DIR;
import util.TGrid;

import java.util.Set;

public class ConnectorGrid<T> extends TGrid<T, Connector<T>> {
    public ConnectorGrid() {
        super(null, null);
    }

    public static <T> Connector<T> insertBefore(Connector<T> start, DIR dir, long newCoord) {
        Connector<T> newNode = dir.isHorizontal() ?
            new Connector<>(start.row, newCoord, null, start.prev) :
            new Connector<>(newCoord, start.col, null, start.prev);
        newNode.setNext(start);
        start.prev.setNext(newNode);
        start.prev = newNode;
        assert newNode.getDir() == dir;
        return newNode;
    }

    public static <T> Connector<T> createNext(Connector<T> tile, DIR dir, int amountToArrive) {
        Connector<T> next = new Connector<>(tile.row + amountToArrive * dir.rowDiff, tile.col + amountToArrive * dir.colDiff, null, tile);
        return next;
    }
}