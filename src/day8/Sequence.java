package day8;

public class Sequence {
    private final long[] start;
    private Sequence diffs;
    private boolean allSameDiffs;


    public Sequence(long[] start) {
        this.start = start;
        long[] diffs = getDiffs();
        this.allSameDiffs = allSameDiffs(diffs);
        if (!this.allSameDiffs) {
            this.diffs = new Sequence(diffs);
        } else {
            this.diffs = null;
        }
    }

    private static boolean allSameDiffs(long[] diffs) {
        for (var diff : diffs) {
            if (diff != diffs[0]) {
                return false;
            }
        }
        return true;
    }

    boolean allSameDiff() {
        var diffs = this.getDiffs();
        return allSameDiffs(diffs);
    }

    long[] getDiffs() {
        long[] result = new long[this.start.length - 1];
        for (int i = 0; i < start.length - 1; i++) {
            result[i] = start[i + 1] - start[i];
        }
        return result;
    }

    long getDiff() {
        return this.start[1] - this.start[0];
    }

    long extrapolateRec() {
        long lastVal = this.start[this.start.length - 1];
        if (this.allSameDiffs) {
            return lastVal + this.getDiff();
        }
        long nextDiff = this.diffs.extrapolateRec();
        return lastVal + nextDiff;
    }
}