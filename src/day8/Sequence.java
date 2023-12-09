package day8;

public class Sequence {
    private final long[] start;


    public Sequence(long[] start) {
        this.start = start;
    }

    boolean allSameDiff() {
        // TODO
        return false;
    }

    long getDiff() {
        return this.start[1] - this.start[0];
    }

    long extrapolateRec() {
        // TODO
        return 0;
    }
}