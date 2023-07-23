package use_cases.attack_use_case;

import entities.GameEvent;
import entities.GameState;
import entities.Player;
import entities.cards.Card;
import entities.cards.CreatureCard;
import use_cases.GameOutputBoundary;
import java.util.List;
import java.util.ArrayList;

public class AttackInteractor implements AttackInputBoundary {

    private final GameState gameState;
    private final AttackOutputBoundary attackPresenter;

    public AttackInteractor(GameState gameState, AttackOutputBoundary attackPresenter) {
        this.gameState = gameState;
        this.attackPresenter = attackPresenter;
    }

    @Override
    public void initiateAttack(AttackRequestModel inputData) {

        Player player1 = gameState.getCurrentPlayer();
        Player player2 = gameState.getOpposingPlayer();
        CreatureCard creatureAttacker = player1.getCreatureById(inputData.getAttackerId());
        if (player1.getNumOfEssence() < creatureAttacker.getAttackCost()) {
            attackPresenter.displayFailMessage("You do not have enough essence");
        }
        List<Integer> possibleDefenders = new ArrayList<Integer>();
        for (CreatureCard card: player2.getCreatures()) {
            int cardId = card.getId();
            if (cardId != inputData.getTargetId() & card.getDefendCost() <= player2.getNumOfEssence()) {
                possibleDefenders.add(card.getId());
            }
        }

        AttackResponseModel attackModel = new AttackResponseModel(inputData.getAttackerId(), possibleDefenders, inputData.getTargetId());
        attackPresenter.showDefendInputScreen(attackModel);

    }

    @Override
    public FinishAttackResponseModel defend(AttackRequestModel inputData) {
        Player player1 = gameState.getCurrentPlayer();
        Player player2 = gameState.getOpposingPlayer();
        CreatureCard creatureAttacker = player1.getCreatureById(inputData.getAttackerId());
        CreatureCard creatureDefender = player2.getCreatureById(inputData.getTargetId());

        player1.spendEssence(creatureAttacker.getAttackCost());
        creatureDefender.takeDamage(creatureAttacker.getAttackDamage());


        List <Integer> player1CreatureHitpoints = new ArrayList<>();
        List <Integer> player1CreatureIds= new ArrayList<>();
        List <Integer> player1HandIds = new ArrayList<>();

        List <Integer> player2CreatureIds= new ArrayList<>();
        List <Integer> player2CreatureHitpoints = new ArrayList<>();
        List <Integer> player2HandIds = new ArrayList<>();

        for (int i = 0; i< 3; i++) {
            player1CreatureHitpoints.add(player1.getCreatures().get(i).getHitPoints());
            player1CreatureIds.add(player1.getCreatures().get(i).getId());
            player2CreatureHitpoints.add(player2.getCreatures().get(i).getHitPoints());
            player2CreatureIds.add(player2.getCreatures().get(i).getId());
        }

        for (Card player1Card: player1.getHand()) {
            player1HandIds.add(player1Card.getId());
        }

        for (Card player2Card: player2.getHand()) {
            player2HandIds.add(player2Card.getId());
        }

        FinishAttackResponseModel updatedModel = new FinishAttackResponseModel(player1CreatureHitpoints,
                player2CreatureHitpoints, player1CreatureIds, player2CreatureIds, player1HandIds, player2HandIds);

        return attackPresenter.exitDefendInputScreen(updatedModel);

        return null;
    }
}
