package interface_adapters.controllers;

import use_cases.death_detector_use_case.DeathDetectorInputBoundary;
import use_cases.win_loss_use_case.WinLossInputBoundary;

public class EndGameController {

    private DeathDetectorInputBoundary detectorInteractor;
    private WinLossInputBoundary winLossInteractor;

    public EndGameController(DeathDetectorInputBoundary detectorInteractor, WinLossInputBoundary winLossInteractor) {
        this.detectorInteractor = detectorInteractor;
        this.winLossInteractor = winLossInteractor;
    }

    public void checkEndGame() {
        detectorInteractor.detectCreatureDeath();
        winLossInteractor.detectWinLoss();
    }
}
