package game_ui;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HoverableLabel extends JLabel implements MouseListener {

    private String deckSize;

    public HoverableLabel(String text, String deckSize) {
        super(text);
        this.deckSize = deckSize;
        addMouseListener(this);
    }

    public void setDeckSize(String deckSize) {
        this.deckSize = deckSize;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setToolTipText(deckSize);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setToolTipText(null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }
}
