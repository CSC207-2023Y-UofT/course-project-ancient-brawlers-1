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
        JLabel messageLabel = new JLabel("Select a defender or pass / You can's defend");
        messageLabel.setFont(smallfontButton);

        JButton attackerButton = new JButton("Attacker");
        attackerButton.setFont(fontButton);
        JButton targetButton = new JButton("Target");
        targetButton.setFont(fontButton);

        JButton defender1Button = new JButton("Defender1");
        defender1Button.setFont(smallfontButton);
        JButton defender2Button = new JButton("Defender2");
        defender2Button.setFont(smallfontButton);

        JButton confirmButton = new JButton("Confirm");
        confirmButton.setFont(fontButton);
        JButton passButton = new JButton("Pass");
        passButton.setFont(fontButton);

        panel.add(attackerLabel, getGBC(0, 0, 1, 1, 0, 0, 1, 1));
        panel.add(targetLabel, getGBC(3, 0, 1, 1, 0, 0, 1, 1));
        panel.add(messageLabel, getGBC(0, 1, 1, 1, 0, 0, 4, 1));

        panel.add(attackerButton, getGBC(1, 0, 1, 1, 80, 300, 1, 1));
        panel.add(targetButton, getGBC(2, 0, 1, 1, 80, 300, 1, 1));
        panel.add(defender1Button, getGBC(1, 2, 1, 1, 80, 300, 1, 1));
        panel.add(defender2Button, getGBC(2, 2, 1, 1, 80, 300, 1, 1));

        panel.add(confirmButton, getGBC(1, 3, 1, 1, 0, 0, 1, 1));
        panel.add(passButton, getGBC(2, 3, 1, 1, 0, 0, 1, 1));
////////////////////
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);


    }
}
