package use_cases.play_card_use_case;

import entities.GameState;
import entities.Player;
import entities.cardEffects.CardEffect;
import entities.cardEffects.CreatureStatsEffect;
import entities.cardEffects.PlayerStatsEffect;
import entities.cards.*;

import java.util.ArrayList;
import java.util.List;

public class PlayCardInteractor implements PlayCardInputBoundary {

    final GameState gameState;
    final PlayCardOutputBoundary playCardPresenter;

    public PlayCardInteractor(GameState gameState, PlayCardOutputBoundary playCardPresenter) {
        this.gameState = gameState;
        this.playCardPresenter = playCardPresenter;
    }


    /**
     * Fetch the card object from the player, and play if it can be played
     * without target selection (a second input from the player), or tell the
     * presenter the possible targets and let presenter open a target selection
     * screen.
     *
     * @param cardId the id of the card, given from the controller's layer.
     */
    @Override
    public PlayCardOutputModel playCard(int cardId) {
        return null;
    }

    /**
     * Spend the card that the player chose to play, and trigger its effects
     * on the targets from the {@code inputData}.
     * Target selection should be complete when this method is called, so the
     * TargetModel contains not the possible targets but the chosen targets.
     * Further, the targets will always be creatures, so we can safely assume
     * the effects are CreatureStatsEffects.
     *
     * @param inputData a TargetModel containing the id of card to be played and
     *                  the list of targets.
     * @return a PlayCardOutputModel, containing all possible changed stats in
     * the gameState.
     */
    @Override
    public PlayCardOutputModel playSingleTargetCard(TargetModel inputData) {
        return null;
    }

    /**
     * A helper to prepare the output data.
     */
    private PlayCardOutputModel getOutputModel() {
        Player player1 = gameState.getCurrentPlayer();
        Player player2 = gameState.getOpposingPlayer();

        List<Integer> handIds = new ArrayList<>();
        List<String> handNames = new ArrayList<>();
        for (Card c : player1.getHand()) {
            handIds.add(c.getId());
            handNames.add(c.getName());
        }

        List<Integer> ids1 = new ArrayList<>();
        List<Integer> ids2 = new ArrayList<>();
        List<Integer> hitPoints1 = new ArrayList<>();
        List<Integer> hitPoints2 = new ArrayList<>();
        List<Integer> attacks1 = new ArrayList<>();
        List<Integer> attacks2 = new ArrayList<>();
        for (CreatureCard c : player1.getCreatures()) {
            ids1.add(c.getId());
            hitPoints1.add(c.getTotalHitPoints());
            attacks1.add(c.getTotalAttackDamage());
        }
        for (CreatureCard c : player2.getCreatures()) {
            ids2.add(c.getId());
            hitPoints2.add(c.getTotalHitPoints());
            attacks2.add(c.getTotalAttackDamage());
        }

        String structure1 = "";
        String structure2 = "";
        if (player1.getStructure() != null) {
            structure1 = player1.getStructure().getName();
        }
        if (player1.getStructure() != null) {
            structure2 = player2.getStructure().getName();
        }

        return new PlayCardOutputModel(handIds, handNames, structure1, structure2,
                ids1, ids2, hitPoints1, hitPoints2, attacks1, attacks2);
    }

    /**
     * Get list of creature ids for one player.
     * NOTE: we will edit the entities when the use cases are all finished.
     * Particularly for adding a method in player to get all creature ids at once.
     * It has become too common across all use cases that it deserves a whole method.
     */
    private List<Integer> getCreatureIds(List<CreatureCard> creatures) {
        List<Integer> ids = new ArrayList<>();
        for (CreatureCard c : creatures) {
            ids.add(c.getId());
        }
        return ids;
    }
}
