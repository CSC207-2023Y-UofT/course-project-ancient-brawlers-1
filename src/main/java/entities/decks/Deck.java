package entities.decks;

import entities.cards.Card;

/**
 * The Deck interface is used so that different types of Decks are able to draw cards
 */
public interface Deck {

    /**
     * Draw the top card from the deck.
     *
     * @return a Card instance.
     */
    Card draw();
}
