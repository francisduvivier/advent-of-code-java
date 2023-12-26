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
        var flowRules = new ArrayList<>(flowMap.get(flowKey));
        while (!flowRules.isEmpty()) {
            var currRule = flowRules.removeFirst();
            if (currRule.equals("A")) {
                return true;
            }
            if (currRule.equals("R")) {
                return false;
            }
            if (currRule.isDirect()) {
                return getFlowResultRec(xmas, currRule.getRef());
            }
            if (currRule.getCompareResult(xmas)) {
                return getFlowResultRec(xmas, currRule.getRef());
            }
        }
        throw new IllegalStateException("Ended rules without a result");
    }
}