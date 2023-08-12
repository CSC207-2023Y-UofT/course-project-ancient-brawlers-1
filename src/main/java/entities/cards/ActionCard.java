package entities.cards;

import entities.cardEffects.CardEffect;

import java.util.List;

/**
 * ActionCard represents an action in the game. Action cards are playable from
 * the player's hand, and has immediate effects.
 */
public class ActionCard extends Card implements Playable {

    private final String description;
    private final TargetType targetType;
    private final List<CardEffect> effects;

    public ActionCard(int id, String name, PlayableCardData playableData) {
        super(id, name);
        this.description = playableData.getDescription();
        this.targetType = playableData.getTargetType();
        this.effects = playableData.getEffects();
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
}
