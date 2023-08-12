import data_access.CardDataMapper;
import entities.GameState;
import entities.PlayerFactory;
import entities.cards.CardFactory;
import entities.decks.DeckFactory;
import game_ui.*;
import interface_adapters.controllers.*;
import interface_adapters.presenters.*;
import interface_adapters.view_models.*;
import use_cases.attack_use_case.AttackInputBoundary;
import use_cases.attack_use_case.AttackInteractor;
import use_cases.attack_use_case.AttackOutputBoundary;
import use_cases.death_detector_use_case.DeathDetectorInputBoundary;
import use_cases.death_detector_use_case.DeathDetectorInteractor;
import use_cases.death_detector_use_case.DeathDetectorOutputBoundary;
import use_cases.game_preparation_use_case.CardDataGateway;
import use_cases.game_preparation_use_case.GamePrepInputBoundary;
import use_cases.game_preparation_use_case.GamePrepInteractor;
import use_cases.game_preparation_use_case.GamePrepOutputBoundary;
import use_cases.game_start_use_case.GameStartInputBoundary;
import use_cases.game_start_use_case.GameStartInteractor;
import use_cases.game_start_use_case.GameStartOutputBoundary;
import use_cases.play_card_use_case.PlayCardInputBoundary;
import use_cases.play_card_use_case.PlayCardInteractor;
import use_cases.play_card_use_case.PlayCardOutputBoundary;
import use_cases.turn_end_use_case.TurnEndInputBoundary;
import use_cases.turn_end_use_case.TurnEndInteractor;
import use_cases.turn_end_use_case.TurnEndOutputBoundary;
import use_cases.turn_start_use_case.TurnStartInputBoundary;
import use_cases.turn_start_use_case.TurnStartInteractor;
import use_cases.turn_start_use_case.TurnStartOutputBoundary;
import use_cases.win_loss_use_case.WinLossInputBoundary;
import use_cases.win_loss_use_case.WinLossInteractor;
import use_cases.win_loss_use_case.WinLossOutputBoundary;

import javax.swing.*;

/**
 * This class configures and runs the game.
 */
public class AncientBrawlersApp {

    private static final GameFrameModel gameFrameModel = new GameFrameModel();
    private static final SetupScreenModel setupScreenModel = new SetupScreenModel();
    private static final GameplayScreenModel gameplayScreenModel = new GameplayScreenModel();
    private static final MulliganScreenModel mulliganScreenModel = new MulliganScreenModel();
    private static final DefendScreenModel defendScreenModel = new DefendScreenModel();
    private static final TargetSelectScreenModel targetScreenModel = new TargetSelectScreenModel();
    private static final VictoryScreenModel victoryScreenModel = new VictoryScreenModel();

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

        // This pause presenter may be combined in the future with the use case
        // that does game saves
        GamePausePresenter pausePresenter = new GamePausePresenter(gameFrameModel);
        PauseScreen pauseScreen = new PauseScreen(pausePresenter);

        // These are the well determined components
        GamePrepController gamePrepController = getGamePrepController();
        GameStartController gameStartController = getGameStartController();
        TurnStartController turnStartController = getTurnStartController();
        AttackController attackController = getAttackController();
        TurnEndController turnEndController = getTurnEndController();
        PlayCardController playCardController = getPlayCardController();
        EndGameController endGameController = getEndGameController();

        MenuScreen menuScreen = new MenuScreen(gamePrepController);
        SetupScreen setupScreen = new SetupScreen(setupScreenModel, gamePrepController, gameStartController);
        GameplayScreen gameplayScreen = new GameplayScreen(gameplayScreenModel, gameStartController, attackController,
                turnStartController, turnEndController, playCardController, endGameController, pausePresenter);
        MulliganScreen mulliganScreen = new MulliganScreen(mulliganScreenModel, gameStartController, turnStartController, turnEndController);
        DefendScreen defendScreen = new DefendScreen(defendScreenModel, attackController, turnEndController, turnStartController, endGameController);
        TargetSelectionScreen targetSelectionScreen = new TargetSelectionScreen(targetScreenModel, playCardController, endGameController);
        VictoryScreen victoryScreen = new VictoryScreen(victoryScreenModel, pausePresenter);

        gameFrame.addScreen(new SplashScreen(), GameScreenType.SPLASH);  // A fancy intro, can be removed anytime
        gameFrame.addScreen(menuScreen, GameScreenType.MENU);
        gameFrame.addScreen(setupScreen, GameScreenType.SETUP);
        gameFrame.addScreen(gameplayScreen, GameScreenType.GAMEPLAY);
        gameFrame.addScreen(mulliganScreen, GameScreenType.MULLIGAN);
        gameFrame.addScreen(defendScreen, GameScreenType.DEFEND);
        gameFrame.addScreen(targetSelectionScreen, GameScreenType.TARGET_SELECTION);
        gameFrame.addScreen(victoryScreen, GameScreenType.VICTORY);
        gameFrame.addScreen(pauseScreen, GameScreenType.PAUSE);

        gameFrame.setVisible(true);

        // This part can be removed along with the Splash screen.
        // For now, it is kept to make the program feel more like a game
        Timer timer = new Timer(22000, e -> {
            gameFrameModel.setCurrentScreen(GameScreenType.MENU); // Switch to the main menu
        });
        timer.setRepeats(false);
        timer.start();
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

    private static TurnStartController getTurnStartController() {
        TurnStartOutputBoundary presenter = new TurnStartPresenter(gameFrameModel, gameplayScreenModel);
        TurnStartInputBoundary interactor = new TurnStartInteractor(gameState, presenter);
        return new TurnStartController(interactor);
    }

    private static AttackController getAttackController() {
        AttackOutputBoundary presenter = new AttackPresenter(gameFrameModel, gameplayScreenModel, defendScreenModel);
        AttackInputBoundary interactor = new AttackInteractor(gameState, presenter);
        return new AttackController(interactor);
    }

    private static TurnEndController getTurnEndController() {
        TurnEndOutputBoundary presenter = new TurnEndPresenter(gameFrameModel, gameplayScreenModel);
        TurnEndInputBoundary interactor = new TurnEndInteractor(gameState, presenter);
        return new TurnEndController(interactor);
    }

    private static PlayCardController getPlayCardController() {
        PlayCardOutputBoundary presenter = new PlayCardPresenter(gameFrameModel, gameplayScreenModel, targetScreenModel);
        PlayCardInputBoundary interactor = new PlayCardInteractor(gameState, presenter);
        return new PlayCardController(interactor);
    }

    private static EndGameController getEndGameController() {
        DeathDetectorOutputBoundary deathPresenter = new DeathDetectorPresenter(gameFrameModel, gameplayScreenModel);
        DeathDetectorInputBoundary deathInteractor = new DeathDetectorInteractor(gameState, deathPresenter);
        WinLossOutputBoundary winLossPresenter = new WinLossPresenter(gameFrameModel, victoryScreenModel);
        WinLossInputBoundary winLossInteractor = new WinLossInteractor(gameState, winLossPresenter);
        return new EndGameController(deathInteractor, winLossInteractor);
    }
}
