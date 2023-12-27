package day19;

public class XmasRange {
    final Xmas startIncl;
    final Xmas endIncl;

    XmasRange(Xmas startIncl, Xmas endIncl) {
        this.startIncl = startIncl;
        this.endIncl = endIncl;
    }

    public long getMultiplication() {
        long result = 1;
        for (var key : startIncl.keySet()) {
            var start = startIncl.getLetter(key);
            var end = endIncl.getLetter(key);
            var diff = Math.max(0, end - start + 1);
            if (diff == 0) {
                return 0;
            }
            result *= diff;
        }
        return result;
    }

    public XmasRange createWithChangedStart(String letter, long newStart) {
        return new XmasRange(startIncl.createChanged(letter, newStart), endIncl);
    }
    public XmasRange createWithChangedEnd(String letter, long newEnd) {
        return new XmasRange(startIncl, endIncl.createChanged(letter, newEnd));
    }
}