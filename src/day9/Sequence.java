package day9;

import java.util.Arrays;

public class Sequence {
    final long[] start;
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

    public static Sequence parse(String line) {
        String[] numberStrings = line.split(" +");

        long[] numbers = Arrays.stream(numberStrings).mapToLong(Long::parseLong).toArray();
        return new Sequence(numbers); //TODO
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

    long extrapolateRightRec() {
        long lastVal = this.start[this.start.length - 1];
        if (this.allSameDiffs) {
            return lastVal + this.getDiff();
        }
        long nextDiff = this.diffs.extrapolateRightRec();
        return lastVal + nextDiff;
    }

    public long extrapolateLeftRec() {
        long firstVal = this.start[0];
        if (this.allSameDiffs) {
            return firstVal - this.getDiff();
        }
        long nextDiff = this.diffs.extrapolateLeftRec();
        return firstVal - nextDiff;
    }
}