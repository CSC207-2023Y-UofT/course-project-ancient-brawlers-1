package interface_adapters.presenters;

import interface_adapters.view_models.GameFrameModel;
import interface_adapters.view_models.GameScreenType;
import interface_adapters.view_models.VictoryScreenModel;
import use_cases.win_loss_use_case.WinLossOutputBoundary;
import use_cases.win_loss_use_case.WinLossResponseModel;

public class WinLossPresenter implements WinLossOutputBoundary {

    private final GameFrameModel frameModel;
    private final VictoryScreenModel victoryScreenModel;

    public WinLossPresenter(GameFrameModel frameModel, VictoryScreenModel victoryScreenModel) {
        this.frameModel = frameModel;
        this.victoryScreenModel = victoryScreenModel;
    }

    /**
     * Sends instruction to display the Victory Screen when a winner appears.
     * 
     * @param outputData a WinLossResponseModel that contains the name of the 
     *                   winner.
     * @return the same responseModel that was passed in
     */
    @Override
    public WinLossResponseModel showVictoryScreen(WinLossResponseModel outputData) {
        if(!outputData.getWinnerName().isEmpty()){
            victoryScreenModel.setWinnerName(outputData.getWinnerName());
            frameModel.setCurrentScreen(GameScreenType.VICTORY);
        }
        return outputData;
    }
}
