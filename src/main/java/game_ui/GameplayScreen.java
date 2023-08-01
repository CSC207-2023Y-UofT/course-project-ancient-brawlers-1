package game_ui;

import interface_adapters.controllers.GameStartController;
import interface_adapters.view_models.GameplayScreenModel;
import interface_adapters.view_models.PlayerDataModel;
import interface_adapters.view_models.ScreenUpdateListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameplayScreen extends JPanel implements ActionListener, ScreenUpdateListener {

    private GameplayScreenModel gameplayScreenModel;
    private GameStartController gameStartController;

    public GameplayScreen(GameplayScreenModel gameplayScreenModel, GameStartController gameStartController) {
        this.gameplayScreenModel = gameplayScreenModel;
        this.gameStartController = gameStartController;
    }

    public void updateGameplayScreen() {
        this.setLayout(new GridBagLayout());

        PlayerDataModel playerData1 = gameplayScreenModel.getPlayer1();
        PlayerDataModel playerData2 = gameplayScreenModel.getPlayer2();

        CardButton p1creature1 = new CardButton(playerData1.getCreatureIds().get(0), playerData1.getCreatureNames().get(0));
        CardButton p1creature2 = new CardButton(playerData1.getCreatureIds().get(1), playerData1.getCreatureNames().get(1));
        CardButton p1creature3 = new CardButton(playerData1.getCreatureIds().get(2), playerData1.getCreatureNames().get(2));

        CardButton p2creature1 = new CardButton(playerData2.getCreatureIds().get(0), playerData2.getCreatureNames().get(0));
        CardButton p2creature2 = new CardButton(playerData2.getCreatureIds().get(1), playerData2.getCreatureNames().get(1));
        CardButton p2creature3 = new CardButton(playerData2.getCreatureIds().get(2), playerData2.getCreatureNames().get(2));

        this.add(p1creature1, getGBC(3, 6, 1, 1, 0, 0, 2, 3));
        this.add(p1creature2, getGBC(5, 6, 1, 1, 0, 0, 2, 3));
        this.add(p1creature3, getGBC(7, 6, 1, 1, 0, 0, 2, 3));
        this.add(p2creature1, getGBC(3, 2, 1, 1, 0, 0, 2, 3));
        this.add(p2creature2, getGBC(5, 2, 1, 1, 0, 0, 2, 3));
        this.add(p2creature3, getGBC(7, 2, 1, 1, 0, 0, 2, 3));
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
        updateGameplayScreen();
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
