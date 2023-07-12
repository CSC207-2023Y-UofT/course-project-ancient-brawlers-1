package entities.cardEffects;

import entities.cards.CreatureCard;

public class SwapHealthAttackEffect implements CreatureStatsEffect {

    @Override
    public void invokeEffect(CreatureCard target) {
        int damage = target.getTotalAttackDamage();
        int health = target.getTotalHitPoints();
        target.setAttackDamage(health);
        target.setMaxHitPoints(damage);
    }
}
