package use_cases.death_detector_use_case;

import entities.GameEvent;
import entities.GameState;
import entities.Player;
import entities.cardEffects.CardEffect;
import entities.cardEffects.CreatureStatsEffect;
import entities.cards.CreatureCard;
import entities.cards.StructureCard;
import entities.cards.TargetType;
import use_cases.GameOutputBoundary;

import java.util.ArrayList;
import java.util.List;

public class DeathDetectorInteractor implements DeathDetectorInputBoundary {
    final GameState gameState;
    final DeathDetectorOutputBoundary deathDetectorPresenter;

    public DeathDetectorInteractor(GameState gameState, DeathDetectorOutputBoundary deathDetectorPresenter) {
        this.gameState = gameState;
        this.deathDetectorPresenter = deathDetectorPresenter;
    }

    private void processPlayer(Player player1, Player player2, List<Integer> ids) {
        List<Integer> defeatCreatureIds = new ArrayList<>();
        for (CreatureCard c : player1.getCreatures()) {
            if (c.getHitPoints() <= 0) {
                defeatCreatureIds.add(c.getId());
                player1.removeCreature(c.getId());
            }
        }
        if (defeatCreatureIds.size() != 0 && player1.getStructure() != null) {
            StructureCard structureCard = player1.getStructure();
            if (structureCard.getTriggerEvent() == GameEvent.CREATURE_DEATH) {
                List<CreatureCard> targetCreatures = new ArrayList<>();
                switch (structureCard.getTargetType()){
                    case ALL:
                        targetCreatures.addAll(player1.getCreatures());
                        targetCreatures.addAll(player2.getCreatures());
                        break;
                    case FRIENDLY:
                        targetCreatures.addAll(player1.getCreatures());
                        break;
                    case ENEMY:
                        targetCreatures.addAll(player2.getCreatures());
                        break;
                }
                for(CreatureCard t : targetCreatures){
                    for(CardEffect e : structureCard.getEffects()){
                        ((CreatureStatsEffect)e).invokeEffect(t);
                    }
                }
            }
        }
        ids.addAll(defeatCreatureIds);
    }

    @Override
    public DeathDetectorResponseModel detectCreatureDeath() {
        Player player1 = gameState.getCurrentPlayer();
        Player player2 = gameState.getOpposingPlayer();

        List<Integer> allDefeatIds = new ArrayList<>();
        processPlayer(player1, player2, allDefeatIds);
        processPlayer(player2, player1, allDefeatIds);

        List<Integer> p1CreatureIds = new ArrayList<>();
        List<Integer> p2CreatureIds = new ArrayList<>();
        List<Integer> p1CreatureHitPoints = new ArrayList<>();
        List<Integer> p2CreatureHitPoints = new ArrayList<>();
        List<Integer> p1CreatureAttack = new ArrayList<>();
        List<Integer> p2CreatureAttack = new ArrayList<>();

        for (CreatureCard c : player1.getCreatures()){
            p1CreatureIds.add(c.getId());
            p1CreatureHitPoints.add(c.getTotalHitPoints());
            p1CreatureAttack.add(c.getTotalAttackDamage());
        }
        for (CreatureCard c : player2.getCreatures()){
            p2CreatureIds.add(c.getId());
            p2CreatureHitPoints.add(c.getTotalHitPoints());
            p2CreatureAttack.add(c.getTotalAttackDamage());
        }

        DeathDetectorResponseModel outputData = new DeathDetectorResponseModel(allDefeatIds, p1CreatureIds, p2CreatureIds,
                p1CreatureHitPoints, p2CreatureHitPoints, p1CreatureAttack, p2CreatureAttack);

        return deathDetectorPresenter.updateCreatureStats(outputData);
    }
}