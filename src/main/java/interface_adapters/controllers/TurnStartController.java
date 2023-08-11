package interface_adapters.controllers;

import use_cases.turn_start_use_case.TurnStartInputBoundary;

public class TurnStartController {

    private final TurnStartInputBoundary turnStartInteractor;

    public TurnStartController(TurnStartInputBoundary turnStartInteractor) {
        this.turnStartInteractor = turnStartInteractor;
    }

    public void processTurnStart() {
        turnStartInteractor.drawCards();
        turnStartInteractor.clearBuffs();
        turnStartInteractor.triggerTurnStartEffects();
    }
}
