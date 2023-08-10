package interface_adapters.presenters;

import interface_adapters.view_models.GameFrameModel;
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

    /**
     * Responsible for setting the updated data for the two players in the View Model
     * @param outputData contains the updated id, hitpoints, and attackpoints for all the creatures for both players.
     * More specifically, it will indicate whether a creature is dead by a -1 id.
     */
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
