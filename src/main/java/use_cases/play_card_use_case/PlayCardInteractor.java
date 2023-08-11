package use_cases.play_card_use_case;

import entities.GameState;
import entities.Player;
import entities.cardEffects.CardEffect;
import entities.cardEffects.CreatureStatsEffect;
import entities.cardEffects.PlayerStatsEffect;
import entities.cards.*;
import use_cases.CreatureCardModel;

import java.util.ArrayList;
import java.util.List;

public class PlayCardInteractor implements PlayCardInputBoundary {

    final GameState gameState;
    final PlayCardOutputBoundary playCardPresenter;

    /**
     * Construct a PlayCardInteractor, with given GameState and PlayOutputBoundary
     * 
     * @param gameState the GameState that records the progress of the current game.
     *                  It should be shared by all use case interactors.
     * @param playCardPresenter implementing class of the output boundary that
     *                          handles the communication to the outer layers of
     *                          the program.
     */
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
        String cardName = action.getName();
        String cardDescription = action.getDescription();
        List<Integer> targetIds = new ArrayList<>();
        List<CreatureCardModel> targetData = new ArrayList<>();
        switch (action.getTargetType()) {
            case SINGLE_ENEMY:
                targetIds.addAll(getCreatureIds(new ArrayList<>()));  // placeholder, keeping the total size = 6
                targetIds.addAll(getCreatureIds(opponent.getCreatures()));
                targetData.addAll(getTargetDataModels(new ArrayList<>()));
                targetData.addAll(getTargetDataModels(opponent.getCreatures()));
                break;
            case SINGLE_FRIENDLY:
                targetIds.addAll(getCreatureIds(player.getCreatures()));
                targetIds.addAll(getCreatureIds(new ArrayList<>()));
                targetData.addAll(getTargetDataModels(player.getCreatures()));
                targetData.addAll(getTargetDataModels(new ArrayList<>()));
                break;
            case ANY:
                targetIds.addAll(getCreatureIds(player.getCreatures()));
                targetIds.addAll(getCreatureIds(opponent.getCreatures()));
                targetData.addAll(getTargetDataModels(player.getCreatures()));
                targetData.addAll(getTargetDataModels(opponent.getCreatures()));
                break;
        }
        if (!targetIds.isEmpty()) {
            TargetModel targetModel = new TargetModel(cardId, cardName, cardDescription, targetIds, targetData);
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
        // Need attention in the future: right now, the output model ensures that
        // the data labeled with a '1' will always be the player at the bottom of the screen
        // which is also the player going first.
        // If we introduce the random play order, this needs some revisions
        Player player1, player2;
        if (gameState.getCurrentPlayerIndex() == 0) {
            player1 = gameState.getCurrentPlayer();
            player2 = gameState.getOpposingPlayer();
        } else {
            player1 = gameState.getOpposingPlayer();
            player2 = gameState.getCurrentPlayer();
        }

        Player playerToUpdateHand = gameState.getCurrentPlayer();
        List<Integer> handIds = new ArrayList<>();
        List<String> handNames = new ArrayList<>();
        List<String> handDescriptions = new ArrayList<>();
        for (Card c : playerToUpdateHand.getHand()) {
            handIds.add(c.getId());
            handNames.add(c.getName());
            if (c instanceof Playable) {
                handDescriptions.add(((Playable) c).getDescription());
            } else {
                handDescriptions.add("Essence");
            }
        }

        List<Integer> ids1 = new ArrayList<>();
        List<Integer> ids2 = new ArrayList<>();
        List<Integer> hitPoints1 = new ArrayList<>();
        List<Integer> hitPoints2 = new ArrayList<>();
        List<Integer> attacks1 = new ArrayList<>();
        List<Integer> attacks2 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            if (i > player1.getCreatures().size() - 1) {
                ids1.add(-1);
                hitPoints1.add(0);
                attacks1.add(0);
            } else {
                CreatureCard c = player1.getCreatures().get(i);
                ids1.add(c.getId());
                hitPoints1.add(c.getTotalHitPoints());
                attacks1.add(c.getTotalAttackDamage());
            }
        }
        for (int i = 0; i < 3; i++) {
            if (i > player2.getCreatures().size() - 1) {
                ids2.add(-1);
                hitPoints2.add(0);
                attacks2.add(0);
            } else {
                CreatureCard c = player2.getCreatures().get(i);
                ids2.add(c.getId());
                hitPoints2.add(c.getTotalHitPoints());
                attacks2.add(c.getTotalAttackDamage());
            }
        }

        String structure1 = "";
        String structure2 = "";
        String structureDesc1 = "";
        String structureDesc2 = "";
        if (player1.getStructure() != null) {
            structure1 = player1.getStructure().getName();
            structureDesc1 = player1.getStructure().getDescription();
        }
        if (player2.getStructure() != null) {
            structure2 = player2.getStructure().getName();
            structureDesc2 = player2.getStructure().getDescription();
        }

        return new PlayCardOutputModel(handIds, handNames, handDescriptions, structure1, structure2,
                structureDesc1, structureDesc2, ids1, ids2, hitPoints1, hitPoints2, attacks1, attacks2);
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
        for (int i = 0; i < 3; i++) {
            if (i > creatures.size() - 1) {
                ids.add(-1);
            } else {
                ids.add(creatures.get(i).getId());
            }
        }
        return ids;
    }

    private List<CreatureCardModel> getTargetDataModels(List<CreatureCard> creatures) {
        List<CreatureCardModel> models = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            if (i > creatures.size() - 1) {
                models.add(null);
            } else {
                models.add(new CreatureCardModel(creatures.get(i).getName(),
                        creatures.get(i).getAttackCost(),
                        creatures.get(i).getDefendCost(),
                        creatures.get(i).getTotalAttackDamage(),
                        creatures.get(i).getTotalHitPoints()));
            }
        }
        return models;
    }
}
