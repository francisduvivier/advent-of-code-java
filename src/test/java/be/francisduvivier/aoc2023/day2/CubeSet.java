package be.francisduvivier.aoc2023.day2;

public class CubeSet {
    private CubeSet() {
    }

    public int red;
    public int green;
    public int blue;

    public static CubeSet parseSet(String setString) throws IllegalStateException {
        CubeSet cubeSet = new CubeSet();
        String[] amountsWithColor = setString.split(", ");
        for (String amountWithColorString : amountsWithColor) {
            String[] amountAndColorTuple = amountWithColorString.split(" ");
            int amount = Integer.parseInt(amountAndColorTuple[0]);
            String color = amountAndColorTuple[1];
            switch (color) {
                case "red":
                    cubeSet.red = amount;
                    break;
                case "green":
                    cubeSet.green = amount;
                    break;
                case "blue":
                    cubeSet.blue = amount;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + color);
            }
        }
        return cubeSet;
    }
}
