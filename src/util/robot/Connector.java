package util.robot;

import util.DIR;
import util.VTile;

public class Connector<T> extends VTile<T> {

    public Connector<T> prev;
    private Connector<T> next;

    public Connector(int row, int col, T value, Connector<T> prev) {
        super(row, col, value);
        this.prev = prev;
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
        if (this.value == null) {
            return ".";
        } else return this.value.toString().split("")[0];
    }

    @Override
    public int hashCode() {
        return this.key.hashCode();
    }

    public boolean isCornerTile() {
        return DIR.calcDir(this.next, this) != DIR.calcDir(this, this.prev);
    }

    public void setNext(Connector<T> next) {
        this.next = next;
    }
}