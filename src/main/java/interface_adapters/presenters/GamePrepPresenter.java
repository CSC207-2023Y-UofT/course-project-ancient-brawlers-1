package interface_adapters.presenters;

import interface_adapters.GamePrepException;
import interface_adapters.view_models.*;
import use_cases.game_preparation_use_case.GamePrepOutputBoundary;
import use_cases.game_preparation_use_case.GamePrepResponseModel;

import java.util.List;

public class GamePrepPresenter implements GamePrepOutputBoundary {

    private final GameFrameModel frameModel;
    private final SetupScreenModel setupScreenModel;
    private final GameplayScreenModel gameplayScreenModel;

    public GamePrepPresenter(GameFrameModel frameModel, SetupScreenModel setupScreenModel,
                             GameplayScreenModel gameplayScreenModel) {
        this.frameModel = frameModel;
        this.setupScreenModel = setupScreenModel;
        this.gameplayScreenModel = gameplayScreenModel;
    }

    /**
     * Display a screen to ask both players for their names and let them choose
     * the creatures for their game.
     *
     * @param creatureNames the names of the creatures that can be selected for
     *                      the game.
     */
    @Override
    public void showGamePreparationScreen(List<String> creatureNames) {
        setupScreenModel.setCreaturesToChoose(creatureNames);
        frameModel.setCurrentScreen(GameScreenType.SETUP);
    }

    /**
     * Notify the players that the game creation process cannot go through due
     * to unexpected inputs.
     * This could be empty player names, or not exactly 3 selected creatures.
     *
     * @param message the error message for the players.
     * @return the GamePrepResponseModel to match the method signature of the
     * interactor method calling this method.
     */
    @Override
    public GamePrepResponseModel displayErrorMessage(String message) {
        throw new GamePrepException(message);
    }

    /**
     * Display the main gameplay screen and show the initial game data given in
     * the {@code outputData}.
     *
     * @param outputData the data model containing the player names and all the
     *                   stats of their selected creatures (such as hit-points
     *                   and attack damage, and also some internal ids for the
     *                   presenter to identify specific creatures).
     * @return the same GamePrepResponseModel that is passed to this method.
     */
    @Override
    public GamePrepResponseModel showGameplayScreen(GamePrepResponseModel outputData) {
        PlayerDataModel playerData1 = new PlayerDataModel(outputData.getPlayerName1(), outputData.getCreatureNames1(),
                outputData.getCreatureIds1(), outputData.getHitPoints1(), outputData.getAttacks1());
        PlayerDataModel playerData2 = new PlayerDataModel(outputData.getPlayerName2(), outputData.getCreatureNames2(),
                outputData.getCreatureIds2(), outputData.getHitPoints2(), outputData.getAttacks2());
        gameplayScreenModel.setPlayer1(playerData1);
        gameplayScreenModel.setPlayer2(playerData2);
        frameModel.setCurrentScreen(GameScreenType.GAMEPLAY);
        return outputData;
    }
}
