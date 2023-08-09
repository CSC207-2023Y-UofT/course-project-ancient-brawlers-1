package interface_adapters.controllers;

import use_cases.turn_end_use_case.TurnEndInputBoundary;

public class TurnEndController {

    private TurnEndInputBoundary interactor;

    public TurnEndController(TurnEndInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void passTurn() {
        interactor.passTurn();
    }
}
