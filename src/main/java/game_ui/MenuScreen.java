package game_ui;

import interface_adapters.controllers.GamePrepController;
import interface_adapters.view_models.ScreenUpdateListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * MenuScreen is the first screen to be shown when the game application starts up.
 * (Besides the Splash screen)
 * This is where the player can start a new game or load a game (still being
 * implemented).
 */
public class MenuScreen extends JPanel implements ActionListener, ScreenUpdateListener {

    private GamePrepController gamePrepController;

    public MenuScreen(GamePrepController gamePrepController) {
        this.gamePrepController = gamePrepController;

        Font font = new Font("Herculanum", Font.BOLD, 30);

        setLayout(new GridBagLayout());
        setBackground(new Color(210, 180, 140));

        ImageIcon titleImage = new ImageIcon("./src/gameArt/GameTitle.png");
        JLabel title = new JLabel(titleImage);

        JButton newGameButton = new JButton("New Game");
        newGameButton.setFont(font);
        newGameButton.setOpaque(true);
        newGameButton.setBackground(Color.ORANGE);

        JButton loadGameButton = new JButton("Load Game");
        loadGameButton.setFont(font);
        loadGameButton.setOpaque(true);
        loadGameButton.setBackground(Color.ORANGE);

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(font);
        exitButton.setOpaque(true);
        exitButton.setBackground(Color.ORANGE);

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 3;
        add(title, c);

        c.gridx = 1;
        c.gridy = 1;
        c.ipadx = 30;
        c.ipady = 30;
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 3;
        add(newGameButton, c);

        c.gridx = 1;
        c.gridy = 2;
        c.ipadx = 30;
        c.ipady = 30;
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 3;
        add(loadGameButton, c);

        c.gridx = 0;
        c.gridy = 3;
        c.ipadx = 60;
        c.ipady = 60;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.anchor = GridBagConstraints.LAST_LINE_START;
        add(exitButton, c);

        // adding listeners
        newGameButton.addActionListener(this);
        loadGameButton.addActionListener(this);
        exitButton.addActionListener(this);
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "New Game":
                System.out.println("New Game button clicked!");
                gamePrepController.createNewGame();
                System.out.println("Controller has been called!");
                break;
            case "Load Game":
                System.out.println("Load Game button clicked!");
                // Perform actions for "Load Game" button
                break;
            case "Exit":
                int option = JOptionPane.showConfirmDialog(this,
                        "Are you sure you want to quit?", "You are about to leave!",
                        JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (option == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
                break;
        }
    }

    @Override
    public void onScreenUpdate() {
        // menu is fixed at the moment
    }
}
