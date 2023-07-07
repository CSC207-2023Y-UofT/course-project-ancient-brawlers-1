package entities.cardEffects;

import entities.cards.CreatureCard;

public class DamageEffect implements CreatureStatsEffect {

    private final int damageAmount;

    public DamageEffect(int damageAmount) {
        this.damageAmount = damageAmount;
    }

    @Override
    public void invokeEffect(CreatureCard target) {
        target.takeDamage(damageAmount);
    }
}
