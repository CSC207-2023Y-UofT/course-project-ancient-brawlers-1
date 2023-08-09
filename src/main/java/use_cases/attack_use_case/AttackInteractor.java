package use_cases.attack_use_case;

import entities.GameState;
import entities.Player;
import entities.cards.*;
import use_cases.CreatureCardModel;

import java.util.ArrayList;
import java.util.List;

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
        CreatureCard attacker = player1.getCreatureById(inputData.getAttackerId());
        CreatureCard target = player2.getCreatureById(inputData.getTargetId());
        if (attacker == null || target == null) {
            attackPresenter.displayFailMessage("Invalid Attacker/Target.");
            return;
        } else if (player1.getNumOfEssence() < attacker.getAttackCost()) {
            attackPresenter.displayFailMessage("You do not have enough essence.");
            return;
        }

        CreatureCardModel attackerData = new CreatureCardModel(attacker.getName(), attacker.getAttackCost(),
                attacker.getDefendCost(), attacker.getTotalAttackDamage(), attacker.getTotalHitPoints());
        CreatureCardModel targetData = new CreatureCardModel(target.getName(), target.getAttackCost(),
                target.getDefendCost(), target.getTotalAttackDamage(), target.getTotalHitPoints());

        List<Integer> defenderIds = new ArrayList<>();
        List<CreatureCardModel> defenderData = new ArrayList<>();
        for (CreatureCard card : player2.getCreatures()) {
            if (card.getId() != target.getId() && card.getDefendCost() <= player2.getNumOfEssence()) {
                defenderIds.add(card.getId());
                defenderData.add(new CreatureCardModel(card.getName(), card.getAttackCost(), card.getDefendCost(),
                        card.getTotalAttackDamage(), card.getTotalHitPoints()));
            }
        }
        AttackResponseModel attackModel = new AttackResponseModel(inputData.getAttackerId(), attackerData, inputData.getTargetId(), targetData, defenderIds, defenderData);
        attackPresenter.showDefendInputScreen(attackModel);
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

    private FinishAttackResponseModel getFinishAttackResponseModel(Player player1, Player player2,
                                                                   CreatureCard creatureAttacker,
                                                                   CreatureCard creatureDefender) {
        creatureDefender.takeDamage(creatureAttacker.getAttackDamage());

        List<Integer> hitPoints1 = new ArrayList<>();
        List<Integer> hitPoints2 = new ArrayList<>();
        List<Integer> creatureIds1 = new ArrayList<>();
        List<Integer> creatureIds2 = new ArrayList<>();
        List<Integer> handIds1 = new ArrayList<>();
        List<Integer> handIds2 = new ArrayList<>();
        List<String> handCardDescription1 = new ArrayList<>();
        List<String> handCardDescription2 = new ArrayList<>();

        for (CreatureCard creature : player1.getCreatures()) {
            hitPoints1.add(creature.getTotalHitPoints());
            creatureIds1.add(creature.getId());
        }

        for (CreatureCard creature : player2.getCreatures()) {
            hitPoints2.add(creature.getTotalHitPoints());
            creatureIds2.add(creature.getId());
        }

        for (Card card : player1.getHand()) {
            handIds1.add(card.getId());

            if (card instanceof ActionCard) {
                handCardDescription1.add(((ActionCard) card).getDescription());
            } else if (card instanceof StructureCard) {
                handCardDescription1.add(((StructureCard) card).getDescription());
            } else {
                handCardDescription1.add("Essence");
            }

        }

        for (Card card : player2.getHand()) {
            handIds2.add(card.getId());
            if (card instanceof ActionCard) {
                handCardDescription2.add(((ActionCard) card).getDescription());
            } else if (card instanceof StructureCard) {
                handCardDescription2.add(((StructureCard) card).getDescription());
            } else {
                handCardDescription2.add("Essence");
            }
        }

        FinishAttackResponseModel updatedModel = new FinishAttackResponseModel(hitPoints1,
                hitPoints2, creatureIds1, creatureIds2, handIds1, handIds2, handCardDescription1, handCardDescription2);

        return attackPresenter.exitDefendInputScreen(updatedModel);
    }
}
