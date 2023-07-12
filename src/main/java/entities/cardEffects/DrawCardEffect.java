package entities.cardEffects;

import entities.Player;
import entities.decks.Deck;

public class DrawCardEffect implements PlayerStatsEffect {

    private final int drawNumber;
    private final Deck drawDeck;

    public DrawCardEffect(int drawNumber, Deck drawDeck) {
        this.drawNumber = drawNumber;
        this.drawDeck = drawDeck;
    }

    @Override
    public void invokeEffect(Player target) {
        for (int i = 0; i < drawNumber; i++) {
            target.addCard(drawDeck.draw());
        }
    }
}
