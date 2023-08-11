package game_ui;

import data_access.CardImageMapper;
import interface_adapters.GamePrepException;
import interface_adapters.controllers.GamePrepController;
import interface_adapters.controllers.GameStartController;
import interface_adapters.view_models.ScreenUpdateListener;
import interface_adapters.view_models.SetupScreenModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SetupScreen extends JPanel implements ActionListener, ScreenUpdateListener {

    private final SetupScreenModel setupScreenModel;
    private final GamePrepController gamePrepController;
    private final GameStartController gameStartController;
    private final CardImageMapper imageMapper = new CardImageMapper("./src/gameArt");
    private JPanel playerPanel1, playerPanel2;
    private JTextField nameField1, nameField2;
    private Timer timer;
    private int timerStep;

    public SetupScreen(SetupScreenModel setupScreenModel, GamePrepController gamePrepController, GameStartController gameStartController) {
        this.setupScreenModel = setupScreenModel;
        this.gamePrepController = gamePrepController;
        this.gameStartController = gameStartController;
        this.setBackground(new Color(210, 180, 140));
    }

    public void updateSetupScreen() {
        this.removeAll();
        this.revalidate();
        this.repaint();
        setLayout(new GridBagLayout());
        playerPanel1 = new JPanel();
        playerPanel2 = new JPanel();
        playerPanel1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        playerPanel2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        Font font = new Font("Herculanum", Font.BOLD, 30);
        nameField1 = new JTextField(20);
        nameField2 = new JTextField(20);
        nameField1.setFont(font);
        nameField2.setFont(font);

        JButton doneButton = new JButton("Let the battle begin!");
        doneButton.addActionListener(this);
        doneButton.setFont(font);

        JLabel instruction = new JLabel("Please enter both players' names and each select 3 creatures");
        instruction.setFont(font);

        add(instruction, getGBC(0, 0, 1, 1, 0, 0, 2, 1));
        add(playerPanel1, getGBC(0, 1, 1, 1, 0, 0, 1, 1));
        add(playerPanel2, getGBC(1, 1, 1, 1, 0, 0, 1, 1));
        add(doneButton, getGBC(0, 2, 1, 1, 0, 0, 2, 1));

        playerPanel1.setLayout(new GridBagLayout());
        playerPanel2.setLayout(new GridBagLayout());
        playerPanel1.setBackground(new Color(210, 180, 140));
        playerPanel2.setBackground(new Color(210, 180, 140));
        playerPanel1.add(nameField1, getGBC(0, 0, 0.5, 0.5, 240, 0, 3, 1));
        playerPanel2.add(nameField2, getGBC(0, 0, 0.5, 0.5, 240, 0, 3, 1));
        setCardsOnPanel(playerPanel1, setupScreenModel.getCreaturesToChoose());
        setCardsOnPanel(playerPanel2, setupScreenModel.getCreaturesToChoose());
    }

    private void setCardsOnPanel(JPanel playerPanel, List<String> creatures) {
        // Hard coded, because right now the game is fixed at 6 possible creature cards
        List<CardButton> cards = new ArrayList<>();
        for (String name : creatures) {
            CardButton card = new CardButton(-1, name, imageMapper.getImageByName(name));
            card.setOpaque(true);
            card.setPreferredSize(new Dimension(170, 280));
            card.addActionListener(this);
            cards.add(card);
        }
        for (int i = 0; i < cards.size(); i++) {
            if (i < 3) {
                playerPanel.add(cards.get(i), getGBC(i, 1, 2.0, 2.0, 0, 0, 1, 1));
            } else {
                playerPanel.add(cards.get(i), getGBC(i - 3, 2, 2.0, 2.0, 0, 0, 1, 1));
            }
        }
        playerPanel.revalidate();
        playerPanel.repaint();
    }

    private GridBagConstraints getGBC(int gridx, int gridy, double weightx, double weighty,
                                      int ipadx, int ipady, int gridwidth, int gridheight) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = gridx;
        c.gridy = gridy;
        c.weightx = weightx;
        c.weighty = weighty;
        c.ipadx = ipadx;
        c.ipady = ipady;
        c.gridwidth = gridwidth;
        c.gridheight = gridheight;
        return c;
    }

    @Override
    public void onScreenUpdate() {
        if (setupScreenModel.getCreaturesToChoose() != null) {
            updateSetupScreen();
        }
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof CardButton) {
            playerPanel1.revalidate();
            playerPanel1.repaint();
            playerPanel2.revalidate();
            playerPanel2.repaint();
            return;
        }

        List<String> selections1 = new ArrayList<>();
        List<String> selections2 = new ArrayList<>();

        for (Component component : playerPanel1.getComponents()) {
            if (component instanceof CardButton) {
                CardButton cardButton = (CardButton) component;
                if (cardButton.isSelected()) {
                    selections1.add(cardButton.getName());
                }
            }
        }
        for (Component component : playerPanel2.getComponents()) {
            if (component instanceof CardButton) {
                CardButton cardButton = (CardButton) component;
                if (cardButton.isSelected()) {
                    selections2.add(cardButton.getName());
                }
            }
        }
        String nameFieldText1 = nameField1.getText();
        String nameFieldText2 = nameField2.getText();

        System.out.println("Selected button names: " + selections1 + " and " + selections2);
        System.out.println("Name field text for player 1: " + nameFieldText1);
        System.out.println("Name field text for player 2: " + nameFieldText2);

        try {
            gamePrepController.setInitialGameState(nameFieldText1, nameFieldText2, selections1, selections2);
            System.out.println("Initial game state set!");

            performDelayedActions();
        } catch (GamePrepException exception) {
            JOptionPane.showMessageDialog(this, exception.getMessage());
        }
    }

    /**
     * A helper for the sequence of events to trigger with some delays in between.
     */
    private void performDelayedActions() {
        int delay = 1000;
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (timerStep) {
                    case 0:
                        /*
                        This deciding player order part has a problem:
                        - We have set the game board before we call this method.
                        - We fixed the game board such that the bottom half is the first player
                        and the top half is the second player.
                        - The player index has been fixed as well (0 is for bottom half, 1 is for top half)
                        - But switching the player order attempts to swap the bottom and top information
                        - This fails and causes the screen to show inconsistent information
                        - (It fails because the play order has changed but the UI is still treating it like before).
                        Needs a separate branch to work on this issue, but before that we are focusing on other logics.
                         */

                        // gameStartController.decidePlayOrder();
                        // System.out.println("Deciding play order...");
                        break;
                    case 1:
                        gameStartController.startMulligan();
                        System.out.println("Starting first mulligan!");
                        break;
                    default:
                        timer.stop();
                        timerStep = 0;
                        break;
                }
                timerStep++;
            }
        };
        timer = new Timer(delay, actionListener);
        timer.setInitialDelay(0);
        timer.start();
    }
}
