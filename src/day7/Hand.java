package day7;

class Hand implements Comparable<Hand> {
    private static char[] order = new char[]{'A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2'};
    private final String characters;

    Hand(String hand) {
        this.characters = hand;
    }

    String getCharacters() {
        return this.characters;
    }

    Rank getRANK() {
        Rank[] ranks = Rank.values();
        for (int i = 0; i < ranks.length; i++) {
            var rank = ranks[ranks.length - 1 - i];
            if (rank.matches(this)) {
                return rank;
            }
        }
        throw new Error("Could not find matching rank");
    }

    int getSecondaryRANK() {
        int rank = 0;
        for (var character : this.characters.toCharArray()) {
            int charOrder = 0;
            while (character != order[charOrder++]) {
            }
            rank *= order.length;
            rank += order.length - charOrder;
        }
        return rank;
    }

    @Override
    public int compareTo(Hand o) {
        if (getRANK() == o.getRANK()) {
            return getSecondaryRANK() - o.getSecondaryRANK();
        }
        return getRANK().ordinal() - o.getRANK().ordinal();
    }
}