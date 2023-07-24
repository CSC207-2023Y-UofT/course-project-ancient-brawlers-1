package use_cases.game_preparation_use_case;

/**
 * GamePrepInputBoundary provides the interface for handling the setup of a new
 * game.
 */
public interface GamePrepInputBoundary {

    /**
     *
     */
    void promptPlayerInfo();

    GamePrepResponseModel processPlayerInfo(GamePrepRequestModel requestModel);
}
