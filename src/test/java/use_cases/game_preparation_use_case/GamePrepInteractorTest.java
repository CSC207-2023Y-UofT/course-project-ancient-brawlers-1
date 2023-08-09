package use_cases.game_preparation_use_case;

import entities.GameState;
import entities.PlayerFactory;
import entities.cards.CardFactory;
import entities.decks.DeckFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GamePrepInteractorTest {

    GameState gameState;
    CardFactory cardFactory;
    DeckFactory deckFactory;
    PlayerFactory playerFactory;
    CardDataGateway dataAccessor;

    @BeforeEach
    void setUp() {
        gameState = new GameState();
        cardFactory = new CardFactory();
        deckFactory = new DeckFactory();
        playerFactory = new PlayerFactory();
        dataAccessor = new TestDataAccessor();
    }

    @Test
    void testPromptPlayerInfo() {
        // For why we've chosen the expected values in the assert statements,
        // refer to TestDataAccessor for the mock data.
        GamePrepOutputBoundary presenter = new GamePrepOutputBoundary() {
            @Override
            public void showGamePreparationScreen(List<String> creatureNames) {
                assertEquals(List.of("C1", "C2", "C3", "C4"), creatureNames,
                        "List of creature names inconsistent with mock data.");
            }

            @Override
            public GamePrepResponseModel displayErrorMessage(String message) {
                fail("promptPlayerInfo() should not call displayErrorMessage().");
                return null;
            }

            @Override
            public GamePrepResponseModel showGameplayScreen(GamePrepResponseModel outputData) {
                fail("promptPlayerInfo() should not call showGameplayScreen().");
                return null;
            }
        };
        GamePrepInteractor interactor = new GamePrepInteractor(gameState, cardFactory, deckFactory,
                playerFactory, dataAccessor, presenter);

        interactor.promptPlayerInfo();
    }

    @Test
    void testProcessPlayerInfo_ValidInputs() {
        GamePrepOutputBoundary presenter = new GamePrepOutputBoundary() {
            @Override
            public void showGamePreparationScreen(List<String> creatureNames) {
                fail("processPlayerInfo() should not call showGamePreparationScreen().");
            }

            @Override
            public GamePrepResponseModel displayErrorMessage(String message) {
                fail("On valid inputs, processPlayerInfo() should not call displayErrorMessage().");
                return null;
            }

            @Override
            public GamePrepResponseModel showGameplayScreen(GamePrepResponseModel outputData) {
                assertEquals("Kevin", outputData.getPlayerName1());
                assertEquals("Unknown", outputData.getPlayerName2());
                assertEquals(3, outputData.getCreatureIds1().size());
                assertEquals(3, outputData.getCreatureIds2().size());
                assertEquals(3, outputData.getAttacks1().size());
                assertEquals(3, outputData.getAttacks2().size());
                assertEquals(3, outputData.getHitPoints1().size());
                assertEquals(3, outputData.getHitPoints2().size());
                return null;
            }
        };
        GamePrepRequestModel inputData = new GamePrepRequestModel("Kevin", "Unknown",
                List.of("C1", "C2", "C3"), List.of("C2", "C3", "C4"));

        GamePrepInteractor interactor = new GamePrepInteractor(gameState, cardFactory, deckFactory,
                playerFactory, dataAccessor, presenter);
        interactor.processPlayerInfo(inputData);
    }

    @Test
    void testProcessPlayerInfo_InvalidInputs() {
        GamePrepOutputBoundary presenter = new GamePrepOutputBoundary() {
            @Override
            public void showGamePreparationScreen(List<String> creatureNames) {
                fail("processPlayerInfo() should not call showGamePreparationScreen().");
            }

            @Override
            public GamePrepResponseModel displayErrorMessage(String message) {
                assertEquals("Please select only 3 creatures.", message);
                return null;
            }

            @Override
            public GamePrepResponseModel showGameplayScreen(GamePrepResponseModel outputData) {
                fail("On invalid inputs, processPlayerInfo() should not call showGameplayScreen().");
                return null;
            }
        };
        GamePrepRequestModel inputData = new GamePrepRequestModel("Kevin", "J",
                List.of("C1", "C2", "C3", "C4"), List.of("C1", "C2", "C3", "C4"));

        GamePrepInteractor interactor = new GamePrepInteractor(gameState, cardFactory, deckFactory,
                playerFactory, dataAccessor, presenter);
        interactor.processPlayerInfo(inputData);
    }
}