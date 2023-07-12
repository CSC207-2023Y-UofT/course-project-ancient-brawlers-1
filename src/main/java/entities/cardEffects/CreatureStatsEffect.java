package entities.cardEffects;

import entities.cards.CreatureCard;

public interface CreatureStatsEffect extends CardEffect {

    void invokeEffect(CreatureCard target);
}
