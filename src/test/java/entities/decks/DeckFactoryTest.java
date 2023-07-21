package entities.decks;

import entities.cardEffects.CardEffect;
import entities.cardEffects.DamageModifyEffect;
import entities.cardEffects.HealEffect;
import entities.cardEffects.StunEffect;
import entities.cards.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeckFactoryTest {

    DeckFactory deckFactory;
    CardFactory cardFactory;
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
        cardFactory = new CardFactory();
        deckFactory = new DeckFactory();
    }

    @Test
    void createPlayerDeck() {
        Deck deck = deckFactory.createPlayerDeck(cards);
        assertTrue(deck instanceof PlayerDeck);
        assertEquals(3, ((PlayerDeck) deck).getDeckSize());
    }

    @Test
    void createEssenceDeck() {
        Deck deck = deckFactory.createEssenceDeck(cardFactory);
        assertTrue(deck instanceof EssenceDeck);
        Card card = deck.draw();
        assertEquals(1, card.getId());
    }
}