package use_cases.play_card_use_case;

import entities.GameEvent;
import entities.GameState;
import entities.Player;
import entities.PlayerFactory;
import entities.cardEffects.DamageEffect;
import entities.cardEffects.DrawCardEffect;
import entities.cardEffects.HealthBuffEffect;
import entities.cards.*;
import entities.decks.EssenceDeck;
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

        Player player1 = playerFactory.createPlayer("Kevin", new ArrayList<>(List.of(c1)), null, new EssenceDeck(cardFactory));
        Player player2 = playerFactory.createPlayer("Kevin", new ArrayList<>(List.of(c2)), null, null);
        player1.addCard(card1);
        player1.addCard(card2);
        player1.addCard(card3);
        player1.addCard(card4);

        gameState = new GameState();
        gameState.setPlayers(player1, player2);
    }

    @Test
    void testPlayCard_NotPlayableCard() {
        PlayCardOutputBoundary presenter = new PlayCardOutputBoundary() {
            @Override
            public PlayCardOutputModel displayErrorMessage(String message) {
                assertEquals("This card cannot be played directly.", message);
                return null;
            }

            @Override
            public PlayCardOutputModel showTargetSelectionScreen(TargetModel requestModel) {
                fail("Non-Playable card: playCard() should not call showTargetSelectionScreen()");
                return null;
            }

            @Override
            public PlayCardOutputModel updateGameScreen(PlayCardOutputModel outputData) {
                fail("Non-Playable card: playCard() should not call updateGameScreen()");
                return null;
            }
        };
        PlayCardInputBoundary interactor = new PlayCardInteractor(gameState, presenter);
        interactor.playCard(4);
    }

    @Test
    void testPlayCard_Action_NonSingleTarget() {
        PlayCardOutputBoundary presenter = new PlayCardOutputBoundary() {
            @Override
            public PlayCardOutputModel displayErrorMessage(String message) {
                fail("Valid card: playCard() should not call displayErrorMessage()");
                return null;
            }

            @Override
            public PlayCardOutputModel showTargetSelectionScreen(TargetModel requestModel) {
                fail("No target selection needed.");
                return null;
            }

            @Override
            public PlayCardOutputModel updateGameScreen(PlayCardOutputModel outputData) {
                assertEquals(3, outputData.getPlayHandIds().size());
                assertTrue(outputData.getStructure1().isEmpty());
                assertTrue(outputData.getStructure2().isEmpty());
                assertEquals(List.of(5), outputData.getCreatureIds1());
                assertEquals(List.of(11), outputData.getHitPoints1());
                assertEquals(List.of(1), outputData.getAttacks1());
                assertEquals(List.of(6), outputData.getCreatureIds2());
                assertEquals(List.of(1), outputData.getHitPoints2());
                assertEquals(List.of(1), outputData.getAttacks2());
                return null;
            }
        };
        PlayCardInputBoundary interactor = new PlayCardInteractor(gameState, presenter);
        interactor.playCard(1);
    }

    @Test
    void testPlayCard_Action_DrawCards() {
        PlayableCardData data = new PlayableCardData("", TargetType.SELF, List.of(new DrawCardEffect(2, "ESSENCE_DECK")));
        ActionCard card = new ActionCard(500, "draw", data);
        // Adding a card, now hand has 5 cards
        gameState.getCurrentPlayer().addCard(card);
        PlayCardOutputBoundary presenter = new PlayCardOutputBoundary() {
            @Override
            public PlayCardOutputModel displayErrorMessage(String message) {
                fail("Valid card: playCard() should not call displayErrorMessage()");
                return null;
            }

            @Override
            public PlayCardOutputModel showTargetSelectionScreen(TargetModel requestModel) {
                fail("No target selection needed.");
                return null;
            }

            @Override
            public PlayCardOutputModel updateGameScreen(PlayCardOutputModel outputData) {
                assertEquals(6, outputData.getPlayHandIds().size());
                assertTrue(outputData.getStructure1().isEmpty());
                assertTrue(outputData.getStructure2().isEmpty());
                assertEquals(List.of(5), outputData.getCreatureIds1());
                assertEquals(List.of(1), outputData.getHitPoints1());
                assertEquals(List.of(1), outputData.getAttacks1());
                assertEquals(List.of(6), outputData.getCreatureIds2());
                assertEquals(List.of(1), outputData.getHitPoints2());
                assertEquals(List.of(1), outputData.getAttacks2());
                return null;
            }
        };
        PlayCardInputBoundary interactor = new PlayCardInteractor(gameState, presenter);
        // this action card will be used first, then we draw two cards, expect 6 in the end.
        interactor.playCard(500);
    }

    @Test
    void testPlayCard_Structure() {
        PlayCardOutputBoundary presenter = new PlayCardOutputBoundary() {
            @Override
            public PlayCardOutputModel displayErrorMessage(String message) {
                fail("Valid card: playCard() should not call displayErrorMessage()");
                return null;
            }

            @Override
            public PlayCardOutputModel showTargetSelectionScreen(TargetModel requestModel) {
                fail("No target selection needed.");
                return null;
            }

            @Override
            public PlayCardOutputModel updateGameScreen(PlayCardOutputModel outputData) {
                assertEquals(3, outputData.getPlayHandIds().size());
                assertEquals("Structure1", outputData.getStructure1());
                assertTrue(outputData.getStructure2().isEmpty());
                assertEquals(List.of(5), outputData.getCreatureIds1());
                assertEquals(List.of(1), outputData.getHitPoints1());
                assertEquals(List.of(1), outputData.getAttacks1());
                assertEquals(List.of(6), outputData.getCreatureIds2());
                assertEquals(List.of(1), outputData.getHitPoints2());
                assertEquals(List.of(1), outputData.getAttacks2());
                return null;
            }
        };
        PlayCardInputBoundary interactor = new PlayCardInteractor(gameState, presenter);
        interactor.playCard(3);
    }

    @Test
    void testPlayCard_Action_SingleTarget() {
        PlayCardOutputBoundary presenter = new PlayCardOutputBoundary() {
            @Override
            public PlayCardOutputModel displayErrorMessage(String message) {
                fail("Valid card: playCard() should not call displayErrorMessage()");
                return null;
            }

            @Override
            public PlayCardOutputModel showTargetSelectionScreen(TargetModel requestModel) {
                assertEquals(List.of(5, 6), requestModel.getTargetIds());
                assertEquals(2, requestModel.getCardId());
                return null;
            }

            @Override
            public PlayCardOutputModel updateGameScreen(PlayCardOutputModel outputData) {
                fail("Single Target Card: playCard() should not call updateGameScreen().");
                return null;
            }
        };
        PlayCardInputBoundary interactor = new PlayCardInteractor(gameState, presenter);
        interactor.playCard(2);
    }

    @Test
    void testPlaySingleTargetCard() {
        PlayCardOutputBoundary presenter = new PlayCardOutputBoundary() {
            @Override
            public PlayCardOutputModel displayErrorMessage(String message) {
                fail("Valid card: playCard() should not call displayErrorMessage()");
                return null;
            }

            @Override
            public PlayCardOutputModel showTargetSelectionScreen(TargetModel requestModel) {
                fail("playSingleTargetCard() should not need target selection anymore.");
                return null;
            }

            @Override
            public PlayCardOutputModel updateGameScreen(PlayCardOutputModel outputData) {
                assertEquals(3, outputData.getPlayHandIds().size());
                assertTrue(outputData.getStructure1().isEmpty());
                assertTrue(outputData.getStructure2().isEmpty());
                assertEquals(List.of(5), outputData.getCreatureIds1());
                assertEquals(List.of(-9), outputData.getHitPoints1());
                assertEquals(List.of(1), outputData.getAttacks1());
                assertEquals(List.of(6), outputData.getCreatureIds2());
                assertEquals(List.of(1), outputData.getHitPoints2());
                assertEquals(List.of(1), outputData.getAttacks2());
                return null;
            }
        };
        PlayCardInputBoundary interactor = new PlayCardInteractor(gameState, presenter);
        interactor.playSingleTargetCard(2, 5);
    }
}