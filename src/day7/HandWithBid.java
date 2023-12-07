package day7;

class HandWithBid implements Comparable<HandWithBid> {
    final Hand hand;
    final long bid;

    HandWithBid(String line) {
        String[] lineParts = line.split(" ");
        this.hand = new Hand(lineParts[0]);
        this.bid = Long.parseLong(lineParts[1]);
    }

    long getWinnings(int rank) {
        return rank * this.bid;
    }

    @Override
    public int compareTo(HandWithBid o) {
        return this.hand.compareTo(o.hand);
    }
}