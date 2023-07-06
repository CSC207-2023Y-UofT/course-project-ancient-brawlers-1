package entities.cards;

public abstract class Card {

    private final int id;
    private final String name;
    private boolean displayed = false;

    public Card(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void showCard() {
        displayed = true;
    }

    public void hideCard() {
        displayed = false;
    }
}
