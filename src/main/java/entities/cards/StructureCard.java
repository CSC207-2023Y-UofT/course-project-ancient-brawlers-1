package entities.cards;

import entities.GameEvent;
import entities.cardEffects.CardEffect;

import java.util.List;

/**
 * StructureCard represents a structure in the game. Structure cards are playable
 * from the player's hand, and stays on the game board when played. When the game
 * comes to a certain stage, marked by values in the {@code GameEvent} enum, then
 * the structure will trigger its effects.
 */
public class StructureCard extends Card implements Playable {

    private final String description;
    private final TargetType targetType;
    private final List<CardEffect> effects;
    private final GameEvent triggerEvent;

    public StructureCard(int id, String name, PlayableCardData playableData,
                         GameEvent triggerEvent) {
        super(id, name);
        this.description = playableData.getDescription();
        this.targetType = playableData.getTargetType();
        this.effects = playableData.getEffects();
        this.triggerEvent = triggerEvent;
    }

    /**
     * Return the card description.
     *
     * @return a string for the card description.
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Return the target type of this card.
     *
     * @return a TargetType enum value.
     */
    @Override
    public TargetType getTargetType() {
        return targetType;
    }

    /**
     * Return the list of effects on this card.
     *
     * @return a list of CardEffect values (its subtypes).
     */
    @Override
    public List<CardEffect> getEffects() {
        return effects;
    }

    /**
     * Return the trigger condition of this card, represented by GameEvent enum
     * values.
     *
     * @return a GameEvent enum value.
     */
    public GameEvent getTriggerEvent() {
        return triggerEvent;
    }
}
