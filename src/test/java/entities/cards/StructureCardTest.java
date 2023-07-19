package entities.cards;

import entities.GameEvent;
import entities.cardEffects.CardEffect;
import entities.cardEffects.HealEffect;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StructureCardTest {

    StructureCard structure;
    String desc;
    TargetType targetType;
    List<CardEffect> effects;
    GameEvent triggerEvent;

    @BeforeEach
    void setUp() {
        desc = "At the end of your turn, heal 3 HP to all friendly creatures.";
        targetType = TargetType.FRIENDLY;
        effects = new ArrayList<>();
        effects.add(new HealEffect(3));
        PlayableCardData sampleStructure = new PlayableCardData(desc, targetType, effects);
        triggerEvent = GameEvent.TURN_END;

        structure = new StructureCard(2, "Structure", sampleStructure, triggerEvent);
    }

    @Test
    void testIdAndName() {
        assertEquals(2, structure.getId());
        assertEquals("Structure", structure.getName());
    }

    @Test
    void testGetDescription() {
        assertEquals(desc, structure.getDescription());
    }

    @Test
    void testGetTargetType() {
        assertEquals(targetType, structure.getTargetType());
    }

    @Test
    void testGetEffects() {
        assertEquals(effects, structure.getEffects());
    }

    @Test
    void testGetTriggerEvent() {
        assertEquals(triggerEvent, structure.getTriggerEvent());
    }
}