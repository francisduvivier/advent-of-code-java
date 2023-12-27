package day19;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Xmas {
    private final Map<String, Long> values;

    public Xmas(Map<String, Long> values) {
        this.values = values;
    }

    static Xmas parse(String line) {
        Map<String, Long> lineValues = new HashMap<>();
        var splitResult = line.split("[^0-9xmas]+");
        String currLetter = null;
        for (var letterOrNumber : splitResult) {
            if (letterOrNumber.isEmpty()) {
                continue;
            }
            if (currLetter == null) {
                currLetter = letterOrNumber;
            } else {
                lineValues.put(currLetter, Long.parseLong(letterOrNumber));
                currLetter = null;
            }
        }
        return new Xmas(lineValues);
    }

    Long getLetter(String letter) {
        return values.get(letter);
    }

    Long getSum() {
        var sum = 0L;
        for (var value : values.values()) {
            sum += value;
        }
        return sum;
    }

    public Set<String> keySet() {
        return this.values.keySet();
    }

    public Xmas createChanged(String letter, long newValue) {
        HashMap<String, Long> valuesClone = new HashMap<>(values);
        valuesClone.put(letter, newValue);
        return new Xmas(valuesClone);
    }
}