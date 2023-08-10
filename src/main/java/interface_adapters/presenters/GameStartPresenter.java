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
     * Shows the MulliganScreen which lets players choose which cards they would like to discard from their hand into
     * their deck in exchange for a new card from the deck
     * @param outputData the response model that contains, the cardId's, card names, and the cards that they could
     * potentially swap out
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
     * Exists the Mulligan Screen back to the Gameplay Screen with players updated hand from the mulligan screen
     * @param outputData the response model that contains, the cardId's, card names, and the cards that they
     * potentially swapped out
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

        if (outputData.getPlayerIndex() == 0) {
            gameplayScreenModel.getPlayer1().setHandCardNames(handCardNames);
            gameplayScreenModel.getPlayer1().setHandCardIds(handCardIds);
        } else {
            gameplayScreenModel.getPlayer2().setHandCardNames(handCardNames);
            gameplayScreenModel.getPlayer2().setHandCardIds(handCardIds);
        }
        frameModel.setCurrentScreen(GameScreenType.GAMEPLAY);
        return outputData;
    }
}
