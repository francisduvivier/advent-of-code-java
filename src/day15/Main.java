package day15;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
    }

    static String solve(String sampleInput) {
        String[] lines = sampleInput.split("\n");
        var runner = new Hasher();
        for (var part : lines[0].split(",")) {
            runner.hash(part);
        }
        return "" + runner.getTotal();
    }

    static String solve2(String sampleInput) {
        String[] lines = sampleInput.split("\n");
        // SO we need to have a list of 256 box elements
        // Then every box should have a list containing lenses
        // lens objects have a label and a number
        // we need to be able to look up lenses by their label so we can remove them from the list
        // Box is decided by hash algo from day 1
        long result = 0;
        List<String>[] boxes = new ArrayList[256];
        HashMap<String, Short> lensMap = new HashMap<>();
        for (int i = 0; i < 256; i++) {
            boxes[i] = new ArrayList<>();
        }
        var hasher = new Hasher();
        for (var part : lines[0].split(",")) {
            String[] stepSplit = part.split("[-=]");
            var label = stepSplit[0];
            int boxIndex = hasher.hash(part);
            var lenses = boxes[boxIndex];

            if (part.contains("-")) {
                lenses.removeIf(l -> l.equals(label));
            } else {
                short focal = Short.parseShort(stepSplit[1]);
                lensMap.put(label, focal);
                if (lenses.stream().noneMatch(l -> l.equals(label))) {
                    lenses.add(label);
                }
            }
        }
        long focusingPower = 0;
        int b = 0;
        for (var box : boxes) {
            int l = 0;
            for (var lens : box) {
                focusingPower += (long) (1 + b) * (l + 1) * lensMap.get(lens);
                l++;
            }
            b++;
        }
        return "" + focusingPower;
    }

}