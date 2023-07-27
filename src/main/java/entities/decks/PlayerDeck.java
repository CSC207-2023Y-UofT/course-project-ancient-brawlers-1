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

    @Override
    public Card draw() {
        if (getDeckSize() > 0) {
            return cards.remove(getDeckSize() - 1);
        } else {
            return null;
        }
    }
}
