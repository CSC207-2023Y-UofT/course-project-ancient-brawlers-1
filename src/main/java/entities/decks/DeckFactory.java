package entities.decks;

import entities.cards.Card;
import entities.cards.CardFactory;

import java.util.List;

/**
 * The DeckFactory class creates the different types of Deck
 */
public class DeckFactory {

    public Deck createPlayerDeck(List<Card> cards) {
        return new PlayerDeck(cards);
    }

    public Deck createEssenceDeck(CardFactory cardFactory) {
        return new EssenceDeck(cardFactory);
    }
}
