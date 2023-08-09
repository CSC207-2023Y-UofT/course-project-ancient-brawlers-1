package game_ui;

import interface_adapters.CardImageMapper;
import interface_adapters.controllers.PlayCardController;
import interface_adapters.view_models.ScreenUpdateListener;
import interface_adapters.view_models.TargetSelectScreenModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TargetSelectionScreen extends JPanel implements ActionListener, ScreenUpdateListener {

    private TargetSelectScreenModel targetSelectModel;
    private PlayCardController playCardController;
    private CardImageMapper imageMapper = new CardImageMapper("./src/gameArt");

    public TargetSelectionScreen(TargetSelectScreenModel targetSelectModel, PlayCardController playCardController) {
        this.targetSelectModel = targetSelectModel;
        this.playCardController = playCardController;

        this.setLayout(new GridBagLayout());
    }

    public void updateTargetScreen() {
        this.removeAll();
        this.revalidate();
        this.repaint();

        Font font = new Font("Herculanum", Font.BOLD, 40);

        JLabel instruction = new JLabel("Select a Target");
        JButton confirmButton = new JButton("Confirm");

        instruction.setFont(font);
        confirmButton.setFont(font);
        confirmButton.addActionListener(this);

        List<CardButton> targets = new ArrayList<>();
        List<JLabel> targetHPs = new ArrayList<>();
        List<JLabel> targetATKs = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            CardButton card = new CardButton(targetSelectModel.getTargetIds().get(i),
                    targetSelectModel.getTargetNames().get(i), imageMapper.getImageByName(targetSelectModel.getTargetNames().get(i)));
            card.setOpaque(true);
            card.setPreferredSize(new Dimension(140, 240));
            card.addActionListener(this);
            if (card.getId() == -1) {
                card.setEnabled(false);
            }
            targets.add(card);

            JLabel hp = new JLabel("HP: " + targetSelectModel.getTargetHP().get(i));
            hp.setFont(font);
            JLabel atk = new JLabel("ATK: " + targetSelectModel.getTargetATK().get(i));
            atk.setFont(font);
            targetHPs.add(hp);
            targetATKs.add(atk);
        }

        JLabel text = new JLabel("Card to play");
        text.setFont(font);

        CardButton cardInEffect = new CardButton(targetSelectModel.getCardId(), targetSelectModel.getCardName(),
                imageMapper.getImageByName(targetSelectModel.getCardName()));
        cardInEffect.setPreferredSize(new Dimension(140, 240));
        cardInEffect.setEnabled(false);

        this.add(instruction, getGBC(0,0,1,1,0, 0,6,1));
        this.add(confirmButton, getGBC(0,5,1,1,0, 0,6,1));

        this.add(targets.get(0), getGBC(0,3,1,1,5, 5,2,1));
        this.add(targetHPs.get(0), getGBC(0,4,1,1,0, 0,1,1));
        this.add(targetATKs.get(0), getGBC(1,4,1,1,0, 0,1,1));

        this.add(targets.get(1), getGBC(2,3,1,1,5, 5,2,1));
        this.add(targetHPs.get(1), getGBC(2,4,1,1,0, 0,1,1));
        this.add(targetATKs.get(1), getGBC(3,4,1,1,0, 0,1,1));

        this.add(targets.get(2), getGBC(4,3,1,1,5, 5,2,1));
        this.add(targetHPs.get(2), getGBC(4,4,1,1,0, 0,1,1));
        this.add(targetATKs.get(2), getGBC(5,4,1,1,0, 0,1,1));

        this.add(targets.get(3), getGBC(0,1,1,1,5, 5,2,1));
        this.add(targetHPs.get(3), getGBC(0,2,1,1,0, 0,1,1));
        this.add(targetATKs.get(3), getGBC(1,2,1,1,0, 0,1,1));

        this.add(targets.get(4), getGBC(2,1,1,1,5, 5,2,1));
        this.add(targetHPs.get(4), getGBC(2,2,1,1,0, 0,1,1));
        this.add(targetATKs.get(4), getGBC(3,2,1,1,0, 0,1,1));

        this.add(targets.get(5), getGBC(4,1,1,1,5, 5,2,1));
        this.add(targetHPs.get(5), getGBC(4,2,1,1,0, 0,1,1));
        this.add(targetATKs.get(5), getGBC(5,2,1,1,0, 0,1,1));

        this.add(text, getGBC(6,1,1,1,0,0,1,1));
        this.add(cardInEffect, getGBC(6,3,1,1,5,5,1,1));
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
        if (targetSelectModel.getCardName() != null) {
            updateTargetScreen();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof CardButton) {
            this.revalidate();
            this.repaint();
            return;
        }

        List<CardButton> targets = new ArrayList<>();
        for (Component component : this.getComponents()) {
            if (component instanceof CardButton) {
                CardButton cardButton = (CardButton) component;
                if (cardButton.isSelected()) {
                    targets.add(cardButton);
                }
            }
        }
        if (targets.size() > 1) {
            for (CardButton c : targets) {
                c.setSelected(false);
                c.setBackground(null);
            }
            JOptionPane.showMessageDialog(this, "Please select only one target.");
            this.revalidate();
            this.repaint();
        } else {
            playCardController.playSingleTarget(targetSelectModel.getCardId(), targets.get(0).getId());
        }
    }
}































