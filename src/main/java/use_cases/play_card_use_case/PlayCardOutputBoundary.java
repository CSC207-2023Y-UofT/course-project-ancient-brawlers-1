package use_cases.play_card_use_case;

/**
 * PlayCardOutputBoundary provides the interface for the presenter to display
 * changes to the game screen when a card is played, or to prompt the player for
 * target selection.
 */
public interface PlayCardOutputBoundary {

    /**
     * To be used in the playCard() method in the interactor, for cases when
     * the card cannot be played.
     *
     * @param message the string message to show on the screen.
     */
    PlayCardOutputModel displayErrorMessage(String message);

    /**
     * Display a target request screen, showing the card to be played and the
     * possible targets to choose from. The input from this screen will be sent
     * to the {@code playSingleTargetCard()} method of the interactor.
     *
     * @param requestModel a data model containing the id of the card to be played
     *                     and the list of possible targets.
     */
    PlayCardOutputModel showTargetSelectionScreen(TargetModel requestModel);

    /**
     * Bring back the gameplay screen and add all information in {@code outputData}
     * into the view.
     *
     * @param outputData a PlayCardOutputModel containing all values that reflect
     *                   the changes in the gameState.
     * @return the same as the input parameter, for consistency with the interactor.
     */
    PlayCardOutputModel updateGameScreen(PlayCardOutputModel outputData);
}
