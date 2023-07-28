package game_ui;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel gamePanel;

    public GameFrame() {
        // Set application window properties
        setTitle("Ancient Brawlers");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the content in this application window
        cardLayout = new CardLayout();
        gamePanel = new JPanel(cardLayout);

        // gamePanel.add(new MenuScreen(), "MainMenu");
        gamePanel.add(new SetupScreen(), "Setup");
        gamePanel.add(new GameplayScreen(), "Gameplay");
        gamePanel.add(new MulliganScreen(), "Mulligan");
        gamePanel.add(new TargetSelectionScreen(), "TargetSelection");
        gamePanel.add(new DefendScreen(), "Defend");
        gamePanel.add(new VictoryScreen(), "Victory");

        add(gamePanel, BorderLayout.CENTER);

        //showMainMenuScreen();

        setVisible(true);
    }

    public void showMainMenuScreen() {
        cardLayout.show(gamePanel, "MainMenu");
    }

    public void showGameSetupScreen() {
        cardLayout.show(gamePanel, "Setup");
    }

    public void showGameplayScreen() {
        cardLayout.show(gamePanel, "Gameplay");
    }

    public void showMulliganScreen() {
        cardLayout.show(gamePanel, "Mulligan");
    }

    public void showTargetSelectionScreen() {
        cardLayout.show(gamePanel, "TargetSelection");
    }

    public void showDefendScreen() {
        cardLayout.show(gamePanel, "Defend");
    }

    public void showVictoryScreen() {
        cardLayout.show(gamePanel, "Victory");
    }
}
