package day19;

public class Rule {
    private final String ruleString;

    public Rule(String ruleString) {
        this.ruleString = ruleString;
    }

    static Rule parseRule(String ruleString) {
        return new Rule(ruleString);
    }

    String[] getParts() {
        return this.ruleString.split("[:<>]");
    }

    boolean isDirect() {
        return getParts().length == 1;
    }

    boolean isBiggerThan() {
        return !isDirect() && this.ruleString.contains(">");
    }

    String getRef() {
        return this.getParts()[this.getParts().length - 1];
    }

    public boolean getCompareResult(Xmas xmas) {
        assert !this.isDirect();

        var parts = getParts();
        var number = Long.parseLong(parts[1]);
        var letterVal = xmas.getLetter(parts[0]);
        int multiplier = isBiggerThan() ? 1 : -1;
        if (letterVal * multiplier > number * multiplier) {
            return true;
        } else {
            return false;
        }
    }

    public XmasRange narrowRange(XmasRange xmasRange) {
        assert !this.isDirect();
        var parts = getParts();
        var compareNumber = Long.parseLong(parts[1]);
        String letter = parts[0];
        if (isBiggerThan()) {
            return xmasRange.createWithChangedStart(letter, compareNumber + 1);
        } else {
            return xmasRange.createWithChangedEnd(letter, compareNumber - 1);
        }
    }
}