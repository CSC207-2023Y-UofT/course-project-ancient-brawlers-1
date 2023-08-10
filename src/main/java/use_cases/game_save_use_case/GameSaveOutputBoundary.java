package use_cases.game_save_use_case;

/**
 * The GameSaveOutputBoundary is an interface for the presenter that directly
 * shows the gameplay screen when the players load in a previously saved game.
 */
public interface GameSaveOutputBoundary {

    /**
     * A method that updates the gameplay screen with the given game data.
     *
     * @param gameData contains identical information in the gamestate, and is
     *                 useful for loading data into the view model in the
     *                 interface layer.
     */
    void loadGameToScreen(GameSaveOutputModel gameData);

    /**
     * A method to be used when the players don't want the game saved.
     */
    void closeGameWithoutSave();
}
