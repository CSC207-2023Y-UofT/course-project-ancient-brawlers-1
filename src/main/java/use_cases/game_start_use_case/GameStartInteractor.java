package use_cases.game_start_use_case;

import entities.GameState;

public class GameStartInteractor implements GameStartInputBoundary {

    final GameState gameState;
    final GameStartOutputBoundary gameStartPresenter;

    public GameStartInteractor(GameState gameState, GameStartOutputBoundary gameStartPresenter) {
        this.gameState = gameState;
        this.gameStartPresenter = gameStartPresenter;
    }

    @Override
    public void decidePlayOrder() {

    }

    @Override
    public GameStartResponseModel prepareMulligan() {
        return null;
    }

    @Override
    public GameStartResponseModel processMulligan(GameStartRequestModel inputData) {
        return null;
    }
}
