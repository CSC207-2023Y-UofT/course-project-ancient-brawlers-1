package entities.cards;

import entities.cardEffects.CardEffect;

import java.util.List;

public class SingleTargetActionCard extends ActionCard {

    private CreatureCard target;

    public SingleTargetActionCard(int id, String name, String description,
                                  TargetType targetType,
                                  List<CardEffect> effects) {
        super(id, name, description, targetType, effects);
    }

    public void setTarget(CreatureCard target) {
        this.target = target;
    }

    public CreatureCard getTarget() {
        return target;
    }
}
