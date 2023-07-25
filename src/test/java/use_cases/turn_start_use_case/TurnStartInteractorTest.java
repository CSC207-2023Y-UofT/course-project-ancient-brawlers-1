package use_cases.turn_start_use_case;

import entities.GameState;
import entities.Player;
import entities.PlayerFactory;
import entities.cardEffects.CardEffect;
import entities.cardEffects.HealEffect;
import entities.cards.*;
import entities.decks.Deck;
import entities.decks.DeckFactory;
import entities.decks.EssenceDeck;
import entities.decks.PlayerDeck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TurnStartInteractorTest {

    GameState gameState;

    @BeforeEach
    void setUp() {
        CardFactory cardFactory = new CardFactory();
        PlayerFactory playerFactory = new PlayerFactory();
        DeckFactory deckFactory = new DeckFactory();

        List<CardEffect> effects = new ArrayList<>(List.of(new HealEffect(10)));
        PlayableCardData cardData = new PlayableCardData("Some card", TargetType.ANY, effects);
        Card card1 = cardFactory.createActionCard("name1", cardData);

        Deck playerDeck1 = deckFactory.createPlayerDeck(new ArrayList<>(List.of(card1, card1, card1)));
        Deck playerDeck2 = deckFactory.createPlayerDeck(new ArrayList<>(List.of(card1, card1, card1)));
        Deck essenceDeck1 = deckFactory.createEssenceDeck(cardFactory);
        Deck essenceDeck2 = deckFactory.createEssenceDeck(cardFactory);

        CreatureCard creature1 = (CreatureCard) cardFactory.createCreatureCard("Name1", 1, 1, 1, 1);
        CreatureCard creature2 = (CreatureCard) cardFactory.createCreatureCard("Name2", 1, 1, 1, 1);

        Player player1 = playerFactory.createPlayer("Player 1", new ArrayList<>(List.of(creature1)),
                (PlayerDeck) playerDeck1, (EssenceDeck) essenceDeck1);
        Player player2 = playerFactory.createPlayer("Player 2", new ArrayList<>(List.of(creature2)),
                (PlayerDeck) playerDeck2, (EssenceDeck) essenceDeck2);

        gameState = new GameState();
        gameState.setPlayers(player1, player2);
    }

    @Test
    void testDrawCards() {
        TurnStartOutputBoundary presenter = new TurnStartOutputBoundary() {
            @Override
            public TurnStartResponseModel updateScreen(TurnStartResponseModel outputData) {
                assertEquals(4, outputData.getHandIds1().size(),
                        "Player 1 should have 4 cards.");
                assertNull(outputData.getHandIds2(),
                        "Player 2 should not have cards.");
                assertNull(outputData.getAttacks1(),
                        "For drawCard(), there shouldn't be any input in the other attributes.");
                return null;
            }
        };
        TurnStartInteractor interactor = new TurnStartInteractor(gameState, presenter);
        interactor.drawCards();
    }

    @Test
    void testClearTemporaryEffects() {
        // The current player's creatures are given a health buff of 5,
        // We expect the buff to be cleared when this method is run.
        Player player = gameState.getCurrentPlayer();
        for (CreatureCard c : player.getCreatures()) {
            c.addHealthBuff(5);
        }
        TurnStartOutputBoundary presenter = new TurnStartOutputBoundary() {
            @Override
            public TurnStartResponseModel updateScreen(TurnStartResponseModel outputData) {
                assertEquals(List.of(1), outputData.getHitPoints1());
                return null;
            }
        };
        TurnStartInteractor interactor = new TurnStartInteractor(gameState, presenter);
        interactor.clearTemporaryEffects();
    }

    @Test
    void testTriggerTurnStartEffects() {

    }
}