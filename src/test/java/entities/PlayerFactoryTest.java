package entities;

import entities.cardEffects.CardEffect;
import entities.cardEffects.HealEffect;
import entities.cards.*;
import entities.decks.EssenceDeck;
import entities.decks.PlayerDeck;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerFactoryTest {

    @Test
    void testCreatePlayer() {
        CreatureCard creature1 = new CreatureCard(1, "some name", 30, 5, 2, 1);
        CreatureCard creature2 = new CreatureCard(2, "some name", 50, 5, 1, 2);
        CreatureCard creature3 = new CreatureCard(3, "some name", 10, 5, 3, 1);
        List<CreatureCard> creatures = new ArrayList<>(List.of(new CreatureCard[]{creature1, creature2, creature3}));

        List<CardEffect> effects1 = new ArrayList<>();
        effects1.add(new HealEffect(10));
        PlayableCardData cardData1 = new PlayableCardData("Some card", TargetType.ANY, effects1);
        Card card1 = new ActionCard(1, "name1", cardData1);
        List<Card> cards = new ArrayList<>(List.of(new Card[]{card1}));
        PlayerDeck playerDeck = new PlayerDeck(cards);

        CardFactory cardFactory = new CardFactory();
        EssenceDeck essenceDeck = new EssenceDeck(cardFactory);

        PlayerFactory playerFactory = new PlayerFactory();
        Player player = playerFactory.createPlayer("Player 1", creatures, playerDeck, essenceDeck);
        assertNotNull(player);
        assertEquals("Player 1", player.getName());
        assertEquals(playerDeck, player.getPlayerDeck());
        assertEquals(essenceDeck, player.getEssenceDeck());
        assertIterableEquals(creatures, player.getCreatures());
    }
}