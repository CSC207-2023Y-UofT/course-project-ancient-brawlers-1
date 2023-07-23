package use_cases.attack_use_case;

import entities.GameEvent;
import entities.GameState;
import entities.Player;
import entities.cards.CreatureCard;
import use_cases.GameOutputBoundary;

public class AttackInteractor implements AttackInputBoundary {
    private final GameState gameState;
    private final AttackOutputBoundary attackPresenter;

    public AttackInteractor(GameState gameState, AttackOutputBoundary attackPresenter) {
        this.gameState = gameState;
        this.attackPresenter = attackPresenter;
    }

    @Override
    public FinishAttackResponseModel initiateAttack(AttackRequestModel inputData) {

        Player player1 = gameState.getCurrentPlayer();
        Player player2 = gameState.getOpposingPlayer();
        CreatureCard creatureAttacker = player1.getCreatureById(inputData.getAttackerId());
        CreatureCard creatureDefender = player2.getCreatureById(inputData.getTargetId());
        if (player1.getNumOfEssence() < creature.getAttackCost()) {
            return attackPresenter.displayFailMessage("You do not have enough essence");
        }

        for (CreatureCard card: player2.getCreatures()){
            int cardId = card.getId();
            if
        }
        return null;
    }

    @Override
    public FinishAttackResponseModel defend(AttackRequestModel inputData) {

        return null;
    }
}
