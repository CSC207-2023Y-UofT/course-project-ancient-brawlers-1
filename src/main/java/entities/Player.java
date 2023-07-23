package entities;

import entities.cards.*;
import entities.decks.EssenceDeck;
import entities.decks.PlayerDeck;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final String name;
    private final List<Card> hand;
    private final int handCapacity;
    private final List<CreatureCard> creatures;
    private StructureCard structure;
    private final PlayerDeck playerDeck;
    private final EssenceDeck essenceDeck;

    public Player(String name, List<CreatureCard> creatures, PlayerDeck playerDeck,
                  EssenceDeck essenceDeck) {
        this.name = name;
        this.creatures = creatures;
        this.playerDeck = playerDeck;
        this.essenceDeck = essenceDeck;
        this.hand = new ArrayList<>();
        this.handCapacity = 10;
    }

    public String getName() {
        return name;
    }

    public List<Card> getHand() {
        return hand;
    }

    public List<CreatureCard> getCreatures() {
        return creatures;
    }

    public int getHandCapacity() {
        return handCapacity;
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

    public PlayerDeck getPlayerDeck() {
        return playerDeck;
    }

    public EssenceDeck getEssenceDeck() {
        return essenceDeck;
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

    public boolean spendEssence(int num) {
        if (num > getNumOfEssence()) {
            return false;
        }

        for (Card card : hand) {
            if (num != 0 && card instanceof EssenceCard) {
                hand.remove(card);
                num--;
            }
        }
        return true;
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

    public CreatureCard getCreatureById(int id) {
        for (CreatureCard creature : creatures) {
            if (creature.getId() == id) {
                return creature;
            }
        }
        return null;
    }
}
