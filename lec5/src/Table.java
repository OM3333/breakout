import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class Table {
    Map<String, Hand> players = new HashMap<>();
    Queue<Playcard> deck;

    public void setDeck(Queue<Playcard> deck) {
        this.deck = deck;
    }

    public void addPlayer(String name) {
        players.put(name, new Hand());
    }

    public void takeCards(int n) {
        for(int i=0; i<n; ++i) {
            for (Map.Entry<String, Hand> entry : players.entrySet()) {
                Playcard card = deck.remove();
                System.out.println(entry.getKey() + " takes " + card);
                entry.getValue().addCard(card);
            }
        }
    }

    @Override
    public String toString() {
        return "Table{" +
                "players=" + players +
                '}';
    }

    Hand getHand(String name) {
        return players.get(name);
    }

}
