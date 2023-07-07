package entities.cardEffects;

import entities.Player;

public interface PlayerStatsEffect extends CardEffect {

    void invokeEffect(Player target);
}
