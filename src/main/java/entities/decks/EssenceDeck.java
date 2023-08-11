package entities.decks;

import entities.cards.Card;
import entities.cards.CardFactory;

/**
 * EssenceDeck contains unlimited number of EssenceCards for the players to draw.
 * Thus it takes the CardFactory so that drawing an Essence is handled by creating one.
 */
public class EssenceDeck implements Deck {

    private final CardFactory cardFactory;

    public EssenceDeck(CardFactory cardFactory) {
        this.cardFactory = cardFactory;
    }

    @Override
    public Card draw() {
        return cardFactory.createEssenceCard();
    }
}
