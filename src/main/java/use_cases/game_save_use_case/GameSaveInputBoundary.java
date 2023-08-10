package use_cases.game_save_use_case;

/**
 * The GameSaveInputBoundary provides the interface for requesting to save or
 * load games.
 */
public interface GameSaveInputBoundary {

    /**
     * Serialize the current gamestate object into the file specified by a given
     * file path. Uses a game saving data accessor.
     *
     * @param requestModel this request model should contain a path where the
     *                     game data will be written into.
     */
    void saveGame(GameSaveRequestModel requestModel);

    /**
     * Deserialize a specified gamestate file and load the data into the gamestate.
     * Uses a game saving data accessor.
     *
     * @param requestModel this request model should contain a path to a file
     *                     which will be read to deserialize the data.
     */
    void loadGame(GameSaveRequestModel requestModel);

    /**
     * A method to tell the use case that no saving is needed, so the gamestate
     * can simply be reset.
     */
    void closeGame();
}
