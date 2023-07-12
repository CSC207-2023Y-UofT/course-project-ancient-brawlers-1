package entities.cardEffects;

import entities.cards.CreatureCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HealthModifyEffectTest {

    private CreatureCard creature;
    private HealthModifyEffect effect;
    private HealthModifyEffect negativeEffect;

    @BeforeEach
    public void setUp() {
        creature = new CreatureCard(1, "some name", 30, 5, 2, 1);
        effect = new HealthModifyEffect(10);
        negativeEffect = new HealthModifyEffect(-10);
    }

    @Test
    public void testInvokeEffect_noBuff() {
        effect.invokeEffect(creature);
        assertEquals(30, creature.getHitPoints(),
                "Base hit-points should not change.");
        assertEquals(30, creature.getTotalHitPoints(),
                "Total hit-points should not change.");
        assertEquals(40, creature.getMaxHitPoints(),
                "Max hit-points (the upper bound) should change.");
    }

    @Test
    public void testInvokeEffect_withBuff() {
        // add a buff so now creature's total hit-points should be 40
        // but the max hit-points (the upper bound) is still 30
        creature.addHealthBuff(10);
        assertEquals(40, creature.getTotalHitPoints());
        assertEquals(30, creature.getMaxHitPoints());

        // apply effect, no actual hit-point values are changing
        // only the upper bound is changed
        effect.invokeEffect(creature);
        assertEquals(30, creature.getHitPoints(),
                "Base hit-points should not change.");
        assertEquals(40, creature.getTotalHitPoints(),
                "Total hit-points should not change.");
        assertEquals(40, creature.getMaxHitPoints(),
                "Max hit-points (the upper bound) should change.");

        // after buff is gone, the actual hit-points should be the same
        // and upper bound should remain the new value
        creature.clearHealthBuff();
        assertEquals(30, creature.getHitPoints(),
                "Base hit-points incorrect.");
        assertEquals(40, creature.getMaxHitPoints(),
                "Max hit-points incorrect.");
    }

    @Test
    public void testInvokeEffect_reducingMax() {
        negativeEffect.invokeEffect(creature);
        assertEquals(20, creature.getMaxHitPoints(),
                "Max hit-points incorrect.");
        assertEquals(20, creature.getHitPoints(),
                "Hit-points should not exceed Max hit-points (upper bound)");
        assertEquals(20, creature.getTotalHitPoints(),
                "Total hit-points incorrect.");
    }
}