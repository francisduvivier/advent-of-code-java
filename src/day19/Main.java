package day19;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
    }

    static String solve(String sampleInput) {
        String[] lines = sampleInput.split("\n");
        /**
         *  Plan here:
         *  - First parse lines:
         *    - Xmas object list {sum(), getLetter(), map<String, Long>}
         *    - HashMap from flow name to Rule list, flowMap
         *
         *    sum = 0
         *    For loop through xMas to exec findFlowResultRec, returns false in case of Reject
         *      if(result) sum+= xMas.sum()
         *
         *    findFlowResultRec(flowName):
         *      rules = getFlow(flowName):
     *          for rule in rules:
         *          destination = rule
         *          if(rule.equals("A")){
         *              return true;
         *          }
         *          else if(rule.equals("R")){
         *              return false;
         *          }else if(match "[><]"){
         *           letter, biggerOrSmaller (>=1, <=-1), value, destination
         *           if(xMas.getLetter(letter)*biggerOrSmaller > biggerOrSmaller * value){
         *               destination
         *           }
         *           // Best extract this into tested function checkBiggerSmallerThanRule(xmas, String rule)
         *          }else{
         *              return findFlowResultRec(rule)
         *          }
         *          // TO follow the open closed principle, each of these rules should be some class implementing the boolean exec(xMas, flowMap) interface
         */

        long result = 0;
        // TODO
        return "" + result;
    }

    static String solve2(String sampleInput) {
        String[] lines = sampleInput.split("\n");
        long result = 0;
        // TODO
        return "" + result;
    }

}