package entities.cardEffects;

import entities.Player;
import entities.decks.Deck;

public class DrawCardEffect implements PlayerStatsEffect {

    private final int drawNumber;
    private final String deckType;

    public DrawCardEffect(int drawNumber, String deckType) {
        this.drawNumber = drawNumber;
        this.deckType = deckType;
    }

    @Override
    public void invokeEffect(Player target) {
        Deck drawDeck;
        if (deckType.equals("PLAYER_DECK")) {
            drawDeck = target.getPlayerDeck();
        } else {
            drawDeck = target.getEssenceDeck();
        }

        for (int i = 0; i < drawNumber; i++) {
            target.addCard(drawDeck.draw());
        }
    }
}
