package use_cases.turn_start_use_case;

import entities.GameState;

public class TurnStartInteractor implements TurnStartInputBoundary {

    final GameState gameState;

    final TurnStartOutputBoundary turnStartPresenter;

    public TurnStartInteractor(GameState gameState, TurnStartOutputBoundary turnStartPresenter) {
        this.gameState = gameState;
        this.turnStartPresenter = turnStartPresenter;
    }


    @Override
    public TurnStartResponseModel drawCards() {
        return null;
    }

    @Override
    public TurnStartResponseModel clearTemporaryEffects() {
        return null;
    }

    @Override
    public TurnStartResponseModel triggerTurnStartEffects() {
        return null;
    }
}
