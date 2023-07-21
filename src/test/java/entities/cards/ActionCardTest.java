package entities.cards;

import entities.GameEvent;
import entities.cardEffects.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ActionCardTest {

    ActionCard action;
    String desc;
    TargetType targetType;
    List<CardEffect> effects;

    @BeforeEach
    void setUp() {
        desc = "Give all friendly creatures +3 attack and +3 HP " +
                "until the start of next turn.";
        targetType = TargetType.FRIENDLY;
        effects = new ArrayList<>();
        effects.add(new HealthBuffEffect(3));
        effects.add(new DamageBuffEffect(3));
        PlayableCardData cardData = new PlayableCardData(desc, targetType, effects);

        action = new ActionCard(1, "Name", cardData);
    }

    @Test
    void testIdAndName() {
        assertEquals(1, action.getId());
        assertEquals("Name", action.getName());
    }

    @Test
    void testGetDescription() {
        assertEquals(desc, action.getDescription());
    }

    @Test
    void testGetTargetType() {
        assertEquals(targetType, action.getTargetType());
    }

    @Test
    void testGetEffects() {
        assertEquals(effects, action.getEffects());
    }
}