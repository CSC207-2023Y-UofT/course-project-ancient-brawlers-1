package entities.decks;

import entities.cards.Card;
import entities.cards.CardFactory;

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
