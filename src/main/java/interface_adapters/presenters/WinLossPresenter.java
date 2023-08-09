package interface_adapters.presenters;

import game_ui.VictoryScreen;
import interface_adapters.view_models.GameFrameModel;
import interface_adapters.view_models.GameScreenType;
import interface_adapters.view_models.GameplayScreenModel;
import interface_adapters.view_models.VictoryScreenModel;
import use_cases.win_loss_use_case.WinLossOutputBoundary;
import use_cases.win_loss_use_case.WinLossResponseModel;

import java.util.Objects;

public class WinLossPresenter implements WinLossOutputBoundary {

    private final GameFrameModel frameModel;
    private final VictoryScreenModel victoryScreenModel;

    public WinLossPresenter(GameFrameModel frameModel, VictoryScreenModel victoryScreenModel) {
        this.frameModel = frameModel;
        this.victoryScreenModel = victoryScreenModel;
    }

    @Override
    public WinLossResponseModel showVictoryScreen(WinLossResponseModel outputData) {
        if(!outputData.getWinnerName().isEmpty()){
            victoryScreenModel.setWinnerName(outputData.getWinnerName());
            frameModel.setCurrentScreen(GameScreenType.VICTORY);
        }
        return outputData;
    }
}
