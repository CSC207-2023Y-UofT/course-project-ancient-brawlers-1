package game_ui;

import javax.swing.*;
import java.awt.*;

public class HandCardButton extends JButton {

    private int id;
    private String name;
    private String description;
    private ImageIcon icon;

    public HandCardButton(int id, String name, String description, ImageIcon icon) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.setToolTipText(description);
        this.setIcon(icon);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ImageIcon getImage() {
        return icon;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
