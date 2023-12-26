package day19;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    boolean getFlowResultRec(Xmas input, String flowKey) {
        var flowRules = flowMap.get(flowKey);
        // TODO implement
        return false;
    }
}