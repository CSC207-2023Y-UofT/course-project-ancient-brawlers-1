package entities;

import entities.cardEffects.CardEffect;
import entities.cardEffects.DamageModifyEffect;
import entities.cardEffects.HealEffect;
import entities.cardEffects.StunEffect;
import entities.cards.*;
import entities.decks.EssenceDeck;
import entities.decks.PlayerDeck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {

    GameState gameState;
    Player player1;
    Player player2;
    PlayerDeck playerDeck1;
    PlayerDeck playerDeck2;

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
        playerDeck1 = new PlayerDeck(cards);
        playerDeck2 = new PlayerDeck(cards);

        CardFactory cardFactory = new CardFactory();

        CreatureCard creature1 = new CreatureCard(1, "some name", 30, 5, 2, 1);
        CreatureCard creature2 = new CreatureCard(2, "some name", 50, 5, 1, 2);
        CreatureCard creature3 = new CreatureCard(3, "some name", 10, 5, 3, 1);
        List<CreatureCard> creatures1 = new ArrayList<>(List.of(new CreatureCard[]{creature1, creature2, creature3}));
        List<CreatureCard> creatures2 = new ArrayList<>(List.of(new CreatureCard[]{creature1, creature2, creature3}));

        player1 = new Player("Player 1", creatures1, playerDeck1, new EssenceDeck(cardFactory));
        player2 = new Player("Player 2", creatures2, playerDeck2, new EssenceDeck(cardFactory));

        gameState = new GameState();
    }

    @Test
    void testSetAndGetPlayers() {
        gameState.setPlayers(player1, player2);
        assertEquals(0, gameState.getCurrentPlayerIndex());
        assertEquals(player1, gameState.getCurrentPlayer());
        assertEquals(player2, gameState.getOpposingPlayer());
    }

    @Test
    void testSwitchPlayer() {
        gameState.setPlayers(player1, player2);
        assertEquals(0, gameState.getCurrentPlayerIndex());
        assertEquals(player1, gameState.getCurrentPlayer());
        gameState.switchPlayer();
        assertEquals(1, gameState.getCurrentPlayerIndex());
        assertEquals(player2, gameState.getCurrentPlayer());
    }

    @Test
    void testFlipPlayOrder() {
        gameState.setPlayers(player1, player2);
        gameState.flipPlayOrder();
        // the first player to go becomes player 2, yet the index is still on 0
        // this way, whenever we see index being 0, it means this is the first player,
        // and we can give any bonuses for the second player to go.
        assertEquals(0, gameState.getCurrentPlayerIndex());
        assertEquals(player2, gameState.getCurrentPlayer());
    }
}