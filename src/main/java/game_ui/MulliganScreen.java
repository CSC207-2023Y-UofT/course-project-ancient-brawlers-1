package game_ui;

import interface_adapters.GamePrepException;
import interface_adapters.controllers.GamePrepController;
import interface_adapters.view_models.MulliganScreenModel;
import interface_adapters.view_models.ScreenUpdateListener;
import interface_adapters.view_models.SetupScreenModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import interface_adapters.controllers.GameStartController;

public class MulliganScreen extends JPanel implements ActionListener, ScreenUpdateListener {
    // OVERALL VIEW
    // jlabel with text - select which cards to replace
    // three cards in the middle
    // confirm button

    // Functionality
    // Lift, Seek, etc..
    // 22-feature-

    private GameStartController gameStartController;
    private MulliganScreenModel mulliganScreenModel;

    public MulliganScreen(MulliganScreenModel mulliganScreenModel, GameStartController gameStartController) {
        this.gameStartController = gameStartController;
        this.mulliganScreenModel = mulliganScreenModel;
    }

    public void updateMulliganScreen() {
        setCardsOnPanel(mulliganScreenModel.getCardNames(), mulliganScreenModel.getCardIds());
        setLayout(new GridBagLayout());

        JLabel title = new JLabel("Select cards");

        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(this);
        Font font = new Font("Herculanum", Font.BOLD, 30);
        confirmButton.setFont(font);
        this.add(confirmButton, getGBC(0, 3, 1, 1, 40, 20, 3, 1));
        this.add(title, getGBC(0, 0, 1, 1, 40, 20, 3, 1));
    }

    private void setCardsOnPanel(List<String> cardNames, List<Integer> cardIds) {
        CardButton card1 = new CardButton(cardIds.get(0), cardNames.get(0));
        CardButton card2 = new CardButton(cardIds.get(1), cardNames.get(1));
        CardButton card3 = new CardButton(cardIds.get(2), cardNames.get(2));

        this.add(card1, getGBC(0, 1, 1, 1, 80, 200, 1, 2));
        this.add(card2, getGBC(1, 1, 1, 1, 80, 200, 1, 2));
        this.add(card3, getGBC(2, 1, 1, 1, 80, 200, 1, 2));
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
        if (mulliganScreenModel.getCardIds() != null || mulliganScreenModel.getCardNames() != null) {
            updateMulliganScreen();
        }   
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e);
    }

}
