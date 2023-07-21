package entities.decks;

import entities.cards.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EssenceDeckTest {

    @Test
    void testDraw() {
        CardFactory cardFactory = new CardFactory();
        EssenceDeck essenceDeck = new EssenceDeck(cardFactory);

        Card card1 = essenceDeck.draw();
        Card card2 = essenceDeck.draw();

        assertEquals(1, card1.getId());
        assertEquals(2, card2.getId());
        assertTrue(card1 instanceof EssenceCard);
        assertTrue(card2 instanceof EssenceCard);
    }
}