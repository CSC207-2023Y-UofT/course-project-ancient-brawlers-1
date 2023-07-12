package entities.cardEffects;

import entities.cards.CreatureCard;

public class StunEffect implements CreatureStatsEffect {

    @Override
    public void invokeEffect(CreatureCard target) {
        target.stun();
    }
}
