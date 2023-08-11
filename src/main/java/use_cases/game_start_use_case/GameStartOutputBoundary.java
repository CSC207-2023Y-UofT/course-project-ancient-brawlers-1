package use_cases.game_start_use_case;

/**
 * GameStartOutputBoundary provides the interface for the interface for the presenter to control the
 * UI of the game. More specifically, it is responsible for showing and exiting the Mulligan Screen.
 */
public interface GameStartOutputBoundary {

    GameStartResponseModel showMulliganScreen(GameStartResponseModel outputData);

    GameStartResponseModel exitMulliganScreen(GameStartResponseModel outputData);

    void displayPlayerOrder(String message, String playerName);
}
