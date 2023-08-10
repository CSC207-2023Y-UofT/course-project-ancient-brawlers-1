package game_ui;

import interface_adapters.view_models.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameFrame extends JFrame implements FrameUpdateListener {

    private GameFrameModel gameFrameModel;
    private CardLayout cardLayout;
    private JPanel gamePanel;
    private List<ScreenUpdateListener> listeners = new ArrayList<>();

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
