import entities.GameState;
import entities.PlayerFactory;
import entities.cards.CardFactory;
import entities.decks.DeckFactory;
import game_ui.*;
import interface_adapters.controllers.GamePrepController;
import interface_adapters.presenters.GamePrepPresenter;
import use_cases.game_preparation_use_case.*;

import javax.swing.*;
import java.awt.*;

public class Main {

    private static final GameState gameState = new GameState();
    private static final CardFactory cardFactory = new CardFactory();
    private static final DeckFactory deckFactory = new DeckFactory();
    private static final PlayerFactory playerFactory = new PlayerFactory();
    private static final CardDataGateway dataAccessor = new TestDataAccessor();

    public static void main(String[] args) {
        GamePrepOutputBoundary gamePrepPresenter = new GamePrepPresenter();
        GamePrepInputBoundary gamePrepInteractor = new GamePrepInteractor(gameState,
                cardFactory, deckFactory, playerFactory, dataAccessor, gamePrepPresenter);
        GamePrepController gamePrepController = new GamePrepController(gamePrepInteractor);


        // Game application window

        JFrame frame = new JFrame("Ancient Brawlers");
        frame.setTitle("Ancient Brawlers");
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the content in this application window
        CardLayout cardLayout = new CardLayout();
        JPanel gamePanel = new JPanel(cardLayout);

        gamePanel.add(new MenuScreen(frame, gamePrepController), "MainMenu");
        gamePanel.add(new SetupScreen(), "Setup");
        gamePanel.add(new GameplayScreen(), "Gameplay");
        gamePanel.add(new MulliganScreen(), "Mulligan");
        gamePanel.add(new TargetSelectionScreen(), "TargetSelection");
        gamePanel.add(new DefendScreen(), "Defend");
        gamePanel.add(new VictoryScreen(), "Victory");

        frame.add(gamePanel, BorderLayout.CENTER);

        //showMainMenuScreen();

        frame.setVisible(true);
    }
}
