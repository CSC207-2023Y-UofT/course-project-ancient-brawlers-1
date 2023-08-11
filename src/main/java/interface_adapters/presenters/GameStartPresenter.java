package interface_adapters.presenters;

import interface_adapters.view_models.GameFrameModel;
import interface_adapters.view_models.GameScreenType;
import interface_adapters.view_models.GameplayScreenModel;
import interface_adapters.view_models.MulliganScreenModel;
import use_cases.game_start_use_case.GameStartOutputBoundary;
import use_cases.game_start_use_case.GameStartResponseModel;

import java.util.ArrayList;
import java.util.List;

public class GameStartPresenter implements GameStartOutputBoundary {

    private final GameFrameModel frameModel;
    private final GameplayScreenModel gameplayScreenModel;
    private final MulliganScreenModel mulliganScreenModel;

    public GameStartPresenter(GameFrameModel frameModel, GameplayScreenModel gameplayScreenModel,
                              MulliganScreenModel mulliganScreenModel) {
        this.frameModel = frameModel;
        this.gameplayScreenModel = gameplayScreenModel;
        this.mulliganScreenModel = mulliganScreenModel;
    }

    @Override
    public void displayPlayerOrder(String message, String playerName) {
        gameplayScreenModel.setGameMessage(message);
        gameplayScreenModel.setCurrentPlayer(playerName);
        frameModel.refresh();
    }

    /**
     * Sends instruction to display the MulliganScreen, which is for the mulligan
     * phase of the game.
     * 
     * @param outputData the response model that contains card data representing
     *                   the cards to be replaced.
     * @return the same responseModel that was originally passed in
     */
    @Override
    public GameStartResponseModel showMulliganScreen(GameStartResponseModel outputData) {
        mulliganScreenModel.setCardIds(outputData.getCardIds());
        mulliganScreenModel.setCardNames(outputData.getCardNames());
        frameModel.setCurrentScreen(GameScreenType.MULLIGAN);
        return outputData;
    }

    /**
     * Sends instruction to exit Mulligan Screen and back to Gameplay Screen,
     * while also updating the screen with the player's mulligan results.
     * 
     * @param outputData the response model that contains the finalized card data.
     * @return the same responseModel that was originally passed in
     */
    @Override
    public GameStartResponseModel exitMulliganScreen(GameStartResponseModel outputData) {
        List<String> handCardNames = new ArrayList<>();
        handCardNames.addAll(outputData.getCardNames());
        handCardNames.addAll(outputData.getBonusCardNames());
        List<Integer> handCardIds = new ArrayList<>();
        handCardIds.addAll(outputData.getCardIds());
        handCardIds.addAll(outputData.getBonusCardIds());
        List<String> handCardDescs = new ArrayList<>();
        handCardDescs.addAll(outputData.getCardDescs());
        handCardDescs.addAll(outputData.getBonusCardDescs());

        if (outputData.getPlayerIndex() == 0) {
            gameplayScreenModel.getPlayer1().setHandCardNames(handCardNames);
            gameplayScreenModel.getPlayer1().setHandCardIds(handCardIds);
            gameplayScreenModel.getPlayer1().setHandCardDescriptions(handCardDescs);
        } else {
            gameplayScreenModel.getPlayer2().setHandCardNames(handCardNames);
            gameplayScreenModel.getPlayer2().setHandCardIds(handCardIds);
            gameplayScreenModel.getPlayer2().setHandCardDescriptions(handCardDescs);
        }
        frameModel.setCurrentScreen(GameScreenType.GAMEPLAY);
        return outputData;
    }
}
