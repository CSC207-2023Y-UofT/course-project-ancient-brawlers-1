package interface_adapters.presenters;

import interface_adapters.view_models.GameFrameModel;
import interface_adapters.view_models.GameScreenType;
import interface_adapters.view_models.GameplayScreenModel;
import use_cases.death_detector_use_case.DeathDetectorOutputBoundary;
import use_cases.death_detector_use_case.DeathDetectorResponseModel;

public class DeathDetectorPresenter implements DeathDetectorOutputBoundary {

    private GameFrameModel gameFrameModel;
    private GameplayScreenModel gameplayScreenModel;

    public DeathDetectorPresenter(GameFrameModel gameFrameModel, GameplayScreenModel gameplayScreenModel) {
        this.gameFrameModel = gameFrameModel;
        this.gameplayScreenModel = gameplayScreenModel;
    }

    @Override
    public DeathDetectorResponseModel updateCreatureStats(DeathDetectorResponseModel outputData) {
        gameplayScreenModel.getPlayer1().setCreatureIds(outputData.getP1CreatureIds());
        gameplayScreenModel.getPlayer1().setCreatureHPs(outputData.getP1CreatureHitPoints());
        gameplayScreenModel.getPlayer1().setCreatureAttacks(outputData.getP1CreatureAttack());
        gameplayScreenModel.getPlayer2().setCreatureIds(outputData.getP2CreatureIds());
        gameplayScreenModel.getPlayer2().setCreatureHPs(outputData.getP2CreatureHitPoints());
        gameplayScreenModel.getPlayer2().setCreatureAttacks(outputData.getP2CreatureAttack());
        gameFrameModel.refresh();
        return null;
    }
}