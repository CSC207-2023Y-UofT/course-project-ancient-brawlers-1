package entities.cardEffects;

import entities.cards.CreatureCard;

public class HealthBuffEffect implements CreatureStatsEffect {

    private final int buffAmount;

    public HealthBuffEffect(int buffAmount) {
        this.buffAmount = buffAmount;
    }

    @Override
    public void invokeEffect(CreatureCard target) {
        target.addHealthBuff(buffAmount);
    }
}
