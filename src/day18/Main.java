package day18;

import util.DIR;

import java.util.Arrays;
import java.util.List;

import static util.DIR.DIRS;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
    }

    static String solve(String sampleInput) {
        String[] lines = sampleInput.split("\n");
        LoopCompactor runner = new LoopCompactor(Arrays.stream(lines).map(Main::createInstruction1).toList().toArray(new LoopCompactor.INSTRUCT[0]));
        return "" + runner.run();
    }

    public static LoopCompactor.INSTRUCT createInstruction1(String line) {
        var dir = DIR.valueOfLetter(line.split(" ")[0]);
        assert dir != null;
        var amount = Integer.parseInt(line.split(" ")[1]);
        var color = line.split(" ")[2].split("[()#]+")[1];
        LoopCompactor.INSTRUCT result = new LoopCompactor.INSTRUCT(dir, amount, color);
        return result;
    }

    static String solve2(String sampleInput) {
        String[] lines = sampleInput.split("\n");
        List<LoopCompactor.INSTRUCT> instructions = Arrays.stream(lines).map(Main::createInstruction2).toList();
        for (var instruct : instructions) {
            System.out.println(instruct.dir() + ": " + instruct.amount());
        }
        LoopCompactor runner = new LoopCompactor(instructions.toArray(new LoopCompactor.INSTRUCT[0]));
        return "" + runner.run();
    }

    public static LoopCompactor.INSTRUCT createInstruction2(String line) {
        var color = line.split(" ")[2].split("[()#]+")[1];
        var dir = DIRS[Integer.parseInt(color.split("")[color.length() - 1])];
        assert dir != null;
        var amount = Integer.parseInt(color.substring(0, 5), 16);
        LoopCompactor.INSTRUCT result = new LoopCompactor.INSTRUCT(dir, amount, null);
        return result;
    }


}