package use_cases.game_start_use_case;

public interface GameStartInputBoundary {

    void decidePlayOrder();

    GameStartResponseModel prepareMulligan();

    GameStartResponseModel processMulligan(GameStartRequestModel inputData);
}
