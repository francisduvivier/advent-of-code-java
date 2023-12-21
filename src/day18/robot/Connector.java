package day18.robot;

import util.DIR;
import util.VTile;

public class Connector<T> extends VTile<T> {

    public Connector<T> prev;
    public Connector<T> next;

    public Connector(long row, long col, T value, Connector<T> prev) {
        super(row, col, value);
        this.prev = prev;
        if (prev != null) {
            assert (prev.col != col) != (prev.row != row);// Straight line
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Connector<?>)) {
            return false;
        }
        if (obj == null) {
            return false;
        }
        return ((Connector<?>) obj).key.equals(this.key);
    }

    public void setValue(T newVal) {
        this.value = newVal;
    }

    @Override
    public String toString() {
        if (this.next == null || this.prev == null) {
            return "O";
        }
        if (this.value.equals(1)) {
            return this.getDir().toString();
        } else if (this.isCornerTile()) {
            return "X";
        } else {
            return this.getDir().isHorizontal() ? "-" : "|";
        }
    }

    @Override
    public int hashCode() {
        return this.key.hashCode();
    }

    public boolean isCornerTile() {
        return this.getDir() != prev.getDir();
    }

    public void setNext(Connector<T> next) {
        this.next = next;
    }

    public DIR getDir() {
        return DIR.calcDir(this, this.next);
    }
}