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
        switch (getDir()) {
            case UP -> {
                switch (next.getDir()) {
                    case UP -> {
                        return getDir().toString();
                    }
                    case DOWN -> {
                        return "X";
                    }
                    case RIGHT -> {
                        return "F";
                    }
                    case LEFT -> {
                        return "7";
                    }
                }
            }
            case DOWN -> {
                switch (next.getDir()) {
                    case UP -> {
                        return "X";
                    }
                    case DOWN -> {
                        return getDir().toString();
                    }
                    case RIGHT -> {
                        return "L";
                    }
                    case LEFT -> {
                        return "J";
                    }
                }

            }
            case RIGHT -> {
                switch (next.getDir()) {
                    case UP -> {
                        return "J";
                    }
                    case DOWN -> {
                        return "7";
                    }
                    case RIGHT -> {
                        return getDir().toString();
                    }
                    case LEFT -> {
                        return "X";
                    }
                }
            }
            case LEFT -> {
                switch (next.getDir()) {
                    case UP -> {
                        return "L";
                    }
                    case DOWN -> {
                        return "F";
                    }
                    case RIGHT -> {
                        return "X";
                    }
                    case LEFT -> {
                        return getDir().toString();
                    }
                }
            }
        }
        return "?";
    }

    @Override
    public int hashCode() {
        return this.key.hashCode();
    }

    public boolean isCornerTile() {
        return this.getDir() != next.getDir();
    }

    public void setNext(Connector<T> next) {
        this.next = next;
    }

    public DIR getDir() {
        return DIR.calcDir(this.prev, this);
    }

    public DIR getConnectedVerticalDir() {
        if (this.getDir().isHorizontal()) {
            return this.next.getDir();
        }
//        assert !this.getDir().isHorizontal();
        return this.getDir();
    }
}