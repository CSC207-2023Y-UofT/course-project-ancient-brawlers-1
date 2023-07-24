package entities.cardEffects;

import entities.cards.CreatureCard;

public class HealthModifyEffect implements CreatureStatsEffect {

    private final int modifyAmount;

    public HealthModifyEffect(int modifyAmount) {
        this.modifyAmount = modifyAmount;
    }

    @Override
    public void invokeEffect(CreatureCard target) {
        target.setMaxHitPoints(target.getMaxHitPoints() + modifyAmount);
    }
}
