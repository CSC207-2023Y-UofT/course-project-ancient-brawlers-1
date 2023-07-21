package entities;

import entities.cards.CreatureCard;
import entities.decks.EssenceDeck;
import entities.decks.PlayerDeck;

import java.util.List;

public class PlayerFactory {

    public Player createPlayer(String name, List<CreatureCard> creatures,
                               PlayerDeck playerDeck, EssenceDeck essenceDeck) {
        return new Player(name, creatures, playerDeck, essenceDeck);
    }
}
