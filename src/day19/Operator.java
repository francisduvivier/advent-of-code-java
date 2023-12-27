package day19;

import java.util.*;

public class Operator {
    Map<String, List<Rule>> flowMap = new HashMap<>();

    Operator(String[] flowLines) {
        for (var line : flowLines) {
            addFlowLine(line);
        }
    }

    private void addFlowLine(String flowLine) {
        var flowLineParts = flowLine.split("[{}]");
        var flowKey = flowLineParts[0];
        var flowValue = flowLineParts[1];
        var rules = Arrays.stream(flowValue.split(",")).map(Rule::parseRule).toList();
        this.flowMap.put(flowKey, rules);
    }

    boolean getFlowResult(Xmas input) {
        return getFlowResultRec(input, "in");
    }

    //        destination = rule
//            * if(rule.equals("A")){
//     * return true;
//     * }
//     * else if(rule.equals("R")){
//     * return false;
//     * }else if(match "[><]"){
//     * letter, biggerOrSmaller (>=1, <=-1), value, destination
//                * if(xMas.getLetter(letter)*biggerOrSmaller > biggerOrSmaller * value){
//     * destination
//                    * }
//     * // Best extract this into tested function checkBiggerSmallerThanRule(xmas, String rule)
//     * }else{
//     * return findFlowResultRec(rule)
//                * }
    boolean getFlowResultRec(Xmas xmas, String flowKey) {
        if (flowKey.equals("A")) {
            return true;
        }
        if (flowKey.equals("R")) {
            return false;
        }
        var flowRules = new ArrayList<>(flowMap.get(flowKey));
        while (!flowRules.isEmpty()) {
            var currRule = flowRules.removeFirst();
            if (currRule.isDirect()) {
                return getFlowResultRec(xmas, currRule.getRef());
            }
            if (currRule.getCompareResult(xmas)) {
                return getFlowResultRec(xmas, currRule.getRef());
            }
        }
        throw new IllegalStateException("Ended rules without a result");
    }

    public long getAmountOfAcceptedFlowsRec(XmasRange xmasRange, String flowKey) {
        long multiplication = xmasRange.getMultiplication();
        if (multiplication == 0) {
            return 0;
        }
        if (flowKey.equals("A")) {
            return multiplication;
        }
        if (flowKey.equals("R")) {
            return 0;
        }
        var flowRules = new ArrayList<>(flowMap.get(flowKey));
        var sum = 0L;
        while (!flowRules.isEmpty()) {
            var currRule = flowRules.removeFirst();
            if (currRule.isDirect()) {
                return sum + getAmountOfAcceptedFlowsRec(xmasRange, currRule.getRef());
            }
            List<XmasRange> narrowedRange = currRule.getRangeSplit(xmasRange);
            var trueRange = narrowedRange.getFirst();
            sum += getAmountOfAcceptedFlowsRec(trueRange, currRule.getRef());
            xmasRange = narrowedRange.getLast(); // False Range
        }
        return sum;
    }

    public Long getAmountOfAcceptedFlows() {
        XmasRange xmasRange = new XmasRange(Xmas.parse("{x=1,m=1,a=1,s=1}"), Xmas.parse("{x=4000,m=4000,a=4000,s=4000}"));
        return getAmountOfAcceptedFlowsRec(xmasRange, "in");
    }
}