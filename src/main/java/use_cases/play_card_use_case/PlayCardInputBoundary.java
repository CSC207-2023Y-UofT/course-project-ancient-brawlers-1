package use_cases.play_card_use_case;

/**
 * PlayCardInputBoundary provides the interface for handling play-card logic.
 * That is, during a player's turn, they can play any cards from their hand, and
 * depending on the type of card, this use case should give responses accordingly.
 */
public interface PlayCardInputBoundary {

    /**
     * Fetch the card object from the player, and play if it can be played
     * without target selection (a second input from the player), or tell the
     * presenter the possible targets and let presenter open a target selection
     * screen.
     *
     * @param cardId the id of the card, given from the controller's layer.
     */
    PlayCardOutputModel playCard(int cardId);

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
    PlayCardOutputModel playSingleTargetCard(TargetModel inputData);
}
