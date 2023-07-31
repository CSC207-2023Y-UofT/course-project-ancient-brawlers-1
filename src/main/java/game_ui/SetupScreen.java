package game_ui;

import interface_adapters.view_models.ScreenUpdateListener;
import interface_adapters.view_models.SetupScreenModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SetupScreen extends JPanel implements ActionListener, ScreenUpdateListener {

    private SetupScreenModel setupScreenModel;

    public SetupScreen(SetupScreenModel setupScreenModel) {
        this.setupScreenModel = setupScreenModel;
        // Font font = new Font("Herculanum", Font.BOLD, 30);
    }

    public void updateSetupScreen() {
        setLayout(new GridBagLayout());
        JPanel playerPanel1 = new JPanel();
        JPanel playerPanel2 = new JPanel();

        JTextField nameField1 = new JTextField(20);
        JTextField nameField2 = new JTextField(20);
        JButton doneButton = new JButton("Done");

        List<String> creatures = setupScreenModel.getCreaturesToChoose();

        add(playerPanel1, getGBC(0, 0, 1, 1, 0, 0, 3, 4));
        add(playerPanel2, getGBC(3, 0, 1, 1, 0, 0, 3, 4));
        add(doneButton, getGBC(2, 4, 0.1, 0.1, 0, 0, 2, 1));

        playerPanel1.setLayout(new GridBagLayout());
        playerPanel2.setLayout(new GridBagLayout());

        playerPanel1.add(nameField1, getGBC(0, 0, 1, 1, 0, 0, 3, 1));
        playerPanel2.add(nameField2, getGBC(0, 0, 1, 1, 0, 0, 3, 1));

        setCardsOnPanel(playerPanel1, setupScreenModel.getCreaturesToChoose());
        setCardsOnPanel(playerPanel2, setupScreenModel.getCreaturesToChoose());
    }

    private void setCardsOnPanel(JPanel playerPanel, List<String> creatures) {
        CardButton card1 = new CardButton(-1, creatures.get(0));
        CardButton card2 = new CardButton(-1, creatures.get(1));
        CardButton card3 = new CardButton(-1, creatures.get(2));
        CardButton card4 = new CardButton(-1, creatures.get(3));
        CardButton card5 = new CardButton(-1, "Unknown");
        CardButton card6 = new CardButton(-1, "Unknown");

        playerPanel.add(card1, getGBC(0, 1, 1, 1, 80, 200, 1, 2));
        playerPanel.add(card2, getGBC(1, 1, 1, 1, 80, 200, 1, 2));
        playerPanel.add(card3, getGBC(2, 1, 1, 1, 80, 200, 1, 2));
        playerPanel.add(card4, getGBC(0, 3, 1, 1, 80, 200, 1, 2));
        playerPanel.add(card5, getGBC(1, 3, 1, 1, 80, 200, 1, 2));
        playerPanel.add(card6, getGBC(2, 3, 1, 1, 80, 200, 1, 2));
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

    }
}
