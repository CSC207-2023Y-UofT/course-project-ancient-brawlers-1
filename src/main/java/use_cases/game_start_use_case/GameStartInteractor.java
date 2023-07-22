package use_cases.game_start_use_case;

import entities.GameState;

public class GameStartInteractor implements GameStartInputBoundary {

    GameState gameState;
    GameStartOutputBoundary gameStartPresenter;

    public GameStartInteractor(GameState gameState, GameStartOutputBoundary gameStartPresenter) {
        this.gameState = gameState;
        this.gameStartPresenter = gameStartPresenter;
    }

    @Override
    public void decidePlayOrder() {

    }

    @Override
    public GameStartResponseModel prepareMulligan() {
        GameStartResponseModel responseModel = null;

        return gameStartPresenter.showMulliganScreen(responseModel);
    }

    @Override
    public GameStartResponseModel processMulligan(GameStartRequestModel inputData) {
        return null;
    }
}
