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
     * Fetch the card object from the player and decide if it needs a target
     * or if it can proceed spontaneously.
     * If it does not need target selection, then targets can be located by using
     * information in the gameState, so this method calls {@code playCard()}.
     * If it does need target, a second input from the player is expected. It will
     * signal the presenter with a TargetModel.
     *
     * @param cardId the id of the card, given from the controller's layer.
     */
    @Override
    public void processCard(int cardId) {
        Player player = gameState.getCurrentPlayer();
        Player opponent = gameState.getOpposingPlayer();
        Card card = player.getCardById(cardId);
        if (card instanceof EssenceCard) {
            playCardPresenter.displayErrorMessage("Essence card cannot be played directly.");
        } else if (card instanceof StructureCard) {
            playCard(card, player);
        } else {
            List<CreatureCard> targets = new ArrayList<>();
            ActionCard action = ((ActionCard) card);
            switch (action.getTargetType()) {
                case ALL:
                    targets.addAll(player.getCreatures());
                    targets.addAll(opponent.getCreatures());
                    break;
                case FRIENDLY:
                    targets.addAll(player.getCreatures());
                    break;
                case ENEMY:
                    targets.addAll(opponent.getCreatures());
                    break;
                case SELF:
                    playCard(action, player);
                    break;
                case OPPONENT:
                    playCard(action, opponent);
                    break;
                case SINGLE_ENEMY:
                    playCardPresenter.requestTarget(new TargetModel(cardId, getCreatureIds(opponent.getCreatures())));
                    break;
                case SINGLE_FRIENDLY:
                    playCardPresenter.requestTarget(new TargetModel(cardId, getCreatureIds(player.getCreatures())));
                    break;
            }
            playCard(new TargetModel(cardId, getCreatureIds(targets)));
        }

    }

    /**
     * Spend the card that the player chose to play, and trigger its effects
     * on the targets from the {@code inputData}.
     * Target selection should be complete when this method is called, so the
     * TargetModel contains not the possible targets but the chosen targets.
     *
     * @param inputData a PlayCardInputModel containing the id of card to be
     *                  played and the list of targets.
     * @return a PlayCardOutputModel, containing all possible changed stats in
     * the gameState.
     */
    @Override
    public PlayCardOutputModel playCard(TargetModel inputData) {
        Player player = gameState.getCurrentPlayer();
        Player opponent = gameState.getOpposingPlayer();

        // probably also want a method in gameState to get all creature objects
        // this whole block of code needs refactoring
        List<CreatureCard> targets = new ArrayList<>();
        List<CreatureCard> allCreatures = new ArrayList<>();
        allCreatures.addAll(player.getCreatures());
        allCreatures.addAll(opponent.getCreatures());
        for (int i : inputData.getTargetIds()) {
            for (CreatureCard c : allCreatures) {
                if (c.getId() == i) {
                    targets.add(c);
                }
            }
        }

        Card card = player.playCard(inputData.getCardId());
        for (CardEffect e : ((ActionCard) card).getEffects()) {
            for (CreatureCard c : targets) {
                ((CreatureStatsEffect) e).invokeEffect(c);
            }
        }

        PlayCardOutputModel outputModel = getOutputModel();
        return playCardPresenter.updateStats(outputModel);
    }

    /**
     * A private helper to automatically trigger the cards that are addressed to
     * players rather than creatures.
     *
     * @param card the card object to be spent.
     * @param targetPlayer the player object being affected.
     * @return a PlayCardOutputModel, containing all possible changed stats in
     * the gameState.
     */
    private PlayCardOutputModel playCard(Card card, Player targetPlayer) {
        if (card instanceof ActionCard) {
            for (CardEffect e : ((ActionCard) card).getEffects()) {
                ((PlayerStatsEffect) e).invokeEffect(targetPlayer);
            }
        } else {
            targetPlayer.setStructure((StructureCard) card);
        }
        PlayCardOutputModel outputModel = getOutputModel();
        return playCardPresenter.updateStats(outputModel);
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
