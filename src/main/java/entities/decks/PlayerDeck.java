package entities.decks;

import entities.cards.Card;

import java.util.List;
import java.util.Random;

/**
 * PlayerDeck represents the deck of cards given to a player during a game.
 * The deck contains 30 cards (by the current rule).
 * Cards can be shuffled into the deck, or drawn from the top of the deck.
 */
public class PlayerDeck implements Deck {

    private final List<Card> cards;

    /**
     * Constructs a PlayerDeck instance using the given list of Card instances.
     *
     * @param cards the cards to be put into this deck.
     */
    public PlayerDeck(List<Card> cards) {
        this.cards = cards;
    }

    /**
     * Put a card at a random position in the deck.
     *
     * @param card the card to be shuffled into the deck.
     */
    public void shuffleInto(Card card) {
        Random random = new Random();
        int index = random.nextInt(getDeckSize() + 1);
        cards.add(index, card);
    }

    public List<Card> getCards() {
        return cards;
    }

    /**
     * Get the remaining cards in the deck.
     *
     * @return the number of remaining cards.
     */
    public int getDeckSize() {
        return cards.size();
    }

    /**
     * Randomly shuffle the cards in the deck.
     */
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

    /**
     * Draw the top card from the deck.
     *
     * @return a Card instance.
     */
    @Override
    public Card draw() {
        if (getDeckSize() > 0) {
            return cards.remove(getDeckSize() - 1);
        } else {
            return null;
        }
    }
}
