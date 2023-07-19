package use_cases.game_preparation_use_case;

public interface GamePrepInputBoundary {

    void promptPlayerInfo();

    GamePrepResponseModel processPlayerInfo(GamePrepRequestModel requestModel);
}
