package entities.decks;

import entities.cards.Card;
import entities.cards.CardFactory;

/**
 * EssenceDeck contains unlimited number of EssenceCards for the players to draw.
 * Thus, it takes the CardFactory so that drawing an Essence is simply to create
 * a new EssenceCard.
 */
public class EssenceDeck implements Deck {

    private final CardFactory cardFactory;

    public EssenceDeck(CardFactory cardFactory) {
        this.cardFactory = cardFactory;
    }

    /**
     * Draw the top card from the deck.
     *
     * @return a Card instance.
     */
    @Override
    public Card draw() {
        return cardFactory.createEssenceCard();
    }
}
