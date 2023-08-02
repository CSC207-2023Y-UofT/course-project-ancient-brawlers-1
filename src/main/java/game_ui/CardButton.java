package game_ui;

import javax.swing.*;

public class CardButton extends JToggleButton {

    private int id;
    private String name;

    /**
     * Creates a button with no set text or icon.
     */
    public CardButton(int id, String name) {
        this.id = id;
        this.name = name;
        this.setText(name);
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
}
