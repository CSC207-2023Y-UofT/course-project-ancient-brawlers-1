package use_cases.turn_end_use_case;

import entities.GameEvent;
import entities.GameState;
import entities.Player;
import entities.cardEffects.CardEffect;
import entities.cardEffects.CreatureStatsEffect;
import entities.cardEffects.PlayerStatsEffect;
import entities.cards.Card;
import entities.cards.CreatureCard;
import entities.cards.StructureCard;

import java.util.ArrayList;
import java.util.List;

public class TurnEndInteractor implements TurnEndInputBoundary {

    private final GameState gameState;
    private final TurnEndOutputBoundary turnEndPresenter;

    public TurnEndInteractor(GameState gameState, TurnEndOutputBoundary turnEndPresenter) {
        this.gameState = gameState;
        this.turnEndPresenter = turnEndPresenter;
    }
    
    @Override
    public void passTurn() {
        gameState.switchPlayer();
        // 1. pass player's name to presenter
        // 2. whatever other effects that are needed
    }

    @Override
    public TurnEndResponseModel triggerEndTurnEffects() {
        Player currentPlayer = gameState.getCurrentPlayer();
        Player nextPlayer = gameState.getOpposingPlayer();


        // // 1. Check if the current player has a structure in play
        // // 1a. Yes, lets trigger the event
        if (currentPlayer.getStructure() != null && currentPlayer.getStructure().getTriggerEvent() == GameEvent.TURN_END) {
            // Trigger the effects needed
            StructureCard currentStructure = currentPlayer.getStructure();
            List<CreatureCard> allTargets = new ArrayList<>();

            switch (currentStructure.getTargetType()) {
                // on the player themselves
                case SELF:
                    for (CardEffect effect : currentStructure.getEffects()) {
                        if (effect instanceof PlayerStatsEffect) {
                            ((PlayerStatsEffect) effect).invokeEffect(currentPlayer);
                        }
                    }
                    break;
                case ENEMY:
                    allTargets.addAll(nextPlayer.getCreatures());
                    break;
                case ALL:
                    allTargets.addAll(nextPlayer.getCreatures());
                    allTargets.addAll(currentPlayer.getCreatures());
                    break;
                case FRIENDLY:
                    allTargets.addAll(currentPlayer.getCreatures());
                    break;
                default:
                    break;
            }

            for (CreatureCard creature : allTargets) {
                for (CardEffect effect : currentStructure.getEffects()) {
                    if (effect instanceof CreatureStatsEffect) {
                        ((CreatureStatsEffect) effect).invokeEffect(creature);
                    }
                }
            }
        }

        List<Integer> creatureIds1 = new ArrayList<>();
        List<Integer> creatureIds2 = new ArrayList<>();
        List<Integer> hitPoints1 = new ArrayList<>();
        List<Integer> hitPoints2 = new ArrayList<>();
        List<Integer> attack1 = new ArrayList<>();
        List<Integer> attack2 = new ArrayList<>();
        List<Integer> playerHandIds1 = new ArrayList<>();
        List<Integer> playerHandIds2 = new ArrayList<>();
        
        for (CreatureCard creature : currentPlayer.getCreatures()) {
            creature.clearStun();
            attack1.add(creature.getTotalAttackDamage());
            creatureIds1.add(creature.getId());
            hitPoints1.add(creature.getTotalHitPoints());
        }

        for (Card currentCard : currentPlayer.getHand()) {
            playerHandIds1.add(currentCard.getId());
        }

        for (CreatureCard creature : nextPlayer.getCreatures()) {
            attack2.add(creature.getTotalAttackDamage());
            creatureIds2.add(creature.getId());
            hitPoints2.add(creature.getTotalHitPoints());
        }

        for (Card currentCard : nextPlayer.getHand()) {
            playerHandIds2.add(currentCard.getId());
        }

        TurnEndResponseModel turnEndModel = new TurnEndResponseModel(hitPoints1, hitPoints2, attack1, attack2, creatureIds1, creatureIds2, playerHandIds1, playerHandIds2);
        
        return turnEndPresenter.showTurnEndScreen(turnEndModel);
    }
}
