package game_ui;

import data_access.CardImageMapper;
import interface_adapters.controllers.TurnEndController;
import interface_adapters.controllers.TurnStartController;
import interface_adapters.view_models.MulliganScreenModel;
import interface_adapters.view_models.ScreenUpdateListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import interface_adapters.controllers.GameStartController;

public class MulliganScreen extends JPanel implements ActionListener, ScreenUpdateListener {

    private GameStartController gameStartController;
    private TurnStartController turnStartController;
    private TurnEndController turnEndController;
    private CardImageMapper imageMapper = new CardImageMapper("./src/gameArt");
    private MulliganScreenModel mulliganScreenModel;
    private boolean bothMulliansComplete = false;
    private Timer timer;

    public MulliganScreen(MulliganScreenModel mulliganScreenModel, GameStartController gameStartController, TurnStartController turnStartController, TurnEndController turnEndController) {
        this.gameStartController = gameStartController;
        this.mulliganScreenModel = mulliganScreenModel;
        this.turnStartController = turnStartController;
        this.turnEndController = turnEndController;
    }

    public void updateMulliganScreen() {
        this.removeAll();
        this.revalidate();
        this.repaint();

        this.setLayout(new GridBagLayout());
        Font font = new Font("Herculanum", Font.BOLD, 30);

        JLabel message = new JLabel("Initial Hand: Choose cards to replace");
        message.setFont(font);
        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(this);
        confirmButton.setFont(font);

        this.add(message, getGBC(0, 0, 1, 1, 80, 20, 3, 1));
        setCardsOnPanel(mulliganScreenModel.getCardNames(), mulliganScreenModel.getCardIds());
        this.add(confirmButton, getGBC(0, 2, 1, 1, 80, 20, 3, 1));
    }

    private void setCardsOnPanel(List<String> cardNames, List<Integer> cardIds) {
        CardButton card1 = new CardButton(cardIds.get(0), cardNames.get(0), imageMapper.getImageByName(cardNames.get(0)));
        CardButton card2 = new CardButton(cardIds.get(1), cardNames.get(1), imageMapper.getImageByName(cardNames.get(1)));
        CardButton card3 = new CardButton(cardIds.get(2), cardNames.get(2), imageMapper.getImageByName(cardNames.get(2)));
        card1.setPreferredSize(new Dimension(220, 360));
        card2.setPreferredSize(new Dimension(220, 360));
        card3.setPreferredSize(new Dimension(220, 360));
        card1.setOpaque(true);
        card2.setOpaque(true);
        card3.setOpaque(true);
        card1.addActionListener(this);
        card2.addActionListener(this);
        card3.addActionListener(this);
        this.add(card1, getGBC(0, 1, 1, 1, 10, 10, 1, 1));
        this.add(card2, getGBC(1, 1, 1, 1, 10, 10, 1, 1));
        this.add(card3, getGBC(2, 1, 1, 1, 10, 10, 1, 1));
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
        if (e.getSource() instanceof CardButton) {
            this.revalidate();
            this.repaint();
            return;
        }

        List<String> replaceCardNames = new ArrayList<>();
        List<Integer> replaceCardIds = new ArrayList<>();

        for (Component component : this.getComponents()) {
            if (component instanceof CardButton) {
                CardButton cardButton = (CardButton) component;
                if (cardButton.isSelected()) {
                    replaceCardNames.add(cardButton.getName());
                    replaceCardIds.add(cardButton.getId());
                }
            }
        }
        System.out.println("Cards to replace: " + replaceCardNames);
        gameStartController.endMulligan(replaceCardIds, replaceCardNames);
        turnEndController.passTurn();

        int delay = 1000;
        ActionListener delayAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!bothMulliansComplete) {
                    bothMulliansComplete = true;
                    gameStartController.startMulligan();
                } else {
                    bothMulliansComplete = false;  // resetting it in case we start over
                    System.out.println("Turn Start! Drawing cards, clearing buffs, ...");
                    turnStartController.processTurnStart();
                }
            }
        };
        timer = new Timer(delay, delayAction);
        timer.setRepeats(false);
        timer.start();
    }
}
