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
        Player player = gameState.getCurrentPlayer();
        Player opponent = gameState.getOpposingPlayer();
        Card card = player.getCardById(cardId);

        if (!(card instanceof Playable)) {
            return playCardPresenter.displayErrorMessage("This card cannot be played directly.");
        }
        if (card instanceof StructureCard) {
            player.playCard(cardId);
            player.setStructure((StructureCard) card);
            PlayCardOutputModel outputModel = getOutputModel();
            return playCardPresenter.updateGameScreen(outputModel);
        }
        // The only other case is the card being Action, so we can safely cast it.
        ActionCard action = (ActionCard) card;
        List<Integer> targetIds = new ArrayList<>();
        switch (action.getTargetType()) {
            case SINGLE_ENEMY:
                targetIds.addAll(getCreatureIds(opponent.getCreatures()));
                break;
            case SINGLE_FRIENDLY:
                targetIds.addAll(getCreatureIds(player.getCreatures()));
                break;
            case ANY:
                targetIds.addAll(getCreatureIds(player.getCreatures()));
                targetIds.addAll(getCreatureIds(opponent.getCreatures()));
                break;
        }
        if (targetIds.size() != 0) {
            TargetModel targetModel = new TargetModel(cardId, targetIds);
            return playCardPresenter.showTargetSelectionScreen(targetModel);
        }

        // If we didn't return in the above, we must be arriving at an action
        // that can be immediately spent. So we *spend* it from the player's hand
        // first, so that any cards drawn would not mistakenly get discarded
        player.playCard(cardId);
        switch (action.getTargetType()) {
            case FRIENDLY:
                useAction(action, player.getCreatures());
                break;
            case ENEMY:
                useAction(action, opponent.getCreatures());
                break;
            case ALL:
                useAction(action, player.getCreatures());
                useAction(action, opponent.getCreatures());
                break;
            case SELF:
                useAction(action, player);
                break;
            case OPPONENT:
                useAction(action, opponent);
                break;

        }
        PlayCardOutputModel outputModel = getOutputModel();
        return playCardPresenter.updateGameScreen(outputModel);
    }

    /**
     * Spend the card that the player chose to play, and trigger its effects
     * on the target given by {@code targetId}.
     * The target will always be a creature, so we can safely assume
     * the effects are CreatureStatsEffects.
     *
     * @param cardId   the id of the card being played.
     * @param targetId the id of the creature to be affected.
     * @return a PlayCardOutputModel, containing all possible changed stats in
     * the gameState.
     */
    @Override
    public PlayCardOutputModel playSingleTargetCard(int cardId, int targetId) {
        Player player = gameState.getCurrentPlayer();
        Player opponent = gameState.getOpposingPlayer();

        ActionCard card = (ActionCard) player.playCard(cardId);

        CreatureCard target;
        if (player.getCreatureById(targetId) != null) {
            target = player.getCreatureById(targetId);
        } else {
            target = opponent.getCreatureById(targetId);
        }
        for (CardEffect e : card.getEffects()) {
            ((CreatureStatsEffect) e).invokeEffect(target);
        }

        PlayCardOutputModel outputModel = getOutputModel();
        return playCardPresenter.updateGameScreen(outputModel);
    }

    /* Helper Methods */

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
        if (player2.getStructure() != null) {
            structure2 = player2.getStructure().getName();
        }

        return new PlayCardOutputModel(handIds, handNames, structure1, structure2,
                ids1, ids2, hitPoints1, hitPoints2, attacks1, attacks2);
    }

    private void useAction(ActionCard card, List<CreatureCard> creatures) {
        for (CardEffect e : card.getEffects()) {
            for (CreatureCard c : creatures) {
                ((CreatureStatsEffect) e).invokeEffect(c);
            }
        }
    }

    private void useAction(ActionCard card, Player player) {
        for (CardEffect e : card.getEffects()) {
            ((PlayerStatsEffect) e).invokeEffect(player);
        }
    }

    /**
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
