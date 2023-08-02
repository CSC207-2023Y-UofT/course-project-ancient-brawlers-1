package interface_adapters.controllers;

import use_cases.game_preparation_use_case.GamePrepInputBoundary;
import use_cases.game_preparation_use_case.GamePrepRequestModel;
import use_cases.game_preparation_use_case.GamePrepResponseModel;

import java.util.List;

public class GamePrepController {

    final GamePrepInputBoundary gamePrepInteractor;

    public GamePrepController(GamePrepInputBoundary gamePrepInteractor) {
        this.gamePrepInteractor = gamePrepInteractor;
    }

    /**
     * Asks the players for required info to create the new game.
     */
    public void createNewGame() {
        gamePrepInteractor.promptPlayerInfo();
    }

    /**
     * Takes the info players entered and call the interactor method to finally
     * set up and display the game screen.
     */
    public GamePrepResponseModel setInitialGameState(String player1, String player2,
                                                     List<String> creatures1, List<String> creatures2) {
        GamePrepRequestModel requestModel = new GamePrepRequestModel(player1, player2, creatures1, creatures2);
        return gamePrepInteractor.processPlayerInfo(requestModel);
    }
}
