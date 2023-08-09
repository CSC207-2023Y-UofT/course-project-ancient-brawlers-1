package game_ui;

import interface_adapters.controllers.EndGameController;
import interface_adapters.view_models.ScreenUpdateListener;
import interface_adapters.view_models.VictoryScreenModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VictoryScreen extends JPanel implements ActionListener, ScreenUpdateListener {

    private VictoryScreenModel victoryScreenModel;

    public VictoryScreen(VictoryScreenModel victoryScreenModel){
        this.victoryScreenModel = victoryScreenModel;

        this.setLayout(new GridBagLayout());
    }

    public void updateVictoryScreen() {
        this.removeAll();
        this.revalidate();
        this.repaint();

        Font font = new Font("Herculanum", Font.BOLD, 40);

        JLabel victoryMessage = new JLabel(victoryScreenModel.getWinnerName() + " has won!");
        victoryMessage.setFont(font);
        JButton exitButton = new JButton("Exit to Main Menu");
        exitButton.setFont(font);

        this.add(victoryMessage, getGBC(0, 0, 1, 1, 0, 0, 1, 1));
        this.add(exitButton, getGBC(0, 1, 1, 1, 25, 25, 1, 1));
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
        if (victoryScreenModel.getWinnerName() != null) {
            updateVictoryScreen();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
