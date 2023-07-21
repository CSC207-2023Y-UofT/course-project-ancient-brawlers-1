package entities.cardEffects;

import entities.Player;
import entities.cards.*;
import entities.decks.EssenceDeck;
import entities.decks.PlayerDeck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DrawCardEffectTest {

    Player player;

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

        List<Card> cards = new ArrayList<>(List.of(new Card[]{card1, card2, card3}));
        PlayerDeck playerDeck = new PlayerDeck(cards);
        EssenceDeck essenceDeck = new EssenceDeck(new CardFactory());

        player = new Player("Player 1", null, playerDeck, essenceDeck);
    }

    @Test
    void testInvokeEffect_playerDeck() {
        DrawCardEffect effect = new DrawCardEffect(2, "PLAYER_DECK");
        effect.invokeEffect(player);
        assertEquals(2, player.getHandSize());
    }

    @Test
    void testInvokeEffect_essenceDeck() {
        DrawCardEffect effect = new DrawCardEffect(2, "ESSENCE_DECK");
        effect.invokeEffect(player);
        assertEquals(2, player.getHandSize());

        List<Card> hand = player.getHand();
        for (Card c : hand) {
            assertTrue(c instanceof EssenceCard);
        }
    }
}