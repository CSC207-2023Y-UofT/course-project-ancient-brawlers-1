package interface_adapters.presenters;

import interface_adapters.view_models.GameFrameModel;
import interface_adapters.view_models.GameplayScreenModel;
import interface_adapters.view_models.PlayerDataModel;
import use_cases.turn_start_use_case.CreatureStatsUpdateModel;
import use_cases.turn_start_use_case.DrawCardOutputModel;
import use_cases.turn_start_use_case.TriggerEffectUpdateModel;
import use_cases.turn_start_use_case.TurnStartOutputBoundary;

public class TurnStartPresenter implements TurnStartOutputBoundary {

    private final GameFrameModel frameModel;
    private final GameplayScreenModel gameplayScreenModel;

    public TurnStartPresenter(GameFrameModel frameModel, GameplayScreenModel gameplayScreenModel) {
        this.frameModel = frameModel;
        this.gameplayScreenModel = gameplayScreenModel;
    }

    @Override
    public DrawCardOutputModel showDrawResult(DrawCardOutputModel outputData) {
        PlayerDataModel playerData;
        if (outputData.getPlayerIndex() == 0) {
            playerData = gameplayScreenModel.getPlayer1();
        } else {
            playerData = gameplayScreenModel.getPlayer2();
        }
        playerData.getHandCardNames().addAll(outputData.getNewCardNames());
        playerData.getHandCardIds().addAll(outputData.getNewCardIds());
        playerData.getHandCardDescriptions().addAll(outputData.getNewCardDescriptions());
        frameModel.refresh();
        return null;
    }

    @Override
    public CreatureStatsUpdateModel showClearBuffs(CreatureStatsUpdateModel outputData) {
        PlayerDataModel playerData1 = gameplayScreenModel.getPlayer1();
        PlayerDataModel playerData2 = gameplayScreenModel.getPlayer2();

        playerData1.setCreatureHPs(outputData.getHitPoints1());
        playerData1.setCreatureAttacks(outputData.getAttacks1());
        playerData2.setCreatureHPs(outputData.getHitPoints2());
        playerData2.setCreatureAttacks(outputData.getAttacks2());
        frameModel.refresh();
        return null;
    }

    @Override
    public TriggerEffectUpdateModel showEffectUpdates(TriggerEffectUpdateModel outputData) {
        PlayerDataModel playerData1 = gameplayScreenModel.getPlayer1();
        PlayerDataModel playerData2 = gameplayScreenModel.getPlayer2();

        CreatureStatsUpdateModel allStats = outputData.getAllCreatureStats();
        playerData1.setCreatureHPs(allStats.getHitPoints1());
        playerData1.setCreatureAttacks(allStats.getAttacks1());
        playerData2.setCreatureHPs(allStats.getHitPoints2());
        playerData2.setCreatureAttacks(allStats.getAttacks2());

        if (allStats.getPlayerIndex() == 0) {
            playerData1.setHandCardIds(outputData.getFinalHandIds());
            playerData1.setHandCardNames(outputData.getFinalHandNames());
            playerData1.setHandCardDescriptions(outputData.getFinalHandDescs());
        } else {
            playerData2.setHandCardIds(outputData.getFinalHandIds());
            playerData2.setHandCardNames(outputData.getFinalHandNames());
            playerData2.setHandCardDescriptions(outputData.getFinalHandDescs());
        }
        frameModel.refresh();
        return null;
    }
}
