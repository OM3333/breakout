import java.util.*;
import java.util.stream.Collectors;

public class Hand {
    List<Playcard> cards = new ArrayList<>();
    void addCard(Playcard playcard) {
        cards.add(playcard);
    }

    @Override
    public String toString() {
        return "Hand{" +
                "cards=" + cards +
                '}';
    }

    public void sort(Playcard.Suit trump) {
        Collections.sort(cards, ((o1, o2) -> {
            Map<Playcard.Suit, Integer> weights = new HashMap<>();
            weights.put(Playcard.Suit.Spades, 1);
            weights.put(Playcard.Suit.Hearts, 1);
            weights.put(Playcard.Suit.Diamonds, 1);
            weights.put(Playcard.Suit.Clubs, 1);

            if(trump != null)
                weights.put(trump, 0);

            Integer w1 = weights.get(o1.getSuit());
            Integer w2 = weights.get(o2.getSuit());

            if(w1.equals(w2))
                return o2.compareTo(o1);
            return w1-w2;
        }));
        //Collections.sort(cards);
    }

    public void receive(Set<Playcard> cards) {
        this.cards.addAll(cards);
    }

    public void give(Hand recipient, int n) {
        Playcard.Suit trump = Playcard.Suit.Clubs;
        Set<Playcard> worstCards = cards.stream()
                .filter(o -> o.getSuit() != trump)
                .sorted((o1, o2) -> o1.getRank() - o2.getRank())
                .limit(n)
                .collect(Collectors.toSet());
        System.out.println(worstCards);
        cards.removeAll(worstCards);
        recipient.receive(worstCards);
    }
}
