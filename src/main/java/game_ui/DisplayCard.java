package game_ui;

import javax.swing.*;

/**
 * DisplayCard is a UI element representing a selected card in the player's hand.
 * It simply displays the card more clearly to the player.
 */
public class DisplayCard extends JLabel {

    private int id;
    private String name;
    private String description;

    public DisplayCard(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        this.setToolTipText(description);
    }
}
