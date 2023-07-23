package use_cases.attack_use_case;

import entities.GameState;
import entities.Player;
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
    AttackInteractor interactor;

    @BeforeEach
    void setUp() {

        CreatureCard creature1 = new CreatureCard(11, "some name", 30, 5, 2, 1);
        CreatureCard creature2 = new CreatureCard(12, "some name", 50, 5, 1, 2);
        CreatureCard creature3 = new CreatureCard(13, "some name", 10, 5, 3, 1);
        CreatureCard creature4 = new CreatureCard(14, "some name", 30, 5, 2, 1);
        CreatureCard creature5 = new CreatureCard(15, "some name", 50, 5, 1, 2);
        CreatureCard creature6 = new CreatureCard(16, "some name", 10, 5, 3, 1);
        List<CreatureCard> creatures1 = new ArrayList<>(List.of(new CreatureCard[]{creature1, creature2, creature3}));
        List<CreatureCard> creatures2 = new ArrayList<>(List.of(new CreatureCard[]{creature4, creature5, creature6}));

        CardFactory cardFactory = new CardFactory();
        Player player1 = new Player("Player 1", creatures1, null, new EssenceDeck(cardFactory));
        Player player2 = new Player("Player 2", creatures2, null, new EssenceDeck(cardFactory));

        GameState gameState = new GameState();
        gameState.setPlayers(player1, player2);
        for (int i = 0; i < 3; i++) {
            player1.addCard(player1.getEssenceDeck().draw());
            player2.addCard(player2.getEssenceDeck().draw());
        }

    }

    @Test
    void testInitiateAttack_And_Defend_ValidAttacker() {
        AttackOutputBoundary attackPresenter = new AttackOutputBoundary() {
            @Override
            public void showDefendInputScreen(AttackResponseModel responseModel) {
                assertEquals(11, responseModel.getAttackerId());
                List<Integer> ids = new ArrayList<>(List.of(new Integer[]{14, 15}));
                assertIterableEquals(ids, responseModel.getDefenderIds());

            }

            @Override
            public FinishAttackResponseModel exitDefendInputScreen(FinishAttackResponseModel responseModel) {
                List<Integer> hitPoints1 = new ArrayList<>(List.of(new Integer[]{30, 50, 10}));
                List<Integer> hitPoints2 = new ArrayList<>(List.of(new Integer[]{30, 45, 10}));
                assertIterableEquals(hitPoints1, responseModel.getHitPoints1());
                assertIterableEquals(hitPoints2, responseModel.getHitPoints2());

                List<Integer> creatureIds1 = new ArrayList<>(List.of(new Integer[]{11, 12, 13}));
                List<Integer> creatureIds2 = new ArrayList<>(List.of(new Integer[]{14, 15, 16}));
                assertIterableEquals(creatureIds1, responseModel.getCreatureIds1());
                assertIterableEquals(creatureIds2, responseModel.getCreatureIds2());

                List<Integer> handCardsIds1 = new ArrayList<>(List.of(new Integer[]{5}));
                List<Integer> handCardsIds2 = new ArrayList<>(List.of(new Integer[]{6}));
                assertIterableEquals(handCardsIds1, responseModel.getCreatureIds1());
                assertIterableEquals(handCardsIds2, responseModel.getCreatureIds2());
                return null;
            }

            @Override
            public void displayFailMessage(String message) {
                fail("Unexpected use case test fail.");
            }
        };

        AttackRequestModel requestModel = new AttackRequestModel(1, 6);
        interactor.initiateAttack(requestModel);

        AttackRequestModel requestModel2 = new AttackRequestModel(1, 5);
        interactor.defend(requestModel2);
    }


}