package entities.cards;

import entities.cardEffects.CardEffect;

import java.util.List;

public class ActionCard extends Card implements Playable {

    private final String description;
    private final TargetType targetType;
    private final List<CardEffect> effects;

    public ActionCard(int id, String name, String description,
                      TargetType targetType, List<CardEffect> effects) {
        super(id, name);
        this.description = description;
        this.targetType = targetType;
        this.effects = effects;
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
