package game_ui;

import interface_adapters.GamePrepException;
import interface_adapters.controllers.GamePrepController;
import interface_adapters.controllers.GameStartController;
import interface_adapters.controllers.TurnEndController;
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
    private JPanel playerPanel1, playerPanel2;
    private JTextField nameField1, nameField2;
    private Timer timer;
    private int timerStep;

    public SetupScreen(SetupScreenModel setupScreenModel, GamePrepController gamePrepController, GameStartController gameStartController) {
        this.setupScreenModel = setupScreenModel;
        this.gamePrepController = gamePrepController;
        this.gameStartController = gameStartController;
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
        playerPanel1.add(nameField1, getGBC(0, 0, 1, 1, 240, 0, 3, 1));
        playerPanel2.add(nameField2, getGBC(0, 0, 1, 1, 240, 0, 3, 1));
        setCardsOnPanel(playerPanel1, setupScreenModel.getCreaturesToChoose());
        setCardsOnPanel(playerPanel2, setupScreenModel.getCreaturesToChoose());
    }

    private void setCardsOnPanel(JPanel playerPanel, List<String> creatures) {
        // Hard coded, because right now the game is fixed at 6 possible creature cards
        CardButton card1 = new CardButton(-1, creatures.get(0));
        CardButton card2 = new CardButton(-1, creatures.get(1));
        CardButton card3 = new CardButton(-1, creatures.get(2));
        CardButton card4 = new CardButton(-1, creatures.get(3));
        CardButton card5 = new CardButton(-1, creatures.get(4));
        CardButton card6 = new CardButton(-1, creatures.get(5));
        playerPanel.add(card1, getGBC(0, 1, 1, 1, 140, 240, 1, 1));
        playerPanel.add(card2, getGBC(1, 1, 1, 1, 140, 240, 1, 1));
        playerPanel.add(card3, getGBC(2, 1, 1, 1, 140, 240, 1, 1));
        playerPanel.add(card4, getGBC(0, 2, 1, 1, 140, 240, 1, 1));
        playerPanel.add(card5, getGBC(1, 2, 1, 1, 140, 240, 1, 1));
        playerPanel.add(card6, getGBC(2, 2, 1, 1, 140, 240, 1, 1));
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
        updateSetupScreen();
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
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
            performDelayedActions(nameFieldText1, nameFieldText2, selections1, selections2);
        } catch (GamePrepException exception) {
            JOptionPane.showMessageDialog(this, exception.getMessage());
        }
    }

    /**
     * A helper for the sequence of events to trigger with some delays in between.
     */
    private void performDelayedActions(String nameFieldText1, String nameFieldText2,
                                       List<String> selections1, List<String> selections2) {
        int delay = 1000;
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (timerStep) {
                    case 0:
                        gamePrepController.setInitialGameState(nameFieldText1, nameFieldText2, selections1, selections2);
                        System.out.println("Initial game state set!");
                        break;
                    case 1:
                        gameStartController.decidePlayOrder();
                        System.out.println("Deciding play order...");
                        break;
                    case 2:
                        gameStartController.startMulligan();
                        System.out.println("Starting first mulligan!");
                        break;
                    default:
                        timer.stop();
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
