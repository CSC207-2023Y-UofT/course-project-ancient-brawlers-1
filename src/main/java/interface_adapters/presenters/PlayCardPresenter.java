package interface_adapters.presenters;

import interface_adapters.PlayCardException;
import interface_adapters.view_models.GameFrameModel;
import interface_adapters.view_models.GameplayScreenModel;
import use_cases.play_card_use_case.PlayCardOutputBoundary;
import use_cases.play_card_use_case.PlayCardOutputModel;
import use_cases.play_card_use_case.TargetModel;

public class PlayCardPresenter implements PlayCardOutputBoundary {

    private GameFrameModel gameFrameModel;
    private GameplayScreenModel gameplayScreenModel;

    public PlayCardPresenter(GameFrameModel gameFrameModel, GameplayScreenModel gameplayScreenModel) {
        this.gameFrameModel = gameFrameModel;
        this.gameplayScreenModel = gameplayScreenModel;
    }

    /**
     * To be used in the playCard() method in the interactor, for cases when
     * the card cannot be played.
     *
     * @param message the string message to show on the screen.
     */
    @Override
    public PlayCardOutputModel displayErrorMessage(String message) {
        throw new PlayCardException(message);
    }

    /**
     * Display a target request screen, showing the card to be played and the
     * possible targets to choose from. The input from this screen will be sent
     * to the {@code playSingleTargetCard()} method of the interactor.
     *
     * @param requestModel a data model containing the id of the card to be played
     *                     and the list of possible targets.
     */
    @Override
    public PlayCardOutputModel showTargetSelectionScreen(TargetModel requestModel) {
        return null;
    }

    /**
     * Bring back the gameplay screen and add all information in {@code outputData}
     * into the view.
     *
     * @param outputData a PlayCardOutputModel containing all values that reflect
     *                   the changes in the gameState.
     * @return the same as the input parameter, for consistency with the interactor.
     */
    @Override
    public PlayCardOutputModel updateGameScreen(PlayCardOutputModel outputData) {
        return null;
    }
}
