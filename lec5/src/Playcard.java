import java.util.*;

public class Playcard implements Comparable<Playcard> {
    @Override
    public int compareTo(Playcard o) {
        return this.rank - o.rank;
    }

    public enum Suit {Hearts, Spades, Diamonds, Clubs}
    final private Integer rank;
    final private Suit suit;

    public Playcard(Integer rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    @Override
    public String toString() {
        String result = "";
        if(suit == Suit.Hearts || suit == Suit.Diamonds)
            result += "\033[0;31m";
        switch (rank) {
            case 11 -> result += "J";
            case 12 -> result += "Q";
            case 13 -> result += "K";
            case 14 -> result += "A";
            default -> result += rank;
        }
        switch(suit) {
            case Hearts -> result += "♥";
            case Spades -> result += "♠";
            case Diamonds -> result += "♦";
            case Clubs -> result += "♣";
        }
        result += "\033[0m";
        return result;
    }

    static public List<Playcard> deck() {
        List<Playcard> result = new LinkedList<>();
        for(var suit: Suit.values())
            for(int rank=2; rank<=14; ++rank)
                result.add(new Playcard(rank, suit));
        return result;
    }

    static public List<Playcard> shuffle(List<Playcard> deck) {
        Collections.shuffle(deck, new Random());
        return deck;
    }

    public Integer getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }
}