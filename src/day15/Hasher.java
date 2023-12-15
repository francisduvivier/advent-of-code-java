package day15;

public class Hasher {

    private long total;

    Hasher() {
    }

    char hash(String s) {
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