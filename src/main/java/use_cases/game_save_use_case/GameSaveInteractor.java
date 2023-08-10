package use_cases.game_save_use_case;

import entities.GameState;

public class GameSaveInteractor implements GameSaveInputBoundary {

    final GameState gameState;
    final GameSaveDataGateway dataAccessor;
    final GameSaveOutputBoundary gameSavePresenter;

    public GameSaveInteractor(GameState gameState, GameSaveDataGateway dataAccessor, GameSaveOutputBoundary gameSavePresenter) {
        this.gameState = gameState;
        this.dataAccessor = dataAccessor;
        this.gameSavePresenter = gameSavePresenter;
    }

    /**
     * Serialize the current gamestate object into the file specified by a given
     * file path. Uses a game saving data accessor.
     *
     * @param requestModel this request model should contain a path where the
     *                     game data will be written into.
     */
    @Override
    public void saveGame(GameSaveRequestModel requestModel) {

    }

    /**
     * Deserialize a specified gamestate file and load the data into the gamestate.
     * Uses a game saving data accessor.
     *
     * @param requestModel this request model should contain a path to a file
     *                     which will be read to deserialize the data.
     */
    @Override
    public void loadGame(GameSaveRequestModel requestModel) {

    }

    /**
     * A method to tell the use case that no saving is needed, so the gamestate
     * can simply be reset.
     */
    @Override
    public void closeGame() {

    }
}
