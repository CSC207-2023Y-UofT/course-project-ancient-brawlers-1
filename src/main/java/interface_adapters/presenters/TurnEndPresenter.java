package interface_adapters.presenters;

import interface_adapters.view_models.GameFrameModel;
import interface_adapters.view_models.GameplayScreenModel;
import interface_adapters.view_models.PlayerDataModel;
import use_cases.turn_end_use_case.TurnEndOutputBoundary;
import use_cases.turn_end_use_case.TurnEndResponseModel;

public class TurnEndPresenter implements TurnEndOutputBoundary {

    private final GameFrameModel gameFrameModel;
    private final GameplayScreenModel gameplayScreenModel;

    public TurnEndPresenter(GameFrameModel gameFrameModel, GameplayScreenModel gameplayScreenModel) {
        this.gameFrameModel = gameFrameModel;
        this.gameplayScreenModel = gameplayScreenModel;
    }

    @Override
    public TurnEndResponseModel showTurnEndScreen(TurnEndResponseModel outputData) {
        PlayerDataModel playerData1 = gameplayScreenModel.getPlayer1();
        PlayerDataModel playerData2 = gameplayScreenModel.getPlayer2();

        playerData1.setCreatureAttacks(outputData.getAttack1());
        playerData1.setCreatureHPs(outputData.getHitPoints1());
        playerData1.setCreatureIds(outputData.getCreatureIds1());
        playerData1.setHandCardNames(outputData.getPlayerHandNames1());
        playerData1.setHandCardIds(outputData.getPlayerHandIds1());
        playerData1.setHandCardDescriptions(outputData.getPlayerHandDescriptions1());

        playerData2.setCreatureAttacks(outputData.getAttack2());
        playerData2.setCreatureHPs(outputData.getHitPoints2());
        playerData2.setCreatureIds(outputData.getCreatureIds2());
        playerData2.setHandCardNames(outputData.getPlayerHandNames2());
        playerData2.setHandCardIds(outputData.getPlayerHandIds2());
        playerData2.setHandCardDescriptions(outputData.getPlayerHandDescriptions2());

        gameFrameModel.refresh();
        return outputData;
    }

    @Override
    public void notifyTurnChange(String nextPlayer) {
        gameplayScreenModel.setCurrentPlayer(nextPlayer);
        gameFrameModel.refresh();
    }
}
