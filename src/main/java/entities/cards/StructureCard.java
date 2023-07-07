package entities.cards;

import entities.GameEvent;
import entities.cardEffects.CardEffect;

import java.util.List;

public class StructureCard extends Card implements Playable {

    private final String description;
    private final TargetType targetType;
    private final List<CardEffect> effects;
    private final GameEvent triggerEvent;

    public StructureCard(int id, String name, String description,
                         TargetType targetType, List<CardEffect> effects,
                         GameEvent triggerEvent) {
        super(id, name);
        this.description = description;
        this.targetType = targetType;
        this.effects = effects;
        this.triggerEvent = triggerEvent;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public TargetType getTargetType() {
        return targetType;
    }

    @Override
    public List<CardEffect> getEffects() {
        return effects;
    }

    public GameEvent getTriggerEvent() {
        return triggerEvent;
    }
}
