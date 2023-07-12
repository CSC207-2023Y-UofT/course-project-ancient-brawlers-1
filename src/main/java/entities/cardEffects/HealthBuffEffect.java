package entities.cardEffects;

import entities.cards.CreatureCard;

public class HealthBuffEffect implements CreatureStatsEffect {

    private final int buffValue;

    public HealthBuffEffect(int buffValue) {
        this.buffValue = buffValue;
    }

    @Override
    public void invokeEffect(CreatureCard target) {
        target.addHealthBuff(buffValue);
    }
}
