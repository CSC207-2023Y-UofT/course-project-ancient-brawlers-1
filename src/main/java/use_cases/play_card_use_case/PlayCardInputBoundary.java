package use_cases.play_card_use_case;

/**
 * PlayCardInputBoundary provides the interface for handling play-card logic.
 * That is, during a player's turn, they can play any cards from their hand, and
 * depending on the type of card, this use case should give responses accordingly.
 */
public interface PlayCardInputBoundary {

    /**
     * Fetch the card object from the player and decide if it needs a target
     * or if it can proceed spontaneously.
     * If it does not need target selection, then targets can be located by using
     * information in the gameState, so this method calls {@code playCard()}.
     * If it does need target, a second input from the player is expected. It will
     * signal the presenter with a TargetRequestModel.
     *
     * @param cardId the id of the card, given from the controller's layer.
     */
    void processCard(String cardId);

    /**
     * Spend the card that the player chose to play, and trigger its effects
     * on the targets from the {@code inputData}.
     * Target selection should be complete when this method is called.
     *
     * @param inputData a PlayCardInputModel containing the id of card to be
     *                  played and the list of targets.
     * @return a PlayCardOutputModel, containing all possible changed stats in
     * the gameState.
     */
    PlayCardOutputModel playCard(PlayCardInputModel inputData);
}
