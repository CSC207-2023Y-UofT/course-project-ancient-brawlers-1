package use_cases.attack_use_case;

import entities.GameState;
import entities.Player;
import entities.PlayerFactory;
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

class AttackInteractorTest {

    GameState gameState;
    AttackInteractor interactor;

    @BeforeEach
    void setUp() {
        CardFactory cardFactory = new CardFactory();
        PlayerFactory playerFactory = new PlayerFactory();

        List<CreatureCard> creatures1 = new ArrayList<>();
        creatures1.add((CreatureCard) cardFactory.createCreatureCard("Creature1", 30, 5, 2, 1));
        creatures1.add((CreatureCard) cardFactory.createCreatureCard("Creature2", 50, 5, 1, 2));
        creatures1.add((CreatureCard) cardFactory.createCreatureCard("Creature3", 10, 5, 3, 1));
        List<CreatureCard> creatures2 = new ArrayList<>();
        creatures2.add((CreatureCard) cardFactory.createCreatureCard("Creature4", 30, 5, 2, 1));
        creatures2.add((CreatureCard) cardFactory.createCreatureCard("Creature5", 50, 5, 1, 2));
        creatures2.add((CreatureCard) cardFactory.createCreatureCard("Creature6", 10, 5, 3, 1));

        Player player1 = playerFactory.createPlayer("Player 1", creatures1, null, new EssenceDeck(cardFactory));
        Player player2 = playerFactory.createPlayer("Player 2", creatures2, null, new EssenceDeck(cardFactory));

        gameState = new GameState();
        gameState.setPlayers(player1, player2);
    }

    @Test
    void testInitiateAttack_ValidAttacker() {
        // Give each player 2 Essence so that the attack and defend can be made.
        Player player = gameState.getCurrentPlayer();
        Player player2 = gameState.getOpposingPlayer();
        player.addCard(player.getEssenceDeck().draw());
        player.addCard(player.getEssenceDeck().draw());
        player2.addCard(player2.getEssenceDeck().draw());
        player2.addCard(player2.getEssenceDeck().draw());

        AttackOutputBoundary presenter = new AttackOutputBoundary() {
            @Override
            public void showDefendInputScreen(AttackResponseModel responseModel) {
                assertEquals(1, responseModel.getAttackerId());
                assertEquals(6, responseModel.getTargetId());
                responseModel.setTargetId(5);
                assertEquals(5, responseModel.getTargetId(), "it should have a new target Id");
                assertEquals(List.of(4, 5), responseModel.getDefenderIds());
                responseModel.setAttackerId(3);
                assertEquals(3, responseModel.getAttackerId(), "it should show a new attacker id");
                List<Integer> newDefenderId = List.of(10, 11, 12);
                responseModel.setDefenderIds(newDefenderId);
                assertEquals(List.of(10, 11, 12), responseModel.getDefenderIds(), "should have a new set of " +
                        "defender Id");
            }

            @Override
            public FinishAttackResponseModel exitDefendInputScreen(FinishAttackResponseModel responseModel) {
                fail("initiateAttack() should not call exitDefendInputScreen().");
                return null;
            }

            @Override
            public void displayFailMessage(String message) {
                fail("Valid attacker case: initiateAttack() should not call displayFailMessage().");
            }
        };

        AttackRequestModel inputData = new AttackRequestModel(1, 6);

        interactor = new AttackInteractor(gameState, presenter);
        interactor.initiateAttack(inputData);
    }

    @Test
    void testIntiateAttack_InvalidAttacker() {
        // Players do not have essence. Choosing any attacker should not work.
        AttackOutputBoundary presenter = new AttackOutputBoundary() {
            @Override
            public void showDefendInputScreen(AttackResponseModel responseModel) {
                fail("Invalid attacker case: initiateAttack() should not call showDefendInputScreen().");
            }

            @Override
            public FinishAttackResponseModel exitDefendInputScreen(FinishAttackResponseModel responseModel) {
                fail("Invalid attacker case: initiateAttack() should not call exitDefendInputScreen().");
                return null;
            }

            @Override
            public void displayFailMessage(String message) {
                assertEquals("You do not have enough essence.", message);
            }
        };

        AttackRequestModel inputData = new AttackRequestModel(1, 6);

        interactor = new AttackInteractor(gameState, presenter);
        interactor.initiateAttack(inputData);
    }

    @Test
    void testProceedAttack() {
        // The 2 essence in player's hand should be taken away after the attack.
        Player player = gameState.getCurrentPlayer();
        player.addCard(player.getEssenceDeck().draw());
        player.addCard(player.getEssenceDeck().draw());

        AttackOutputBoundary presenter = new AttackOutputBoundary() {
            @Override
            public void showDefendInputScreen(AttackResponseModel responseModel) {
                fail("proceedAttack() should not call showDefendInputScreen().");
            }

            @Override
            public FinishAttackResponseModel exitDefendInputScreen(FinishAttackResponseModel responseModel) {
                assertEquals(0, responseModel.getHandCardsIds1().size(),
                        "Player 1 who made the attack should no longer have essence.");
                assertEquals(List.of(30, 50, 10), responseModel.getHitPoints1(),
                        "Player 1's creatures should not take damage.");
                assertEquals(List.of(30, 50, 5), responseModel.getHitPoints2(),
                        "Creature6 was attacked, it is the only one to decrease hit points.");
                assertEquals(List.of(1, 2, 3), responseModel.getCreatureIds1());
                assertEquals(List.of(4, 5, 6), responseModel.getCreatureIds2());
                return null;
            }

            @Override
            public void displayFailMessage(String message) {
                fail("proceedAttack() should not call displayFailMessage().");
            }
        };

        // In this case, this input data is confirmed by the second player.
        // All that needs to happen now is to process the attack, let it go through.
        AttackRequestModel inputData = new AttackRequestModel(1, 6);

        interactor = new AttackInteractor(gameState, presenter);
        interactor.proceedAttack(inputData);
    }

    @Test
    void testDefend() {
        Player player = gameState.getCurrentPlayer();
        Player player2 = gameState.getOpposingPlayer();
        player.addCard(player.getEssenceDeck().draw());
        player.addCard(player.getEssenceDeck().draw());
        player2.addCard(player2.getEssenceDeck().draw());
        player2.addCard(player2.getEssenceDeck().draw());

        AttackOutputBoundary presenter = new AttackOutputBoundary() {
            @Override
            public void showDefendInputScreen(AttackResponseModel responseModel) {
                fail("defend() should not call showDefendInputScreen().");
            }

            @Override
            public FinishAttackResponseModel exitDefendInputScreen(FinishAttackResponseModel responseModel) {
                assertEquals(0, responseModel.getHandCardsIds1().size(),
                        "Attacking player should no longer have essence.");
                assertEquals(0, responseModel.getHandCardsIds2().size(),
                        "Defending player should also no longer have essence.");
                assertEquals(List.of(30, 50, 10), responseModel.getHitPoints1(),
                        "Attacking player's creatures should not lose health.");
                assertEquals(List.of(30, 45, 10), responseModel.getHitPoints2(),
                        "Defender creatures should be the only one to lose health.");
                return null;
            }

            @Override
            public void displayFailMessage(String message) {
                fail("defend() should not call displayFailMessage().");
            }
        };

        // This time, Creature5 is the defender, the cost is 2.
        // Both players should have spent all their essence in hand.
        AttackRequestModel inputData = new AttackRequestModel(1, 5);
        interactor = new AttackInteractor(gameState, presenter);
        interactor.defend(inputData);
    }

    @Test
    void testRequestModel(){
        AttackRequestModel inputData = new AttackRequestModel(1, 6);
        assertEquals(1, inputData.getAttackerId());
        assertEquals(6, inputData.getTargetId());
        inputData.setAttackerId(2);
        assertEquals(2, inputData.getAttackerId(), "attacker id should be 2 after updating");
        inputData.setTargetId(5);
        assertEquals(5, inputData.getTargetId(), "target id should be 5 after updating");
    }

}