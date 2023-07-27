package entities;

import entities.cardEffects.*;
import entities.cards.*;
import entities.decks.EssenceDeck;
import entities.decks.PlayerDeck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    Player player;
    List<CreatureCard> creatures;
    PlayerDeck playerDeck;
    EssenceDeck essenceDeck;

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
        playerDeck = new PlayerDeck(cards);
        essenceDeck = new EssenceDeck(new CardFactory());

        CreatureCard creature1 = new CreatureCard(1, "some name", 30, 5, 2, 1);
        CreatureCard creature2 = new CreatureCard(2, "some name", 50, 5, 1, 2);
        CreatureCard creature3 = new CreatureCard(3, "some name", 10, 5, 3, 1);
        creatures = new ArrayList<>(List.of(new CreatureCard[]{creature1, creature2, creature3}));

        player = new Player("Player 1", creatures, playerDeck, essenceDeck);
    }

    @Test
    void testGetters() {
        assertEquals("Player 1", player.getName());
        assertEquals(0, player.getHandSize());
        assertEquals(creatures, player.getCreatures());
        assertEquals(0, player.getNumOfEssence());
        assertEquals(playerDeck, player.getPlayerDeck());
        assertEquals(essenceDeck, player.getEssenceDeck());
    }
    @Test
    void testStructure() {
        assertNull(player.getStructure());

        PlayableCardData data = new PlayableCardData("", TargetType.ANY, null);
        StructureCard structure = new StructureCard(1, "name", data, GameEvent.TURN_END);
        player.setStructure(structure);
        assertEquals(structure, player.getStructure());
    }

    @Test
    void testAddCard() {
        assertTrue(player.addCard(player.getPlayerDeck().draw()));
        assertEquals(1, player.getHandSize());
        assertTrue(player.addCard(player.getEssenceDeck().draw()));
        assertEquals(2, player.getHandSize());
    }

    @Test
    void testAddCard_full() {
        for (int i = 0; i < 10; i++) {
            assertTrue(player.addCard(player.getEssenceDeck().draw()));
        }
        assertEquals(10, player.getHandSize());
        assertFalse(player.addCard(player.getEssenceDeck().draw()),
                "Should return false if the player's hand is full.");
    }

    @Test
    void testPlayCard() {
        Card card = player.getPlayerDeck().draw();
        assertTrue(player.addCard(card));
        Card playedCard = player.playCard(card.getId());
        assertEquals(card, playedCard);
        assertEquals(card.getId(), playedCard.getId());
        assertEquals(null, player.playCard(5));
    }

    @Test
    void testRemoveCreature() {
        player.removeCreature(1);
        assertEquals(2, player.getCreatures().size());
        assertEquals(false, player.removeCreature(10));
        for (CreatureCard creature : player.getCreatures()) {
            assertNotEquals(1, creature.getId());
        }
    }

    @Test
    void testGetHandCapacity() {
        assertEquals(10, player.getHandCapacity());
    }

    @Test
    void testSpendEssence() {
        assertEquals(false, player.spendEssence(10));
    }

    @Test
    void testGetCreatureById() {
        assertEquals(null, player.getCreatureById(4));
        CreatureCard creature1 = creatures.get(0);
        assertEquals(creature1, player.getCreatureById(1));
        assertNotEquals(creature1, player.getCreatureById(2));
    }

    @Test
    void testGetCardById() {
        assertEquals(null, player.getCardById(10));
    }
}