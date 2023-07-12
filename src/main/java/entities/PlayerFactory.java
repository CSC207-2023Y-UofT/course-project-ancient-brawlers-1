package entities;

import entities.cards.CreatureCard;

import java.util.List;

public class PlayerFactory {

    public Player createPlayer(String name, List<CreatureCard> creatures) {
        return new Player(name, creatures);
    }
}
