package entities.cardEffects;

import entities.cards.CreatureCard;

public class HealEffect implements CreatureStatsEffect {

    private final int healAmount;

    public HealEffect(int healAmount) {
        this.healAmount = healAmount;
    }

    @Override
    public void invokeEffect(CreatureCard target) {
        target.heal(healAmount);
    }
}
