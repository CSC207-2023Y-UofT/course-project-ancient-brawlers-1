package game_ui;

import javax.swing.*;

public class HandCardButton extends JButton {

    private int id;
    private String name;
    private String description;

    public HandCardButton(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
