package use_cases.death_detector_use_case;

import entities.GameEvent;
import entities.GameState;
import entities.Player;
import entities.cardEffects.CardEffect;
import entities.cardEffects.CreatureStatsEffect;
import entities.cards.CreatureCard;
import entities.cards.StructureCard;

import java.util.ArrayList;
import java.util.List;

public class DeathDetectorInteractor implements DeathDetectorInputBoundary {

    final GameState gameState;
    final DeathDetectorOutputBoundary deathDetectorPresenter;

    /**
     * Construct a DeathDetectorPresenter, with the given GameState and
     * DeathDetectorPresenter.
     *
     * @param gameState              the GameState that records the progress of the current game.
     *                               It should be shared by all use case interactors.
     * @param deathDetectorPresenter an implementing class of the output boundary that
     *                               handles the communication to the outer layers of
     *                               the program.
     */
    public DeathDetectorInteractor(GameState gameState, DeathDetectorOutputBoundary deathDetectorPresenter) {
        this.gameState = gameState;
        this.deathDetectorPresenter = deathDetectorPresenter;
    }

    /**
     * Helper method for checking potentially defeated creatures for a single
     * player. The second player in the input parameter list is necessary because
     * there may be card effects that trigger during the event of a creature's
     * defeat (GameEvent.CREATURE_DEATH).
     *
     * @param player1 the player being checked for defeated creatures.
     * @param player2 the player needed in case any card effects involve both
     *                players.
     */
    private void processPlayer(Player player1, Player player2) {
        boolean hasNewDefeats = false;
        for (CreatureCard c : player1.getCreatures()) {
            if (c.getHitPoints() <= 0) {
                // if the card id is not already -1, then it should be a new defeated creature
                // if it already is -1 then it is probably defeated from before
                if (c.getId() != -1) {
                    c.setId(-1);  // card has id = -1 means this card is defeated/unused
                    hasNewDefeats = true;
                }
            }
        }
        if (hasNewDefeats && player1.getStructure() != null) {
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
                        ((CreatureStatsEffect) e).invokeEffect(t);
                    }
                }
            }
        }
    }

    /**
     * Checks for both players if any creature's hit-points has dropped to 0 or
     * below, and set them to some state that indicates they are defeated.
     * At the moment, an id of -1 indicates defeat.
     *
     * @return a DeathDetectorResponseModel containing the updated stats of both
     * players' creatures.
     */
    @Override
    public DeathDetectorResponseModel detectCreatureDeath() {
        processPlayer(gameState.getCurrentPlayer(), gameState.getOpposingPlayer());
        processPlayer(gameState.getOpposingPlayer(), gameState.getCurrentPlayer());

        // Needs attention: check PlayCardInteractor
        Player player1, player2;
        if (gameState.getCurrentPlayerIndex() == 0) {
            player1 = gameState.getCurrentPlayer();
            player2 = gameState.getOpposingPlayer();
        } else {
            player1 = gameState.getOpposingPlayer();
            player2 = gameState.getCurrentPlayer();
        }

        List<Integer> p1CreatureIds = new ArrayList<>();
        List<Integer> p2CreatureIds = new ArrayList<>();
        List<Integer> p1CreatureHitPoints = new ArrayList<>();
        List<Integer> p2CreatureHitPoints = new ArrayList<>();
        List<Integer> p1CreatureAttack = new ArrayList<>();
        List<Integer> p2CreatureAttack = new ArrayList<>();

        for (CreatureCard c : player1.getCreatures()){
            p1CreatureIds.add(c.getId());
            if (c.getId() == -1) {
                c.setMaxHitPoints(0);
                c.setAttackDamage(0);
            }
            p1CreatureHitPoints.add(c.getTotalHitPoints());
            p1CreatureAttack.add(c.getTotalAttackDamage());
        }
        for (CreatureCard c : player2.getCreatures()){
            p2CreatureIds.add(c.getId());
            if (c.getId() == -1) {
                c.setMaxHitPoints(0);
                c.setAttackDamage(0);
            }
            p2CreatureHitPoints.add(c.getTotalHitPoints());
            p2CreatureAttack.add(c.getTotalAttackDamage());
        }

        DeathDetectorResponseModel outputData = new DeathDetectorResponseModel(p1CreatureIds, p2CreatureIds,
                p1CreatureHitPoints, p2CreatureHitPoints, p1CreatureAttack, p2CreatureAttack);

        return deathDetectorPresenter.updateCreatureStats(outputData);
    }
}