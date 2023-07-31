import entities.GameState;
import entities.PlayerFactory;
import entities.cards.CardFactory;
import entities.decks.DeckFactory;
import game_ui.*;
import interface_adapters.CardDataMapper;
import interface_adapters.controllers.GamePrepController;
import interface_adapters.presenters.GamePrepPresenter;
import interface_adapters.view_models.GameFrameModel;
import interface_adapters.view_models.GameScreenType;
import interface_adapters.view_models.GameplayScreenModel;
import interface_adapters.view_models.SetupScreenModel;
import use_cases.game_preparation_use_case.*;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class AncientBrawlersApp {

    private static final GameFrameModel gameFrameModel = new GameFrameModel();
    private static final SetupScreenModel setupScreenModel = new SetupScreenModel();
    private static final GameplayScreenModel gameplayScreenModel = new GameplayScreenModel();

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

        MenuScreen menuScreen = new MenuScreen(getGamePrepController());
        SetupScreen setupScreen = new SetupScreen(setupScreenModel);

        gameFrame.addScreen(menuScreen, GameScreenType.MENU);
        gameFrame.addScreen(setupScreen, GameScreenType.SETUP);

        gameFrame.setVisible(true);
    }

    private static GamePrepController getGamePrepController() {
        GamePrepOutputBoundary presenter = new GamePrepPresenter(gameFrameModel, setupScreenModel, gameplayScreenModel);
        GamePrepInputBoundary interactor = new GamePrepInteractor(gameState, cardFactory, deckFactory, playerFactory,
                dataAccessor, presenter);
        return new GamePrepController(interactor);
    }
}
