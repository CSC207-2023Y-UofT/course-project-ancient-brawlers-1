package entities.cards;

import entities.cardEffects.CardEffect;

import java.util.List;

/**
 * Playable interface should be implemented by any card type that can be played
 * from a player's hand. These types of cards must have a description for what
 * they can do when played, and they must have attributes that specify the target
 * type of this playable card, as well as the kind of effects included.
 */
public interface Playable {

    /**
     * Return the card description.
     *
     * @return a string for the card description.
     */
    String getDescription();

    /**
     * Return the target type of this card.
     *
     * @return a TargetType enum value.
     */
    TargetType getTargetType();

    /**
     * Return the list of effects on this card.
     *
     * @return a list of CardEffect values (its subtypes).
     */
    List<CardEffect> getEffects();
}
