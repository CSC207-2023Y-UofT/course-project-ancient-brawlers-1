package game_ui;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HoverableLabel extends JLabel implements MouseListener {

    private String hoverMessage;

    public HoverableLabel(String text, String hoverMessage) {
        super(text);
        this.hoverMessage = hoverMessage;
        addMouseListener(this);
    }

    public void setHoverMessage(String hoverMessage) {
        this.hoverMessage = hoverMessage;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setToolTipText(hoverMessage);
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
