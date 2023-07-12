package entities.cardEffects;

import entities.Player;

public class DestroyStructureEffect implements PlayerStatsEffect {

    @Override
    public void invokeEffect(Player target) {
        target.setStructure(null);
    }
}
