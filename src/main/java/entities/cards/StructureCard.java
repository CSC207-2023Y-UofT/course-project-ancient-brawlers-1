package entities.cards;

import entities.GameEvent;
import entities.cardEffects.CardEffect;

import java.util.List;

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
