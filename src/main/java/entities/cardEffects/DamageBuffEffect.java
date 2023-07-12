package entities.cardEffects;

import entities.cards.CreatureCard;

public class DamageBuffEffect implements CreatureStatsEffect {

    private final int buffValue;

    public DamageBuffEffect(int buffValue) {
        this.buffValue = buffValue;
    }

    @Override
    public void invokeEffect(CreatureCard target) {
        target.addDamageBuff(buffValue);
    }
}
