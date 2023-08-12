package game_ui;

import interface_adapters.view_models.FrameUpdateListener;
import interface_adapters.view_models.GameFrameModel;
import interface_adapters.view_models.GameScreenType;
import interface_adapters.view_models.ScreenUpdateListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * GameFrame is the game application window. It contains all types of screens
 * to be shown during the gameplay.
 * This class is an observer of the {@code GameFrameModel} class in the interface
 * layer.
 */
public class GameFrame extends JFrame implements FrameUpdateListener {

    private GameFrameModel gameFrameModel;
    private CardLayout cardLayout;
    private JPanel gamePanel;
    private List<ScreenUpdateListener> listeners = new ArrayList<>();

    /**
     * Creates a GameFrame with default settings.
     * @param gameFrameModel the view model that determines what this UI component
     *                       should display.
     */
    public GameFrame(GameFrameModel gameFrameModel) {
        this.gameFrameModel = gameFrameModel;

        // Set application window properties
        setTitle("Ancient Brawlers");
        setSize(1200, 800);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the content in this application window
        cardLayout = new CardLayout();
        gamePanel = new JPanel(cardLayout);
        add(gamePanel, BorderLayout.CENTER);
    }

    public void addScreen(JPanel screen, GameScreenType screenType) {
        gamePanel.add(screen, screenType.name());
        if (screen instanceof ScreenUpdateListener) {
            listeners.add((ScreenUpdateListener) screen);
        }
    }

    @Override
    public void onFrameUpdate() {
        cardLayout.show(gamePanel, gameFrameModel.getCurrentScreen().name());
        for (ScreenUpdateListener listener : listeners) {
            listener.onScreenUpdate();
        }
        System.out.println("Current screen: " + gameFrameModel.getCurrentScreen().name());
    }
}
