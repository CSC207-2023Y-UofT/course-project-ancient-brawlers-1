package entities.cards;

/**
 * Card is the super class of all card types in the game. Every card has an id
 * and a name.
 */
public abstract class Card {

    private int id;
    private final String name;

    public Card(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
}
