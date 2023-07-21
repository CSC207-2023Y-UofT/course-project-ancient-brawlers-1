package entities.cardEffects;

import entities.cards.CreatureCard;

public class DamageBuffEffect implements CreatureStatsEffect {

    private final int buffAmount;

    public DamageBuffEffect(int buffAmount) {
        this.buffAmount = buffAmount;
    }

    @Override
    public void invokeEffect(CreatureCard target) {
        target.addDamageBuff(buffAmount);
    }
}
