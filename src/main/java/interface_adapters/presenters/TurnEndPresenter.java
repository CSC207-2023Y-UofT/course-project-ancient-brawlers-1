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

    /**
     * Updates the players with their updated hands and creatures
     * @param outputData contains the hitpoints, the damage, the creature id, the id's of the cards in a players hand,
     * and description of the cards in a player's hand
     * @return the same responseModel that was passed in
     */

    @Override
    public TurnEndResponseModel showTurnEndScreen(TurnEndResponseModel outputData) {
        PlayerDataModel playerData1 = gameplayScreenModel.getPlayer1();
        PlayerDataModel playerData2 = gameplayScreenModel.getPlayer2();

        playerData1.setCreatureAttacks(outputData.getAttack1());
        playerData1.setCreatureHPs(outputData.getHitPoints1());
        playerData1.setCreatureIds(outputData.getCreatureIds1());
        playerData1.setHandCardNames(outputData.getPlayerHandNames1());
        playerData1.setHandCardIds(outputData.getPlayerHandIds1());

        playerData2.setCreatureAttacks(outputData.getAttack2());
        playerData2.setCreatureHPs(outputData.getHitPoints2());
        playerData2.setCreatureIds(outputData.getCreatureIds2());
        playerData2.setHandCardNames(outputData.getPlayerHandNames2());
        playerData2.setHandCardIds(outputData.getPlayerHandIds2());

        gameFrameModel.refresh();
        return outputData;
    }

    /**
     * Sets the gamePlayScreenModel to the opponent
     * @param nextPlayer contains the opponents name
     */
    @Override
    public void notifyTurnChange(String nextPlayer) {
        gameplayScreenModel.setCurrentPlayer(nextPlayer);
        gameFrameModel.refresh();
    }
}
