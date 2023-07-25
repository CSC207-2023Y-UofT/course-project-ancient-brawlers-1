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
    void testProcessPlayerInfo_validInputs() {

    }

    @Test
    void testProcessPlayerInfo_invalidInputs() {

    }
}