package game_ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class CardButton extends JToggleButton {

    private int id;
    private String name;
    private ImageIcon icon;

    /**
     * Creates a button with no set text or icon.
     */
    public CardButton(int id, String name, ImageIcon icon) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.setIcon(icon);
        this.addActionListener(new CustomActionListener());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setPreferredSize(Dimension preferredSize) {
        // Optionally, you can add additional logic here to enforce constraints
        // on the preferredSize before setting it.
        super.setPreferredSize(preferredSize);
        this.setIcon(icon);
    }

    @Override
    public void setIcon(Icon icon) {
        if (icon != null) {
            // Get the button's size
            Dimension buttonSize = getPreferredSize();

            // Get the image and scale it to fit the button
            Image image = ((ImageIcon) icon).getImage();
            Image scaledImage = image.getScaledInstance(buttonSize.width, buttonSize.height, Image.SCALE_SMOOTH);

            // Create a new ImageIcon with the scaled image
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            // Set the scaled ImageIcon as the button's icon
            super.setIcon(scaledIcon);
        } else {
            super.setIcon(icon);
        }
    }

    private static class CustomActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() instanceof CardButton) {
                if (((CardButton) e.getSource()).isSelected()) {
                    ((CardButton) e.getSource()).setBackground(Color.RED);
                } else {
                    ((CardButton) e.getSource()).setBackground(null);
                }
            }
        }
    }
}
