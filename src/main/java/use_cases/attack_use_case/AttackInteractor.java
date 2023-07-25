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
            attackPresenter.displayFailMessage("You do not have enough essence.");
        }
        else {
            List<Integer> possibleDefenders = new ArrayList<Integer>();
            for (CreatureCard card : player2.getCreatures()) {
                int cardId = card.getId();
                if (cardId != inputData.getTargetId() & card.getDefendCost() <= player2.getNumOfEssence()) {
                    possibleDefenders.add(card.getId());
                }
            }
            AttackResponseModel attackModel = new AttackResponseModel(inputData.getAttackerId(), inputData.getTargetId(), possibleDefenders);
            attackPresenter.showDefendInputScreen(attackModel);
        }

    }

    /**
     * Process the attack using the attacker and target in the AttackRequestModel.
     * The target in this AttackRequestModel must be the final target to receive
     * the attack.
     *
     * @param inputData the AttackRequestModel containing the attacking creature's
     *                  id, and the target creature's id.
     * @return the FinishAttackResponseModel reporting the updates to the stats
     * of creatures and stats of players in the game.
     */
    @Override
    public FinishAttackResponseModel proceedAttack(AttackRequestModel inputData) {
        Player player1 = gameState.getCurrentPlayer();
        Player player2 = gameState.getOpposingPlayer();
        CreatureCard creatureAttacker = player1.getCreatureById(inputData.getAttackerId());
        CreatureCard creatureDefender = player2.getCreatureById(inputData.getTargetId());
        player1.spendEssence(creatureAttacker.getAttackCost());
        return getFinishAttackResponseModel(player1, player2, creatureAttacker, creatureDefender);

    }

    @Override
    public FinishAttackResponseModel defend(AttackRequestModel inputData) {
        Player player1 = gameState.getCurrentPlayer();
        Player player2 = gameState.getOpposingPlayer();
        CreatureCard creatureAttacker = player1.getCreatureById(inputData.getAttackerId());
        CreatureCard creatureDefender = player2.getCreatureById(inputData.getTargetId());

        player1.spendEssence(creatureAttacker.getAttackCost());
        player2.spendEssence(creatureDefender.getDefendCost());
        return getFinishAttackResponseModel(player1, player2, creatureAttacker, creatureDefender);

    }

    private FinishAttackResponseModel getFinishAttackResponseModel(Player player1, Player player2, CreatureCard
            creatureAttacker, CreatureCard creatureDefender) {

        creatureDefender.takeDamage(creatureAttacker.getAttackDamage());

        List<List<Integer>> player1HitIdHand= getPlayerHitCreatureHandID(player1);
        List<List<Integer>> player2HitIdHand= getPlayerHitCreatureHandID(player2);

        List<Integer> player1CreatureHitpoints = player1HitIdHand.get(0);
        List<Integer> player2CreatureHitpoints = player2HitIdHand.get(0);
        List<Integer>  player1CreatureIds = player1HitIdHand.get(1);
        List<Integer> player2CreatureIds = player1HitIdHand.get(1);
        List <Integer> player1HandIds = player1HitIdHand.get(2);
        List <Integer> player2HandIds = player1HitIdHand.get(2);

        FinishAttackResponseModel updatedModel = new FinishAttackResponseModel(player1CreatureHitpoints,
                player2CreatureHitpoints, player1CreatureIds, player2CreatureIds, player1HandIds, player2HandIds);

        return attackPresenter.exitDefendInputScreen(updatedModel);
    }

}
