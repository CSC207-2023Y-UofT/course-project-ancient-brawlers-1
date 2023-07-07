package entities;

import entities.cards.*;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final String name;
    private final List<Card> hand;
    private final int handCapacity;
    private final List<CreatureCard> creatures;
    private StructureCard structure;

    public Player(String name, List<CreatureCard> creatures) {
        this.name = name;
        this.creatures = creatures;
        this.hand = new ArrayList<>();
        this.handCapacity = 10;
    }

    public String getName() {
        return name;
    }

    public int getHandSize() {
        return hand.size();
    }

    public int getNumOfEssence() {
        int num = 0;

        for (Card card : hand) {
            if (card instanceof EssenceCard) {
                num++;
            }
        }
        return num;
    }

    public boolean addCard(Card card) {
        if (getHandSize() < handCapacity) {
            hand.add(card);
            return true;
        } else {
            return false;
        }
    }

    public Card playCard(int id) {
        for (Card card : hand) {
            if (card.getId() == id) {
                hand.remove(card);
                return card;
            }
        }
        return null;
    }

    public void setStructure(StructureCard structure) {
        this.structure = structure;
    }

    public StructureCard getStructure() {
        return structure;
    }

    public boolean removeCreature(int id) {
        for (CreatureCard creature : creatures) {
            if (creature.getId() == id) {
                creatures.remove(creature);
                return true;
            }
        }
        return false;
    }
}
