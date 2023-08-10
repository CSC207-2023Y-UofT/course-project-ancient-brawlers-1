package use_cases.game_save_use_case;

import entities.GameState;

/**
 * The GameSaveDataGateway is another Data Access component. This gateway's job
 * is to connect to a database that stores game saves (serialized gamestate
 * objects).
 */
public interface GameSaveDataGateway {

    GameState getGameBySaveName(String saveName);

    void saveGame(String saveName, String filePath);
}
