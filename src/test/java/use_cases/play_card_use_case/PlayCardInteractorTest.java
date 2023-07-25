package use_cases.play_card_use_case;

import entities.GameEvent;
import entities.GameState;
import entities.Player;
import entities.PlayerFactory;
import entities.cardEffects.DamageEffect;
import entities.cardEffects.HealthBuffEffect;
import entities.cards.*;
import entities.decks.DeckFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayCardInteractorTest {

    GameState gameState;

    @BeforeEach
    void setUp() {
        CardFactory cardFactory = new CardFactory();
        PlayerFactory playerFactory = new PlayerFactory();
        PlayableCardData data1 = new PlayableCardData("", TargetType.FRIENDLY, List.of(new HealthBuffEffect(10)));
        PlayableCardData data2 = new PlayableCardData("", TargetType.ANY, List.of(new DamageEffect(10)));
        Card card1 = cardFactory.createActionCard("Action1", data1);
        Card card2 = cardFactory.createActionCard("Action2", data2);
        Card card3 = cardFactory.createStructureCard("Structure1", data1, GameEvent.TURN_END);
        Card card4 = cardFactory.createEssenceCard();
        CreatureCard c1 = (CreatureCard) cardFactory.createCreatureCard("C1", 1, 1, 1, 1);
        CreatureCard c2 = (CreatureCard) cardFactory.createCreatureCard("C2", 1, 1, 1, 1);
        // NOTE: the ids of the cards should be:
        // card1 = 1, card2 = 2, card3 = 3, card4 = 4, c1 = 5, c2 = 6
        // (these are expected values in test methods)

        Player player1 = playerFactory.createPlayer("Kevin", new ArrayList<>(List.of(c1)), null, null);
        Player player2 = playerFactory.createPlayer("Kevin", new ArrayList<>(List.of(c2)), null, null);
        player1.addCard(card1);
        player1.addCard(card2);
        player1.addCard(card3);
        player1.addCard(card4);

        gameState = new GameState();
        gameState.setPlayers(player1, player2);
    }

    /**
     * Test that when playing an action card with no target input needed,
     * the interactor will go straight to updateStats() in presenter.
     *
     * In this test, processCard() will call playCard() too.
     */
    @Test
    void testProcessAndPlay_ActionCard() {
        PlayCardOutputBoundary presenter = new PlayCardOutputBoundary() {
            @Override
            public void requestTarget(TargetModel requestModel) {
                fail("No target input needed: processCard() does not need to call requestTarget().");
            }

            @Override
            public PlayCardOutputModel updateStats(PlayCardOutputModel outputData) {
                assertEquals(3, outputData.getPlayHandIds().size());
                assertEquals(List.of(5), outputData.getCreatureIds1());
                assertEquals(List.of(11), outputData.getHitPoints1());
                assertEquals(List.of(1), outputData.getAttacks1());
                assertEquals(List.of(6), outputData.getCreatureIds2());
                assertEquals(List.of(1), outputData.getHitPoints2());
                assertEquals(List.of(1), outputData.getAttacks2());
                return null;
            }

            @Override
            public void displayErrorMessage(String message) {
                fail("The input should be processed successfully.");
            }
        };
        PlayCardInputBoundary interactor = new PlayCardInteractor(gameState, presenter);
        interactor.processCard(1);
    }

    /**
     * Test that when playing an action that requires a single target, the
     * processCard() method does not go to playCard() but submits the request
     * for targets.
     */
    @Test
    void testProcessCard_ActionCard_NeedTarget() {
        PlayCardOutputBoundary presenter = new PlayCardOutputBoundary() {
            @Override
            public void requestTarget(TargetModel requestModel) {
                assertEquals(2, requestModel.getCardId());
                assertEquals(List.of(4, 5), requestModel.getTargetIds(),
                        "We'll just keep it consistent that we add current player's " +
                                "creature ids first, followed by opposing player's.");
            }

            @Override
            public PlayCardOutputModel updateStats(PlayCardOutputModel outputData) {
                fail("Target input needed: processCard() should not call updateStats().");
                return null;
            }

            @Override
            public void displayErrorMessage(String message) {
                fail("The input should be processed successfully.");
            }
        };
        PlayCardInputBoundary interactor = new PlayCardInteractor(gameState, presenter);
        interactor.processCard(2);
    }

    /**
     * Testing for a structure card, this should follow through with both
     * processCard() and playCard().
     */
    @Test
    void testProcessAndPlay_StructureCard() {
        PlayCardOutputBoundary presenter = new PlayCardOutputBoundary() {
            @Override
            public void requestTarget(TargetModel requestModel) {
                fail("No need to request targets.");
            }

            @Override
            public PlayCardOutputModel updateStats(PlayCardOutputModel outputData) {
                assertEquals("Structure1", outputData.getStructure1());
                assertTrue(outputData.getStructure2().isEmpty());
                return null;
            }

            @Override
            public void displayErrorMessage(String message) {
                fail("The input should be processed successfully.");
            }
        };
        PlayCardInputBoundary interactor = new PlayCardInteractor(gameState, presenter);
        interactor.processCard(3);
    }

    @Test
    void testProcessCard_EssenceCard() {
        PlayCardOutputBoundary presenter = new PlayCardOutputBoundary() {
            @Override
            public void requestTarget(TargetModel requestModel) {
                fail("Invalid card to play: No need to request targets.");
            }

            @Override
            public PlayCardOutputModel updateStats(PlayCardOutputModel outputData) {
                fail("Invalid card to play: No need to update stats.");
                return null;
            }

            @Override
            public void displayErrorMessage(String message) {
                assertEquals("Essence card cannot be played directly.", message);
            }
        };
        PlayCardInputBoundary interactor = new PlayCardInteractor(gameState, presenter);
        interactor.processCard(4);
    }
}