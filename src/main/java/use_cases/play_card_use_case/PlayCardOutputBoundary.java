package use_cases.play_card_use_case;

/**
 * PlayCardOutputBoundary provides the interface for the presenter to display
 * changes to the game screen when a card is played, or to prompt the player for
 * target selection.
 */
public interface PlayCardOutputBoundary {

    /**
     * Display a target request screen, showing the card to be played and the
     * possible targets to choose from. The input from this screen will be sent
     * to the {@code playCard()} method of the interactor.
     *
     * @param requestModel a data model containing the id of the card to be played
     *                     and the list of possible targets.
     */
    void requestTarget(TargetRequestModel requestModel);

    PlayCardOutputModel updateStats(PlayCardOutputModel outputData);
}
