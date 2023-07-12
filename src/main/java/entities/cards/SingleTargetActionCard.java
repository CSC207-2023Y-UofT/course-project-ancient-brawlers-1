package entities.cards;

import entities.cardEffects.CardEffect;

import java.util.List;

public class SingleTargetActionCard extends ActionCard {

    private CreatureCard target;

    public SingleTargetActionCard(int id, String name,
                                  PlayableCardData playableData) {
        super(id, name, playableData);
    }

    public void setTarget(CreatureCard target) {
        this.target = target;
    }

    public CreatureCard getTarget() {
        return target;
    }
}
