package game_ui;

import javax.swing.*;
import java.awt.*;

public class TargetSelectionScreen extends JPanel {

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
        JFrame frame = new JFrame("Target Selection Screen");
        JPanel panel = new JPanel();

        panel.setLayout(new GridBagLayout());

        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel, BorderLayout.CENTER);

        Font font = new Font("Herculanum", Font.BOLD, 40);

        JLabel title = new JLabel("Select a Target");
        title.setFont(font);

        JButton ConfirmButton = new JButton("Confirm");
        ConfirmButton.setFont(font);

        JButton C1Button = new JButton("C1");
        C1Button.setFont(font);

        JLabel C1Hp = new JLabel("HP:");
        C1Hp.setFont(font);

        JLabel C1Atk = new JLabel("ATK:");
        C1Atk.setFont(font);

        JButton C2Button = new JButton("C2");
        C2Button.setFont(font);

        JLabel C2Hp = new JLabel("HP:");
        C2Hp.setFont(font);

        JLabel C2Atk = new JLabel("ATK:");
        C2Atk.setFont(font);

        JButton C3Button = new JButton("C3");
        C3Button.setFont(font);

        JLabel C3Hp = new JLabel("HP:");
        C3Hp.setFont(font);

        JLabel C3Atk = new JLabel("ATK:");
        C3Atk.setFont(font);

        JButton C4Button = new JButton("C4");
        C4Button.setFont(font);

        JLabel C4Hp = new JLabel("HP:");
        C4Hp.setFont(font);

        JLabel C4Atk = new JLabel("ATK:");
        C4Atk.setFont(font);

        JButton C5Button = new JButton("C5");
        C5Button.setFont(font);

        JLabel C5Hp = new JLabel("HP:");
        C5Hp.setFont(font);

        JLabel C5Atk = new JLabel("ATK:");
        C5Atk.setFont(font);

        JButton C6Button = new JButton("C6");
        C6Button.setFont(font);

        JLabel C6Hp = new JLabel("HP:");
        C6Hp.setFont(font);

        JLabel C6Atk = new JLabel("ATK:");
        C6Atk.setFont(font);

        JLabel title2 = new JLabel("Card in Effect");
        title2.setFont(font);

        JLabel title3 = new JLabel("Spell");
        title3.setFont(font);

        panel.add(title, getGBC(0,0,1,1,1,1,6,1));
        panel.add(ConfirmButton, getGBC(0,5,1,1,1,1,6,1));

        panel.add(C1Button, getGBC(0,1,1,1,140,240,2,1));
        panel.add(C1Hp, getGBC(0,2,1,1,1,1,1,1));
        panel.add(C1Atk, getGBC(1,2,1,1,1,1,1,1));

        panel.add(C2Button, getGBC(2,1,1,1,140,240,2,1));
        panel.add(C2Hp, getGBC(2,2,1,1,1,1,1,1));
        panel.add(C2Atk, getGBC(3,2,1,1,1,1,1,1));

        panel.add(C3Button, getGBC(4,1,1,1,140,240,2,1));
        panel.add(C3Hp, getGBC(4,2,1,1,1,1,1,1));
        panel.add(C3Atk, getGBC(5,2,1,1,1,1,1,1));

        panel.add(C4Button, getGBC(0,3,1,1,140,240,2,1));
        panel.add(C4Hp, getGBC(0,4,1,1,1,1,1,1));
        panel.add(C4Atk, getGBC(1,4,1,1,1,1,1,1));

        panel.add(C5Button, getGBC(2,3,1,1,140,240,2,1));
        panel.add(C5Hp, getGBC(2,4,1,1,1,1,1,1));
        panel.add(C5Atk, getGBC(3,4,1,1,1,1,1,1));

        panel.add(C6Button, getGBC(4,3,1,1,140,240,2,1));
        panel.add(C6Hp, getGBC(4,4,1,1,1,1,1,1));
        panel.add(C6Atk, getGBC(5,4,1,1,1,1,1,1));

        panel.add(title2, getGBC(6,1,1,1,1,1,1,1));

        panel.add(title3, getGBC(6,3,1,1,1,1,1,1));

        frame.setVisible(true);

    }
}































