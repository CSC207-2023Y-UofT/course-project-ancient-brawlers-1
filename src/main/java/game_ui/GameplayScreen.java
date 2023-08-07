package game_ui;

import interface_adapters.CardImageMapper;
import interface_adapters.controllers.GameStartController;
import interface_adapters.view_models.GameplayScreenModel;
import interface_adapters.view_models.PlayerDataModel;
import interface_adapters.view_models.ScreenUpdateListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GameplayScreen extends JPanel implements ActionListener, ScreenUpdateListener {

    private final GameplayScreenModel gameplayScreenModel;
    private final GameStartController gameStartController;
    private final JPanel p1HandPanel;
    private final JPanel p2HandPanel;
    private final JPanel creaturePanel;
    private final CardImageMapper imageMapper = new CardImageMapper("./src/gameArt");

    public GameplayScreen(GameplayScreenModel gameplayScreenModel, GameStartController gameStartController) {
        this.gameplayScreenModel = gameplayScreenModel;
        this.gameStartController = gameStartController;

        this.setLayout(new GridBagLayout());

        p1HandPanel = new JPanel();
        p1HandPanel.setLayout(new GridBagLayout());
        p2HandPanel = new JPanel();
        p2HandPanel.setLayout(new GridBagLayout());
        creaturePanel = new JPanel();
        creaturePanel.setLayout(new GridBagLayout());

        /* ----------------------Fixed UI components--------------------------*/
        // Decks
        // Load the images
        ImageIcon cardBackIcon = new ImageIcon("./src/gameArt/Cardback.png");
        ImageIcon essenceIcon = new ImageIcon("./src/gameArt/EssenceSide.png");
        // Scale the images using ImageIcon's setImage method
        cardBackIcon.setImage(cardBackIcon.getImage().getScaledInstance(140, 100, Image.SCALE_SMOOTH));
        essenceIcon.setImage(essenceIcon.getImage().getScaledInstance(140, 100, Image.SCALE_SMOOTH));
        // Create JLabels with the scaled ImageIcons
        JLabel playDeck1 = new JLabel(cardBackIcon);
        JLabel playDeck2 = new JLabel(cardBackIcon);
        JLabel essenceDeck1 = new JLabel(essenceIcon);
        JLabel essenceDeck2 = new JLabel(essenceIcon);
        playDeck1.setToolTipText("Draws 2 every turn");
        playDeck2.setToolTipText("Draws 2 every turn");
        essenceDeck1.setToolTipText("Unlimited");
        essenceDeck2.setToolTipText("Unlimited");
        // buttons
        JButton endTurnButton = new JButton("End Turn");
        JButton pauseButton = new JButton("Pause");
        JButton playCardButton1 = new JButton("Play Card");
        JButton playCardButton2 = new JButton("Play Card");

        /* ----------------------UI components LAYOUT-------------------------*/
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new GridBagLayout());
        GridBagConstraints c;
        c = getGBC(0, 0, 0.5, 1, 0, 30, 1, 1, GridBagConstraints.EAST);
        c.insets = new Insets(0, 0, 70, 0);
        sidePanel.add(pauseButton, c);
        sidePanel.add(playDeck2, getGBC(0, 1, 1, 1, 0, 0, 1, 1, GridBagConstraints.EAST));
        sidePanel.add(essenceDeck2, getGBC(0, 2, 1, 1, 0, 0, 1, 1, GridBagConstraints.EAST));
        c = getGBC(0, 3, 0.5, 0.5, 0, 30, 1, 1, GridBagConstraints.EAST);
        c.insets = new Insets(30, 0, 30, 0);
        sidePanel.add(endTurnButton, c);
        sidePanel.add(essenceDeck1, getGBC(0, 4, 1, 1, 0, 0, 1, 1, GridBagConstraints.EAST));
        sidePanel.add(playDeck1, getGBC(0, 5, 1, 1, 0, 0, 1, 1, GridBagConstraints.EAST));
        sidePanel.add(new JLabel(), getGBC(0, 6, 0.5, 0.5, 0, 100, 1, 1, GridBagConstraints.EAST));

        this.add(playCardButton1, getGBC(2, 0, 0.5, 0.5, 25, 10, 1, 1));
        this.add(playCardButton2, getGBC(2, 2, 0.5, 0.5, 25, 10, 1, 1));
        c = getGBC(3, 0, 1, 1, 0, 0, 1, 3);
        c.anchor = GridBagConstraints.EAST;
        this.add(sidePanel, c);

        // Listeners
        pauseButton.addActionListener(this);
        endTurnButton.addActionListener(this);
        playCardButton1.addActionListener(this);
        playCardButton2.addActionListener(this);
    }

    public void updateGameplayScreen() {
        for (JPanel p : List.of(p1HandPanel, p2HandPanel, creaturePanel)) {
            p.removeAll();
            p.revalidate();
            p.repaint();
        }

        PlayerDataModel p1 = gameplayScreenModel.getPlayer1();
        PlayerDataModel p2 = gameplayScreenModel.getPlayer2();
        Font font = new Font("Herculanum", Font.BOLD, 30);
        Font font2 = new Font("Herculanum", Font.BOLD, 20);

        /* --------------------Dynamic UI components--------------------------*/
        // Creating the UI components (code will not look pleasant)
        JLabel p1Name = new JLabel(p1.getPlayerName());
        JLabel p2Name = new JLabel(p2.getPlayerName());
        p1Name.setFont(font2);
        p2Name.setFont(font2);
        p1Name.setBackground(Color.GRAY);
        p2Name.setBackground(Color.GRAY);
        // Creatures
        List<CardButton> creatures = new ArrayList<>();
        List<JLabel> hitPointLabels = new ArrayList<>();
        List<JLabel> attackLabels = new ArrayList<>();
        List<Integer> ids = new ArrayList<>(p1.getCreatureIds());
        List<String> names = new ArrayList<>(p1.getCreatureNames());
        List<Integer> hitPoints = new ArrayList<>(p1.getCreatureHPs());
        List<Integer> attacks = new ArrayList<>(p1.getCreatureAttacks());
        ids.addAll(p2.getCreatureIds());
        names.addAll(p2.getCreatureNames());
        hitPoints.addAll(p2.getCreatureHPs());
        attacks.addAll(p2.getCreatureAttacks());
        // Creatures
        for (int i = 0; i < ids.size(); i++) {
            CardButton creature = new CardButton(ids.get(i), names.get(i), imageMapper.getImageByName(names.get(i)));
            creature.setPreferredSize(new Dimension(140, 200));
            JLabel hitPoint = new JLabel("HP: " + hitPoints.get(i));
            JLabel attack = new JLabel("ATK: " + attacks.get(i));
            hitPoint.setFont(font2);
            attack.setFont(font2);
            creatures.add(creature);
            hitPointLabels.add(hitPoint);
            attackLabels.add(attack);
        }
        // The structure card to be displayed
        CardButton structure1;
        CardButton structure2;
        if (p1.getStructureName() != null) {
            structure1 = new CardButton(p1.getStructureId(), p1.getStructureName(), imageMapper.getImageByName(p1.getStructureName()));
            structure1.setToolTipText("Description...");
        } else {
            structure1 = new CardButton(-1, "", null);
            structure1.setToolTipText("Structure Card Slot");
        }
        if (p2.getStructureName() != null) {
            structure2 = new CardButton(p2.getStructureId(), p2.getStructureName(), imageMapper.getImageByName(p2.getStructureName()));
            structure2.setToolTipText("Description...");
        } else {
            structure2 = new CardButton(-1, "", null);
            structure2.setToolTipText("Structure Card Slot");
        }
        structure1.setPreferredSize(new Dimension(140, 200));
        structure2.setPreferredSize(new Dimension(140, 200));
        structure1.setEnabled(false);
        structure2.setEnabled(false);
        // Player hands
        List<HandCardButton> p1Hand = new ArrayList<>();
        List<HandCardButton> p2Hand = new ArrayList<>();
        for (int i = 0; i < p1.getHandCardIds().size(); i++) {
            HandCardButton handCard = new HandCardButton(p1.getHandCardIds().get(i), p1.getHandCardNames().get(i),
                    "Description", imageMapper.getImageByName(p1.getHandCardNames().get(i)));
            handCard.setPreferredSize(new Dimension(60, 90));
            p1Hand.add(handCard);
        }
        for (int i = 0; i < p2.getHandCardIds().size(); i++) {
            HandCardButton handCard = new HandCardButton(p2.getHandCardIds().get(i), p2.getHandCardNames().get(i),
                    "Description", imageMapper.getImageByName(p2.getHandCardNames().get(i)));
            handCard.setPreferredSize(new Dimension(60, 90));
            p2Hand.add(handCard);
        }
        // Miscellaneous
        JLabel message = new JLabel("To attack, select a friendly creature and then an enemy creature.");
        message.setFont(font2);
        CardButton selectedCard1 = new CardButton(-1, "", null);
        CardButton selectedCard2 = new CardButton(-1, "", null);
        selectedCard1.setToolTipText("Card to be played");
        selectedCard2.setToolTipText("Card to be played");
        selectedCard1.setPreferredSize(new Dimension(140, 200));
        selectedCard2.setPreferredSize(new Dimension(140, 200));
        selectedCard1.setEnabled(false);
        selectedCard2.setEnabled(false);

        /* ----------------------UI components LAYOUT-------------------------*/
        for (int i = 0; i < p1Hand.size(); i++) {
            p1HandPanel.add(p1Hand.get(i), getGBC(i, 0, 1, 1, 5, 5, 1, 1));
        }
        for (int i = 0; i < p2Hand.size(); i++) {
            p2HandPanel.add(p2Hand.get(i), getGBC(i, 0, 1, 1, 5, 5, 1, 1));
        }
        GridBagConstraints c = getGBC(0, 0, 1, 1, 5, 5, 1, 1);
        c.insets = new Insets(0, 0, 0, 120);
        creaturePanel.add(structure1, c);
        c = getGBC(0, 3, 1, 1, 5, 5, 1, 1);
        c.insets = new Insets(0, 0, 0, 120);
        creaturePanel.add(structure2, c);
        for (int i = 0; i < creatures.size(); i++) {
            if (i < 3) {
                creaturePanel.add(creatures.get(i), getGBC(1 + 2 * i, 3, 1, 1, 5, 5, 2, 1));
                creaturePanel.add(hitPointLabels.get(i), getGBC(1 + 2 * i, 4, 1, 1, 0, 0, 1, 1));
                creaturePanel.add(attackLabels.get(i), getGBC(2 + 2 * i, 4, 1, 1, 0, 0, 1, 1));
            } else {
                creaturePanel.add(creatures.get(i), getGBC(1 + 2 * (i - 3), 0, 1, 1, 5, 5, 2, 1));
                creaturePanel.add(hitPointLabels.get(i), getGBC(1 + 2 * (i - 3), 1, 1, 1, 0, 0, 1, 1));
                creaturePanel.add(attackLabels.get(i), getGBC(2 + 2 * (i - 3), 1, 1, 1, 0, 0, 1, 1));
            }
        }
        creaturePanel.add(message, getGBC(0, 2, 1, 1, 0, 20, 8, 1));
        c = getGBC(7, 0, 1, 1, 5, 5, 1, 1);
        c.insets = new Insets(0, 120, 0, 0);
        creaturePanel.add(selectedCard1, c);
        c = getGBC(7, 3, 1, 1, 5, 5, 1, 1);
        c.insets = new Insets(0, 120, 0, 0);
        creaturePanel.add(selectedCard2, c);
        creaturePanel.add(new JLabel(), getGBC(0, 1, 1, 1, 0, 0, 1, 1));
        creaturePanel.add(new JLabel(), getGBC(7, 1, 1, 1, 0, 0, 1, 1));
        creaturePanel.add(new JLabel(), getGBC(0, 4, 1, 1, 0, 0, 1, 1));
        creaturePanel.add(new JLabel(), getGBC(7, 4, 1, 1, 0, 0, 1, 1));

        this.add(p1Name, getGBC(0, 0, 0.5, 0.5, 0, 0, 1, 1, GridBagConstraints.CENTER));
        this.add(p2Name, getGBC(0, 2, 0.5, 0.5, 0, 0, 1, 1, GridBagConstraints.CENTER));
        this.add(p1HandPanel, getGBC(1, 2, 0.75, 0.5, 0, 0, 1, 1));
        this.add(p2HandPanel, getGBC(1, 0, 0.75, 0.5, 0, 0, 1, 1));
        this.add(creaturePanel, getGBC(0, 1, 1, 1, 0, 0, 3, 1));
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

    private GridBagConstraints getGBC(int gridx, int gridy, double weightx, double weighty,
                                      int ipadx, int ipady, int gridwidth, int gridheight, int anchor) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = gridx;
        c.gridy = gridy;
        c.weightx = weightx;
        c.weighty = weighty;
        c.ipadx = ipadx;
        c.ipady = ipady;
        c.gridwidth = gridwidth;
        c.gridheight = gridheight;
        c.anchor = anchor;
        return c;
    }

    @Override
    public void onScreenUpdate() {
        if (gameplayScreenModel.getPlayer1() != null || gameplayScreenModel.getPlayer2() != null) {
            updateGameplayScreen();
        }
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
