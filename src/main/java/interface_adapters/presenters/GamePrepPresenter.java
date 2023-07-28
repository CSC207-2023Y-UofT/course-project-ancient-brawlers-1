package interface_adapters.presenters;

import interface_adapters.GamePrepFailed;
import use_cases.game_preparation_use_case.GamePrepOutputBoundary;
import use_cases.game_preparation_use_case.GamePrepResponseModel;

import java.util.List;

public class GamePrepPresenter implements GamePrepOutputBoundary {

    /**
     * Display a screen to ask both players for their names and let them choose
     * the creatures for their game.
     *
     * @param creatureNames the names of the creatures that can be selected for
     *                      the game.
     */
    @Override
    public void showGamePreparationScreen(List<String> creatureNames) {

    }

    /**
     * Notify the players that the game creation process cannot go through due
     * to unexpected inputs.
     * This could be empty player names, or not exactly 3 selected creatures.
     *
     * @param message the error message for the players.
     * @return the GamePrepResponseModel to match the method signature of the
     * interactor method calling this method.
     */
    @Override
    public GamePrepResponseModel displayErrorMessage(String message) {
        throw new GamePrepFailed(message);
    }

    /**
     * Display the main gameplay screen and show the initial game data given in
     * the {@code outputData}.
     *
     * @param outputData the data model containing the player names and all the
     *                   stats of their selected creatures (such as hit-points
     *                   and attack damage, and also some internal ids for the
     *                   presenter to identify specific creatures).
     * @return the same GamePrepResponseModel that is passed to this method.
     */
    @Override
    public GamePrepResponseModel showGameplayScreen(GamePrepResponseModel outputData) {
        return null;
    }
}
