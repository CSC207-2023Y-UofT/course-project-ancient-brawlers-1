package entities.decks;

import entities.cardEffects.*;
import entities.cards.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PlayerDeckTest {

    PlayerDeck playerDeck;
    List<Card> cards;

    @BeforeEach
    void setUp() {
        List<CardEffect> effects1 = new ArrayList<>();
        effects1.add(new HealEffect(10));
        PlayableCardData cardData1 = new PlayableCardData("Some card", TargetType.ANY, effects1);
        Card card1 = new ActionCard(1, "name1", cardData1);

        List<CardEffect> effects2 = new ArrayList<>();
        effects2.add(new DamageModifyEffect(-3));
        PlayableCardData cardData2 = new PlayableCardData("Another card", TargetType.ENEMY, effects2);
        Card card2 = new ActionCard(2, "name2", cardData2);

        List<CardEffect> effects3 = new ArrayList<>();
        effects3.add(new StunEffect());
        PlayableCardData cardData3 = new PlayableCardData("Freeze!", TargetType.SINGLE_ENEMY, effects3);
        Card card3 = new ActionCard(3, "name3", cardData3);

        cards = new ArrayList<>(List.of(new Card[]{card1, card2, card3}));
        playerDeck = new PlayerDeck(cards);
    }

    @Test
    void testGetDeckSize() {
        assertEquals(3, playerDeck.getDeckSize());
    }

    @Test
    void testDraw() {
        Card card = playerDeck.draw();
        assertEquals(3, card.getId(), "Drawn card should be the top card (last item in list)");
        assertEquals(2, playerDeck.getDeckSize());
    }

    @Test
    void testGetCards() {
        List<Card> deckCards = playerDeck.getCards();
        assertIterableEquals(cards, deckCards);
    }

    @Test
    void testShuffleInto() {
        Card card = new EssenceCard(4);
        playerDeck.shuffleInto(card);
        assertEquals(4, playerDeck.getDeckSize());
    }
}