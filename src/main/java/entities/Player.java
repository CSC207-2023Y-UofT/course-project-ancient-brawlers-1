package entities;

import entities.cards.Card;
import entities.cards.CreatureCard;
import entities.cards.EssenceCard;
import entities.cards.StructureCard;
import entities.decks.EssenceDeck;
import entities.decks.PlayerDeck;

import java.util.ArrayList;
import java.util.List;

/**
 * The player in the game. Contains all the information of the Player: decks,
 * current hand, structure in play, and creatures active.
 */
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

    /**
     * Return the amount of Essence cards in the player's hand.
     *
     * @return the number of Essence cards.
     */
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

    /**
     * Add a card to the player's hand. Return true when the card is added
     * successfully. Return false when the player's hand is full.
     *
     * @param card the card to be added to the player's hand.
     * @return a boolean to indicate the success or failure of adding the card.
     */
    public boolean addCard(Card card) {
        if (getHandSize() < handCapacity) {
            hand.add(card);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Remove and return the card with the specified id from the player's hand.
     *
     * @param id the id of the card.
     * @return the requested card.
     */
    public Card playCard(int id) {
        for (Card card : hand) {
            if (card.getId() == id) {
                hand.remove(card);
                return card;
            }
        }
        return null;
    }

    /**
     * Return the card with the specified id from the player's hand, but not
     * removing it.
     *
     * @param id the id of the card.
     * @return the requested card.
     */
    public Card getCardById(int id) {
        for (Card card : hand) {
            if (card.getId() == id) {
                return card;
            }
        }
        return null;
    }

    /**
     * Remove a given number of Essence cards from the player's hand. If the
     * number requested is smaller than the number of Essence cards in the
     * player's hand, then return false.
     *
     * @param num the number of Essence cards to remove.
     * @return a boolean to indicate the success or failure.
     */
    public boolean spendEssence(int num) {
        if (num > getNumOfEssence()) {
            return false;
        }

        List<Card> cards = new ArrayList<>();
        for (Card card : hand) {
            if (num != 0 && card instanceof EssenceCard) {
                cards.add(card);
                num--;
            }
        }
        hand.removeAll(cards);
        return true;
    }

    public void setStructure(StructureCard structure) {
        this.structure = structure;
    }

    public StructureCard getStructure() {
        return structure;
    }

    /**
     * Remove the creature with the given id in the player's list of creatures.
     */
    public boolean removeCreature(int id) {
        for (CreatureCard creature : creatures) {
            if (creature.getId() == id) {
                creatures.remove(creature);
                return true;
            }
        }
        return false;
    }

    /**
     * Return the creature with the given id in the player's list of creatures.
     */
    public CreatureCard getCreatureById(int id) {
        for (CreatureCard creature : creatures) {
            if (creature.getId() == id) {
                return creature;
            }
        }
        return null;
    }
}
