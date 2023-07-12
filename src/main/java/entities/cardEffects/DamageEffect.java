package entities.cardEffects;

import entities.cards.CreatureCard;

public class DamageEffect implements CreatureStatsEffect {

    private final int damage;

    public DamageEffect(int damage) {
        this.damage = damage;
    }

    @Override
    public void invokeEffect(CreatureCard target) {
        target.takeDamage(damage);
    }
}
