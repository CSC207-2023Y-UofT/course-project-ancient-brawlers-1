package entities.cards;

import entities.cardEffects.CardEffect;

import java.util.List;

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
}
