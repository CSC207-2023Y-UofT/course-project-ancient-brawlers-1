import entities.GameState;
import entities.PlayerFactory;
import entities.cards.CardFactory;
import entities.decks.DeckFactory;
import game_ui.*;
import interface_adapters.CardDataMapper;
import interface_adapters.controllers.GamePrepController;
import interface_adapters.controllers.GameStartController;
import interface_adapters.presenters.GamePrepPresenter;
import interface_adapters.presenters.GameStartPresenter;
import interface_adapters.view_models.*;
import use_cases.game_preparation_use_case.*;
import use_cases.game_start_use_case.GameStartInputBoundary;
import use_cases.game_start_use_case.GameStartInteractor;
import use_cases.game_start_use_case.GameStartOutputBoundary;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class AncientBrawlersApp {

    private static final GameFrameModel gameFrameModel = new GameFrameModel();
    private static final SetupScreenModel setupScreenModel = new SetupScreenModel();
    private static final GameplayScreenModel gameplayScreenModel = new GameplayScreenModel();
    private static final MulliganScreenModel mulliganScreenModel = new MulliganScreenModel();

    private static final GameState gameState = new GameState();
    private static final CardFactory cardFactory = new CardFactory();
    private static final DeckFactory deckFactory = new DeckFactory();
    private static final PlayerFactory playerFactory = new PlayerFactory();
    private static final CardDataGateway dataAccessor = new CardDataMapper("./src/utils/creatures.json",
            "./src/utils/actionsAlt.json", "./src/utils/structuresAlt.json",
            "./src/utils/playerDeck1.json", "./src/utils/playerDeck2.json");

    public static void main(String[] args) {
        // Configure the initial state of the application
        gameFrameModel.setCurrentScreen(GameScreenType.MENU);

        GameFrame gameFrame = new GameFrame(gameFrameModel);
        gameFrameModel.addListener(gameFrame);

        GamePrepController gamePrepController = getGamePrepController();
        GameStartController gameStartController = getGameStartController();

        MenuScreen menuScreen = new MenuScreen(gamePrepController);
        SetupScreen setupScreen = new SetupScreen(setupScreenModel, gamePrepController, gameStartController);
        GameplayScreen gameplayScreen = new GameplayScreen(gameplayScreenModel, gameStartController);
        MulliganScreen mulliganScreen = new MulliganScreen(mulliganScreenModel, gameStartController);

        gameFrame.addScreen(menuScreen, GameScreenType.MENU);
        gameFrame.addScreen(setupScreen, GameScreenType.SETUP);
        gameFrame.addScreen(gameplayScreen, GameScreenType.GAMEPLAY);
        gameFrame.addScreen(mulliganScreen, GameScreenType.MULLIGAN);

        gameFrame.setVisible(true);
    }

    private static GamePrepController getGamePrepController() {
        GamePrepOutputBoundary presenter = new GamePrepPresenter(gameFrameModel, setupScreenModel, gameplayScreenModel);
        GamePrepInputBoundary interactor = new GamePrepInteractor(gameState, cardFactory, deckFactory, playerFactory,
                dataAccessor, presenter);
        return new GamePrepController(interactor);
    }

    private static GameStartController getGameStartController() {
        GameStartOutputBoundary presenter = new GameStartPresenter(gameFrameModel, gameplayScreenModel, mulliganScreenModel);
        GameStartInputBoundary interactor = new GameStartInteractor(gameState, presenter);
        return new GameStartController(interactor);
    }
}
