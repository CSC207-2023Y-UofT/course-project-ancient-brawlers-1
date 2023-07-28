package interface_adapters.controllers;

import use_cases.game_start_use_case.GameStartInputBoundary;

public class GameStartController {

    final GameStartInputBoundary gameStartInteractor;

    public GameStartController(GameStartInputBoundary gameStartInteractor) {
        this.gameStartInteractor = gameStartInteractor;
    }

    public void decidePlayOrder() {
        gameStartInteractor.decidePlayOrder();
    }


}
