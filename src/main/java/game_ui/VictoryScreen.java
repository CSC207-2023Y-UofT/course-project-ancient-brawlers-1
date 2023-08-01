package game_ui;

import interface_adapters.controllers.EndGameController;
import interface_adapters.view_models.GameplayScreenModel;
import interface_adapters.view_models.PlayerDataModel;

import javax.swing.*;
import javax.xml.stream.events.EndDocument;
import java.awt.*;

public class VictoryScreen extends JPanel {
    private EndGameController endGameController;

    public VictoryScreen(EndGameController endGameController){
        this.endGameController = endGameController;
    }

    public void showVictoryScreen(){
        this.setLayout(new GridBagLayout());
    }

    private static GridBagConstraints getGBC(int gridx, int gridy, double weightx, double weighty,
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

    public static void main(String[] args) {
        Font fontLable = new Font("Herculanum", Font.BOLD, 40);
        Font fontButton = new Font("Herculanum", Font.BOLD, 20);
        JFrame frame = new JFrame("Test Victory Screen");
        JPanel panel = new JPanel();

        panel.setLayout(new GridBagLayout());

        JLabel label = new JLabel("Message");
        label.setFont(fontLable);
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(fontButton);
        JButton newGameButton = new JButton("Start New Game");
        newGameButton.setFont(fontButton);

        panel.add(label, getGBC(0, 0, 1, 1, 0, 0, 1, 1));
        panel.add(exitButton, getGBC(0, 1, 1, 1, 25, 25, 1, 1));
        panel.add(newGameButton, getGBC(0, 2, 1, 1, 25, 25, 1, 1));

        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
