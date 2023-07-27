package use_cases.game_start_use_case;

import entities.GameState;
import entities.Player;
import entities.PlayerFactory;
import entities.cardEffects.*;
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

class GameStartInteractorTest {

    GameState gameState;
    GameStartInputBoundary interactor;

    @BeforeEach
    void setUp() {
        CardFactory cardFactory = new CardFactory();
        PlayerFactory playerFactory = new PlayerFactory();
        DeckFactory deckFactory = new DeckFactory();
        List<CardEffect> effects;
        PlayableCardData cardData;

        // We are using the CardFactory to create these cards.
        // The CardFactory is correct, so we expect the card ids to be 1, 2, 3, 4.
        // Later on, if any essence cards are being drawn from the essence deck,
        // its id should be 5.
        effects = new ArrayList<>(List.of(new HealEffect(10)));
        cardData = new PlayableCardData("Some card", TargetType.ANY, effects);
        Card card1 = cardFactory.createActionCard("name1", cardData);

        effects = new ArrayList<>(List.of(new DamageModifyEffect(-3)));
        cardData = new PlayableCardData("Another card", TargetType.ENEMY, effects);
        Card card2 = cardFactory.createActionCard("name2", cardData);

        effects = new ArrayList<>(List.of(new StunEffect()));
        cardData = new PlayableCardData("Freeze!", TargetType.SINGLE_ENEMY, effects);
        Card card3 = cardFactory.createActionCard("name3", cardData);

        effects = new ArrayList<>(List.of(new DestroyStructureEffect()));
        cardData = new PlayableCardData("Destroy!", TargetType.OPPONENT, effects);
        Card card4 = cardFactory.createActionCard("name4", cardData);

        Deck playerDeck1 = deckFactory.createPlayerDeck(new ArrayList<>(List.of(card1, card2, card3, card4)));
        Deck playerDeck2 = deckFactory.createPlayerDeck(new ArrayList<>(List.of(card4, card3, card2, card1)));
        Deck essenceDeck1 = deckFactory.createEssenceDeck(cardFactory);
        Deck essenceDeck2 = deckFactory.createEssenceDeck(cardFactory);

        Player player1 = playerFactory.createPlayer("Player 1", null,
                (PlayerDeck) playerDeck1, (EssenceDeck) essenceDeck1);
        Player player2 = playerFactory.createPlayer("Player 2", null,
                (PlayerDeck) playerDeck2, (EssenceDeck) essenceDeck2);

        gameState = new GameState();
        gameState.setPlayers(player1, player2);
    }

    @Test
    void testDecidePlayOrder() {
        GameStartOutputBoundary presenter = new GameStartOutputBoundary() {
            @Override
            public GameStartResponseModel showMulliganScreen(GameStartResponseModel outputData) {
                fail("decidePlayOrder() should not call showMulliganScreen().");
                return null;
            }

            @Override
            public GameStartResponseModel exitMulliganScreen(GameStartResponseModel outputData) {
                fail("decidePlayOrder() should not call exitMulliganScreen().");
                return null;
            }

            @Override
            public void displayPlayerOrder(String message) {
                assertTrue(message.equals("Player 1 goes first!") || message.equals("Player 2 goes first!"));
            }
        };

        interactor = new GameStartInteractor(gameState, presenter);
        interactor.decidePlayOrder();
    }

    @Test
    void testPrepareMulligan() {
        GameStartOutputBoundary presenter = new GameStartOutputBoundary() {
            @Override
            public GameStartResponseModel showMulliganScreen(GameStartResponseModel outputData) {
                List<Integer> ids = new ArrayList<>(List.of(4, 3, 2));
                List<String> names = new ArrayList<>(List.of("name4", "name3", "name2"));
                assertIterableEquals(ids, outputData.getCardIds());
                assertIterableEquals(names, outputData.getCardNames());
                assertEquals(0, outputData.getBonusCardIds().size());
                return null;
            }

            @Override
            public GameStartResponseModel exitMulliganScreen(GameStartResponseModel outputData) {
                fail("prepareMulligan() should not call exitMulliganScreen().");
                return null;
            }

            @Override
            public void displayPlayerOrder(String message) {
                fail("prepareMulligan() should not call displayPlayerOrder().");
            }
        };

        interactor = new GameStartInteractor(gameState, presenter);

        interactor.prepareMulligan();
    }

    @Test
    void testProcessMulligan() {
        // Manually let the player draw 3 cards
        // (this would be done by prepareMulligan())
        Player player1 = gameState.getCurrentPlayer();
        PlayerDeck deck1 = player1.getPlayerDeck();
        for (int i = 0; i < 3; i++) {
            player1.addCard(deck1.draw());
        }

        GameStartOutputBoundary presenter = new GameStartOutputBoundary() {
            @Override
            public GameStartResponseModel showMulliganScreen(GameStartResponseModel outputData) {
                fail("processMulligan() should not call showMulliganScreen().");
                return null;
            }

            @Override
            public GameStartResponseModel exitMulliganScreen(GameStartResponseModel outputData) {
                assertEquals(3, outputData.getCardIds().size());
                assertEquals(0, outputData.getBonusCardIds().size(),
                        "There should not be bonus cards for the first player.");
                return null;
            }

            @Override
            public void displayPlayerOrder(String message) {
                fail("prepareMulligan() should not call displayPlayerOrder().");
            }
        };

        interactor = new GameStartInteractor(gameState, presenter);

        List<Integer> ids = new ArrayList<>(List.of(4, 2));
        List<String> names = new ArrayList<>(List.of("name4", "name2"));
        GameStartRequestModel inputData = new GameStartRequestModel(ids, names);

        interactor.processMulligan(inputData);
    }

    @Test
    void testProcessMulligan_SecondPlayer() {
        // Manually switch the turn to the second player, and prepare the hand
        // just like the first player. But now, we expect the output data to
        // include an extra Essence card.
        gameState.switchPlayer();
        Player player2 = gameState.getCurrentPlayer();
        PlayerDeck deck2 = player2.getPlayerDeck();
        for (int i = 0; i < 3; i++) {
            player2.addCard(deck2.draw());
        }

        GameStartOutputBoundary presenter = new GameStartOutputBoundary() {
            @Override
            public GameStartResponseModel showMulliganScreen(GameStartResponseModel outputData) {
                fail("processMulligan() should not call showMulliganScreen().");
                return null;
            }

            @Override
            public GameStartResponseModel exitMulliganScreen(GameStartResponseModel outputData) {
                assertEquals(3, outputData.getCardIds().size());
                assertTrue(outputData.getCardIds().contains(3),
                        "The card with id 3 should still be in the hand.");
                assertEquals(1, outputData.getBonusCardIds().size(),
                        "There should be one bonus Essence for the second player.");
                assertTrue(outputData.getBonusCardNames().contains("Essence"),
                        "The bonus card should be Essence.");
                return null;
            }

            @Override
            public void displayPlayerOrder(String message) {
                fail("prepareMulligan() should not call displayPlayerOrder().");
            }
        };

        interactor = new GameStartInteractor(gameState, presenter);

        // Second player's deck order is 1, 2, 3, 4 (starting from the top of the deck).
        // so the initial hand should be 1 2 3, different from the test case above.
        List<Integer> ids = new ArrayList<>(List.of(1, 2));
        List<String> names = new ArrayList<>(List.of("name1", "name2"));
        GameStartRequestModel inputData = new GameStartRequestModel(ids, names);

        interactor.processMulligan(inputData);
    }
}