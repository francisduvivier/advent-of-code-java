package day19;

import java.util.Arrays;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
    }

    /**
     * Plan here:
     * - First parse lines:
     * - Xmas object list {sum(), getLetter(), map<String, Long>}
     * - HashMap from flow name to Rule list, flowMap
     * <p>
     * sum = 0
     * For loop through xMas to exec findFlowResultRec, returns false in case of Reject
     * if(result) sum+= xMas.sum()
     * <p>
     * findFlowResultRec(flowName):
     * rules = getFlow(flowName):
     * for rule in rules:
     * destination = rule
     * if(rule.equals("A")){
     * return true;
     * }
     * else if(rule.equals("R")){
     * return false;
     * }else if(match "[><]"){
     * letter, biggerOrSmaller (>=1, <=-1), value, destination
     * if(xMas.getLetter(letter)*biggerOrSmaller > biggerOrSmaller * value){
     * destination
     * }
     * // Best extract this into tested function checkBiggerSmallerThanRule(xmas, String rule)
     * }else{
     * return findFlowResultRec(rule)
     * }
     * // To follow the open closed principle, each of these rules should be some class implementing the boolean exec(xMas, flowMap) interface
     */
    static String solve(String input) {
        var flowLines = input.split("\n *\n")[0].split("\n");
        var operator = new Operator(flowLines);
        var xMasLines = input.split("\n *\n")[1].split("\n");
        var xMasObjects = Arrays.stream(xMasLines).map(Xmas::parse).toList();
        long result = 0;
        for (var xmas : xMasObjects) {
            if (operator.getFlowResult(xmas)) {
                result += xmas.getSum();
            }
        }
        return "" + result;
    }

    static String solve2(String sampleInput) {
        String[] lines = sampleInput.split("\n");
        long result = 0;
        // TODO
        return "" + result;
    }

}