package use_cases.game_start_use_case;

public interface GameStartOutputBoundary {

    GameStartResponseModel showMulliganScreen(GameStartResponseModel outputData);

    GameStartResponseModel exitMulliganScreen(GameStartResponseModel outputData);
}
