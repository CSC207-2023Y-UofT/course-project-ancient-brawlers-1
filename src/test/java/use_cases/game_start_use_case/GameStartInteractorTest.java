package use_cases.game_start_use_case;

import entities.GameState;
import entities.Player;
import entities.cardEffects.*;
import entities.cards.*;
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
        List<CardEffect> effects1 = new ArrayList<>(List.of(new CardEffect[]{new HealEffect(10)}));
        PlayableCardData cardData1 = new PlayableCardData("Some card", TargetType.ANY, effects1);
        Card card1 = new ActionCard(1, "name1", cardData1);

        List<CardEffect> effects2 = new ArrayList<>(List.of(new CardEffect[]{new DamageModifyEffect(-3)}));
        PlayableCardData cardData2 = new PlayableCardData("Another card", TargetType.ENEMY, effects2);
        Card card2 = new ActionCard(2, "name2", cardData2);

        List<CardEffect> effects3 = new ArrayList<>(List.of(new CardEffect[]{new StunEffect()}));
        PlayableCardData cardData3 = new PlayableCardData("Freeze!", TargetType.SINGLE_ENEMY, effects3);
        Card card3 = new ActionCard(3, "name3", cardData3);

        List<CardEffect> effects4 = new ArrayList<>(List.of(new CardEffect[]{new DestroyStructureEffect()}));
        PlayableCardData cardData4 = new PlayableCardData("Destroy!", TargetType.OPPONENT, effects4);
        Card card4 = new ActionCard(4, "name4", cardData4);

        PlayerDeck playerDeck1 = new PlayerDeck(new ArrayList<>(List.of(new Card[]{card1, card2, card3, card4})));
        PlayerDeck playerDeck2 = new PlayerDeck(new ArrayList<>(List.of(new Card[]{card4, card3, card2, card1})));

        Player player1 = new Player("Player 1", null, playerDeck1, null);
        Player player2 = new Player("Player 2", null, playerDeck2, null);

        gameState = new GameState();
        gameState.setPlayers(player1, player2);
    }

    @Test
    void testDecidePlayOrder() {
        // ...
    }

    @Test
    void testPrepareMulligan() {
        GameStartOutputBoundary presenter = new GameStartOutputBoundary() {
            @Override
            public GameStartResponseModel showMulliganScreen(GameStartResponseModel outputData) {
                List<Integer> ids = new ArrayList<>(List.of(new Integer[]{4, 3, 2}));
                assertIterableEquals(ids, outputData.getCardIds());
                assertNull(outputData.getBonusCardIds());
                return null;
            }

            @Override
            public GameStartResponseModel exitMulliganScreen(GameStartResponseModel outputData) {
                fail("prepareMulligan() should not call exitMulliganScreen().");
                return null;
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
                return null;
            }
        };

        interactor = new GameStartInteractor(gameState, presenter);

        List<Integer> ids = new ArrayList<>(List.of(new Integer[]{4, 2}));
        GameStartRequestModel inputData = new GameStartRequestModel(ids);

        interactor.processMulligan(inputData);
    }
}