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
        panel.add(title, getGBC(0,0,1,1,1,1,4,1));

        JButton ConfirmButton = new JButton("Confirm");
        ConfirmButton.setFont(font);
        panel.add(ConfirmButton, getGBC(0,3,1,1,1,1,4,1));

        JButton C1Button = new JButton("C1");
        C1Button.setFont(font);
        panel.add(C1Button, getGBC(0,1,1,1,140,240,1,1));

        JButton C2Button = new JButton("C2");
        C2Button.setFont(font);
        panel.add(C2Button, getGBC(1,1,1,1,140,240,1,1));

        JButton C3Button = new JButton("C3");
        C3Button.setFont(font);
        panel.add(C3Button, getGBC(2,1,1,1,140,240,1,1));

        JButton C4Button = new JButton("C4");
        C4Button.setFont(font);
        panel.add(C4Button, getGBC(0,2,1,1,140,240,1,1));

        JButton C5Button = new JButton("C5");
        C5Button.setFont(font);
        panel.add(C5Button, getGBC(1,2,1,1,140,240,1,1));

        JButton C6Button = new JButton("C6");
        C6Button.setFont(font);
        panel.add(C6Button, getGBC(2,2,1,1,140,240,1,1));

        JLabel title2 = new JLabel("Card in Effect");
        title2.setFont(font);
        panel.add(title2, getGBC(3,1,1,1,1,1,1,1));

        JLabel title3 = new JLabel("Spell");
        title3.setFont(font);
        panel.add(title3, getGBC(3,2,1,1,1,1,1,1));

        frame.setVisible(true);

    }
}
