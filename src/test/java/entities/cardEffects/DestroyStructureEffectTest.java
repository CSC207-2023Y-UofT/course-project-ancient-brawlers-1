package entities.cardEffects;

import entities.GameEvent;
import entities.Player;
import entities.cards.PlayableCardData;
import entities.cards.StructureCard;
import entities.cards.TargetType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DestroyStructureEffectTest {

    @Test
    void invokeEffect() {
        String desc = "At the end of your turn, heal 3 HP to all friendly creatures.";
        List<CardEffect> effects = new ArrayList<>();
        effects.add(new HealEffect(3));
        PlayableCardData sampleStructure = new PlayableCardData(desc, TargetType.FRIENDLY, effects);

        StructureCard structure = new StructureCard(2, "Structure", sampleStructure, GameEvent.TURN_END);

        Player player = new Player("Player 1", null, null, null);
        player.setStructure(structure);

        DestroyStructureEffect effect = new DestroyStructureEffect();

        effect.invokeEffect(player);
        assertNull(player.getStructure());
    }
}