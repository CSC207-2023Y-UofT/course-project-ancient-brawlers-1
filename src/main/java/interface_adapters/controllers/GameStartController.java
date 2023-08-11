package interface_adapters.controllers;

import use_cases.game_start_use_case.GameStartInputBoundary;
import use_cases.game_start_use_case.GameStartRequestModel;

import java.util.List;

public class GameStartController {

    private final GameStartInputBoundary gameStartInteractor;

    public GameStartController(GameStartInputBoundary gameStartInteractor) {
        this.gameStartInteractor = gameStartInteractor;
    }

    public void decidePlayOrder() {
        gameStartInteractor.decidePlayOrder();
    }

    public void startMulligan() {
        gameStartInteractor.prepareMulligan();
    }

    public void endMulligan(List<Integer> cardIds, List<String> cardNames) {
        GameStartRequestModel requestModel = new GameStartRequestModel(cardIds, cardNames);
        gameStartInteractor.processMulligan(requestModel);
    }
}
