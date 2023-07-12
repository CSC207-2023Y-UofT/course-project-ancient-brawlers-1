package entities.cardEffects;

import entities.cards.CreatureCard;

public class DamageModifyEffect implements CreatureStatsEffect {

    private final int modifyAmount;

    public DamageModifyEffect(int modifyAmount) {
        this.modifyAmount = modifyAmount;
    }

    @Override
    public void invokeEffect(CreatureCard target) {
        target.setAttackDamage(target.getAttackDamage() + modifyAmount);
    }
}
