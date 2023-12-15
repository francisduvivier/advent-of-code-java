package day15;

public class Runner {

    private long total;

    Runner() {
    }

    char run(String s) {
        short result = 0;
        for (char charVal : s.toCharArray()) {
            result += charVal;
            result *= 17;
            result %= 256;
        }
        total += result;
        return (char) result;
    }

    public long getTotal() {
        return total;
    }
}