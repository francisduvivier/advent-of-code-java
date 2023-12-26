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
}