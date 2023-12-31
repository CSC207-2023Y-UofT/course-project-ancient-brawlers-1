package entities.cards;

import entities.cardEffects.CardEffect;

import java.util.List;

/**
 * PlayableCardData is a parameter object, used to mitigate the problem with
 * repeating parameter list in the constructors of {@code ActionCard} and
 * {@code StructureCard}.
 */
public class PlayableCardData {

    private final String description;
    private final TargetType targetType;
    private final List<CardEffect> effects;

    public PlayableCardData(String description, TargetType targetType,
                            List<CardEffect> effects) {
        this.description = description;
        this.targetType = targetType;
        this.effects = effects;
    }

    public String getDescription() {
        return description;
    }

    public TargetType getTargetType() {
        return targetType;
    }

    public List<CardEffect> getEffects() {
        return effects;
    }
}
