package game_ui;

import interface_adapters.presenters.GamePausePresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseScreen extends JPanel implements ActionListener {

    private final GamePausePresenter pausePresenter;

    public PauseScreen(GamePausePresenter pausePresenter) {
        this.pausePresenter = pausePresenter;

        setLayout(new GridBagLayout());

        JButton resumeButton = new JButton("Resume");
        JButton saveAndQuitButton = new JButton("Save and Quit");
        JButton quitWithoutSaveButton = new JButton("Quit Without Save");
        Font font = new Font("Herculanum", Font.BOLD, 30);
        resumeButton.setFont(font);
        saveAndQuitButton.setFont(font);
        quitWithoutSaveButton.setFont(font);

        resumeButton.addActionListener(this);
        saveAndQuitButton.addActionListener(this);
        quitWithoutSaveButton.addActionListener(this);

        add(resumeButton, getGBC(0, 0, 1, 1, 30, 30, 1, 1));
        add(saveAndQuitButton, getGBC(0, 1, 1, 1, 30, 30, 1, 1));
        add(quitWithoutSaveButton, getGBC(0, 2, 1, 1, 30, 30, 1, 1));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "Resume":
                pausePresenter.resumeGame();
                break;
            case "Save and Quit":
                System.out.println("Feature under development :)");
                break;
            case "Quit Without Save":
                pausePresenter.quitGameWithoutSave();
                break;
        }
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
}
