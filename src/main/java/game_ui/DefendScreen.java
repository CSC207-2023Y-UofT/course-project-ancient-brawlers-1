package game_ui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import data_access.CardImageMapper;
import interface_adapters.controllers.AttackController;
import interface_adapters.controllers.EndGameController;
import interface_adapters.controllers.TurnEndController;
import interface_adapters.controllers.TurnStartController;
import interface_adapters.view_models.DefendScreenModel;
import interface_adapters.view_models.ScreenUpdateListener;

public class DefendScreen extends JPanel implements ActionListener, ScreenUpdateListener {

    private DefendScreenModel defendScreenModel;
    private AttackController attackController;
    private TurnEndController turnEndController;
    private TurnStartController turnStartController;
    private EndGameController endGameController;
    private CardImageMapper imageMapper = new CardImageMapper("./src/gameArt");
    private CardButton attacker, target, defender1, defender2;

    public DefendScreen(DefendScreenModel defendScreenModel, AttackController attackController, TurnEndController turnEndController, TurnStartController turnStartController, EndGameController endGameController) {
        this.defendScreenModel = defendScreenModel;
        this.attackController = attackController;
        this.turnEndController = turnEndController;
        this.turnStartController = turnStartController;
        this.endGameController = endGameController;
    }

    public void updateDefendScreen() {
        this.removeAll();
        this.revalidate();
        this.repaint();
        this.setLayout(new GridBagLayout());

        Font font = new Font("Herculanum", Font.BOLD, 40);
        Font font2 = new Font("Herculanum", Font.BOLD, 30);

        JLabel attackerLabel = new JLabel("Attacker");
        JLabel targetLabel = new JLabel("Target");
        JLabel messageLabel = new JLabel("Select a defender to take the damage for the target");
        attackerLabel.setFont(font);
        targetLabel.setFont(font);
        messageLabel.setFont(font2);

        attacker = new CardButton(defendScreenModel.getAttackerId(), defendScreenModel.getAttackerName(),
                                  imageMapper.getImageByName(defendScreenModel.getAttackerName()));
        target = new CardButton(defendScreenModel.getTargetId(), defendScreenModel.getTargetName(),
                                imageMapper.getImageByName(defendScreenModel.getTargetName()));

        List<Integer> defenderIds = defendScreenModel.getDefenderIds();
        List<String> defenderNames = defendScreenModel.getDefenderNames();
        if (defenderIds.isEmpty()) {
            defender1 = new CardButton(-1, "", null);
            defender2 = new CardButton(-1, "", null);
            defender1.setEnabled(false);
            defender2.setEnabled(false);
        } else if (defenderIds.size() == 1) {
            defender1 = new CardButton(defenderIds.get(0), defenderNames.get(0), imageMapper.getImageByName(defenderNames.get(0)));
            defender2 = new CardButton(-1, "", null);
            defender2.setEnabled(false);
        } else {
            defender1 = new CardButton(defenderIds.get(0), defenderNames.get(0), imageMapper.getImageByName(defenderNames.get(0)));
            defender2 = new CardButton(defenderIds.get(1), defenderNames.get(1), imageMapper.getImageByName(defenderNames.get(1)));
        }

        JButton confirmButton = new JButton("Confirm");
        JButton passButton = new JButton("Pass");
        confirmButton.setFont(font);
        passButton.setFont(font);

        JLabel defender1HP, defender2HP, defender1ATK, defender2ATK;
        if (defendScreenModel.getDefenderIds().isEmpty()) {
            defender1HP = new JLabel("HP: -");
            defender1ATK = new JLabel("ATK: -");
            defender2HP = new JLabel("HP: -");
            defender2ATK = new JLabel("ATK: -");
        } else if (defendScreenModel.getDefenderIds().size() == 1) {
            defender1HP = new JLabel("HP: " + defendScreenModel.getDefenderHP().get(0));
            defender1ATK = new JLabel("ATK: " + defendScreenModel.getDefenderATK().get(0));
            defender2HP = new JLabel("HP: -");
            defender2ATK = new JLabel("ATK: -");
        } else {
            defender1HP = new JLabel("HP: " + defendScreenModel.getDefenderHP().get(0));
            defender1ATK = new JLabel("ATK: " + defendScreenModel.getDefenderATK().get(0));
            defender2HP = new JLabel("HP: " + defendScreenModel.getDefenderHP().get(1));
            defender2ATK = new JLabel("ATK: " + defendScreenModel.getDefenderATK().get(1));
        }
        JLabel attackerHP = new JLabel("HP: " + defendScreenModel.getAttackerHP());
        JLabel attackerATK = new JLabel("ATK: " + defendScreenModel.getAttackerATK());
        JLabel targetHP = new JLabel("HP: " + defendScreenModel.getTargetHP());
        JLabel targetATK = new JLabel("ATK: " + defendScreenModel.getTargetATK());

        for (JLabel label : List.of(attackerHP, attackerATK, targetHP, targetATK,
                                    defender1HP, defender2HP, defender1ATK, defender2ATK)) {
            label.setFont(font);
        }

        this.add(attackerLabel, getGBC(0, 0, 1, 1, 0, 0, 1, 1));
        this.add(targetLabel, getGBC(5, 0, 1, 1, 0, 0, 1, 1));
        this.add(messageLabel, getGBC(0, 2, 1, 1, 0, 0, 6, 1));

        attacker.setPreferredSize(new Dimension(150, 240));
        target.setPreferredSize(new Dimension(150, 240));
        defender1.setPreferredSize(new Dimension(150, 240));
        defender2.setPreferredSize(new Dimension(150, 240));
        defender1.setOpaque(true);
        defender2.setOpaque(true);
        this.add(attacker, getGBC(1, 0, 1, 1, 0, 0, 2, 1));
        this.add(target, getGBC(3, 0, 1, 1, 0, 0, 2, 1));
        this.add(defender1, getGBC(1, 3, 1, 1, 10, 10, 2, 1));
        this.add(defender2, getGBC(3, 3, 1, 1, 10, 10, 2, 1));

        this.add(confirmButton, getGBC(2, 5, 1, 1, 0, 0, 1, 1));
        this.add(passButton, getGBC(4, 5, 1, 1, 0, 0, 1, 1));

        this.add(attackerHP, getGBC(1, 1, 1, 1, 0, 0, 1, 1));
        this.add(attackerATK, getGBC(2, 1, 1, 1, 0, 0, 1, 1));
        this.add(targetHP, getGBC(3, 1, 1, 1, 0, 0, 1, 1));
        this.add(targetATK, getGBC(4, 1, 1, 1, 0, 0, 1, 1));
        this.add(defender1HP, getGBC(1, 4, 1, 1, 0, 0, 1, 1));
        this.add(defender1ATK, getGBC(2, 4, 1, 1, 0, 0, 1, 1));
        this.add(defender2HP, getGBC(3, 4, 1, 1, 0, 0, 1, 1));
        this.add(defender2ATK, getGBC(4, 4, 1, 1, 0, 0, 1, 1));

        defender1.addActionListener(this);
        defender2.addActionListener(this);
        confirmButton.addActionListener(this);
        passButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof CardButton) {
            this.revalidate();
            this.repaint();
            return;
        }

        String command = e.getActionCommand();
        switch (command) {
            case "Confirm":
                System.out.println("Defending!");
                int defenderId;
                if (defender1.isSelected()) {
                    defenderId = defender1.getId();
                } else if (defender2.isSelected()) {
                    defenderId = defender2.getId();
                } else {
                    JOptionPane.showMessageDialog(this, "You haven't selected a defender.");
                    return;
                }
                attackController.defend(attacker.getId(), defenderId);
                System.out.println("Checking defeats");
                endGameController.checkEndGame();
                break;
            case "Pass":
                System.out.println("Taking the hit!");
                attackController.processAttack(attacker.getId(), target.getId());
                System.out.println("Checking defeats");
                endGameController.checkEndGame();
                break;
        }
        turnEndController.passTurn();
        System.out.println("Checking defeats after passing turn");
        endGameController.checkEndGame();
        turnStartController.processTurnStart();
        System.out.println("Checking defeats after turn starts");
        endGameController.checkEndGame();
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
        if (defendScreenModel.getAttackerName() != null) {
            updateDefendScreen();
        }
    }
}
