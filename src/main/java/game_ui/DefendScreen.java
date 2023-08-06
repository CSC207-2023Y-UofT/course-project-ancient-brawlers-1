package game_ui;

import javax.swing.*;
import java.awt.*;

public class DefendScreen extends JPanel {

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
        Font fontButton = new Font("Herculanum", Font.BOLD, 40);
        Font smallfontButton = new Font("Herculanum", Font.BOLD, 30);

        JFrame frame = new JFrame("Test Defend Screen");
        JPanel panel = new JPanel();

        panel.setLayout(new GridBagLayout());
////////////////////
        JLabel attackerLabel = new JLabel("Attacker");
        attackerLabel.setFont(fontLable);
        JLabel targetLabel = new JLabel("Target");
        targetLabel.setFont(fontLable);
        JLabel messageLabel = new JLabel("Select a defender or pass if You can't defend");
        messageLabel.setFont(smallfontButton);

        JButton attackerButton = new JButton("A");
        attackerButton.setFont(fontButton);
        JButton targetButton = new JButton("T");
        targetButton.setFont(fontButton);

        JButton defender1Button = new JButton("D1");
        defender1Button.setFont(smallfontButton);
        JButton defender2Button = new JButton("D2");
        defender2Button.setFont(smallfontButton);

        JButton confirmButton = new JButton("Confirm");
        confirmButton.setFont(fontButton);
        JButton passButton = new JButton("Pass");
        passButton.setFont(fontButton);

        JLabel C1Hp = new JLabel("HP:");
        C1Hp.setFont(fontButton);
        JLabel C1Atk = new JLabel("ATK:");
        C1Atk.setFont(fontButton);
        JLabel C2Hp = new JLabel("HP:");
        C2Hp.setFont(fontButton);
        JLabel C2Atk = new JLabel("ATK:");
        C2Atk.setFont(fontButton);
        JLabel C3Hp = new JLabel("HP:");
        C3Hp.setFont(fontButton);
        JLabel C3Atk = new JLabel("ATK:");
        C3Atk.setFont(fontButton);
        JLabel C4Hp = new JLabel("HP:");
        C4Hp.setFont(fontButton);
        JLabel C4Atk = new JLabel("ATK:");
        C4Atk.setFont(fontButton);


        panel.add(attackerLabel, getGBC(0, 0, 1, 1, 0, 0, 1, 1));
        panel.add(targetLabel, getGBC(5, 0, 1, 1, 0, 0, 1, 1));
        panel.add(messageLabel, getGBC(0, 2, 1, 1, 0, 0, 6, 1));

        panel.add(attackerButton, getGBC(1, 0, 1, 1, 200, 250, 2, 1));
        panel.add(targetButton, getGBC(3, 0, 1, 1, 200, 250, 2, 1));
        panel.add(defender1Button, getGBC(1, 3, 1, 1, 200, 250, 2, 1));
        panel.add(defender2Button, getGBC(3, 3, 1, 1, 200, 250, 2, 1));

        panel.add(confirmButton, getGBC(2, 5, 1, 1, 0, 0, 1, 1));
        panel.add(passButton, getGBC(4, 5, 1, 1, 0, 0, 1, 1));

        panel.add(C1Hp, getGBC(1,1,1,1,0,0,1,1));
        panel.add(C1Atk, getGBC(2,1,1,1,0,0,1,1));
        panel.add(C2Hp, getGBC(3,1,1,1,0,0,1,1));
        panel.add(C2Atk, getGBC(4,1,1,1,0,0,1,1));
        panel.add(C3Hp, getGBC(1,4,1,1,0,0,1,1));
        panel.add(C3Atk, getGBC(2,4,1,1,0,0,1,1));
        panel.add(C4Hp, getGBC(3,4,1,1,0,0,1,1));
        panel.add(C4Atk, getGBC(4,4,1,1,0,0,1,1));

////////////////////
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);


    }
}
