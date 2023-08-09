package entities.decks;

import entities.cards.Card;

import java.util.List;
import java.util.Random;

public class PlayerDeck implements Deck {

    private final List<Card> cards;

    public PlayerDeck(List<Card> cards) {
        this.cards = cards;
    }

    public void shuffleInto(Card card) {
        Random random = new Random();
        int index = random.nextInt(getDeckSize() + 1);
        cards.add(index, card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getDeckSize() {
        return cards.size();
    }

    public void shuffleDeck() {
        Random random = new Random();

        for (int i = getDeckSize() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            // Swap cards at positions i and j
            Card temp = cards.get(i);
            cards.set(i, cards.get(j));
            cards.set(j, temp);
        }
    }

    @Override
    public Card draw() {
        if (getDeckSize() > 0) {
            return cards.remove(getDeckSize() - 1);
        } else {
            return null;
        }
    }
}
