package entities.cards;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreatureCardTest {

    private CreatureCard creature;

    @BeforeEach
    public void setUp() {
        creature = new CreatureCard(1, "some name", 30, 5, 2, 1);
    }

    @Test
    public void testGettersRegular() {
        assertEquals(1, creature.getId());
        assertEquals("some name", creature.getName());
        assertEquals(30, creature.getHitPoints());
        assertEquals(30, creature.getMaxHitPoints());
        assertEquals(5, creature.getAttackDamage());
        assertEquals(2, creature.getAttackCost());
        assertEquals(1, creature.getDefendCost());
    }

    @Test
    public void testSettersRegular() {
        creature.setMaxHitPoints(20);
        assertEquals(20, creature.getMaxHitPoints());
        assertEquals(20, creature.getHitPoints());
        creature.setAttackDamage(10);
        assertEquals(10, creature.getAttackDamage());
        // note to self: these changes won't affect other test cases
        // because setUp() is run before every method.
        // A new creature will be created for each method.
    }

    @Test
    public void testHealthBuff() {
        assertEquals(30, creature.getTotalHitPoints());

        creature.addHealthBuff(10);
        assertEquals(40, creature.getTotalHitPoints());

        creature.clearHealthBuff();
        assertEquals(30, creature.getTotalHitPoints());
    }

    @Test
    public void testDamageBuff() {
        assertEquals(5, creature.getTotalAttackDamage());

        creature.addDamageBuff(10);
        assertEquals(15, creature.getTotalAttackDamage());

        creature.clearDamageBuff();
        assertEquals(5, creature.getTotalAttackDamage());
    }

    @Test
    public void testStun() {
        assertFalse(creature.isStunned());

        creature.stun();
        assertTrue(creature.isStunned());

        creature.clearStun();
        assertFalse(creature.isStunned());
    }

    @Test
    public void testDamageAndHeal_Regular_BelowMax() {
        creature.takeDamage(10);
        assertEquals(20, creature.getTotalHitPoints(),
                "Damage not computed correctly.");
        creature.heal(10);
        assertEquals(30, creature.getTotalHitPoints(),
                "Heal not computed correctly.");
    }

    @Test
    public void testDamageAndHeal_Regular_AboveMax() {
        creature.takeDamage(10);
        assertEquals(20, creature.getTotalHitPoints(),
                "Damage not computed correctly.");
        creature.heal(20);
        assertEquals(30, creature.getTotalHitPoints(),
                "HitPoints exceeding Maximum!");
    }

    @Test
    public void testDamageAndHeal_HealthBuff_damageSmallerThanBuff() {
        creature.addHealthBuff(20);
        assertEquals(50, creature.getTotalHitPoints(),
                "Health Buff not added correctly.");
        creature.takeDamage(15);
        assertEquals(35, creature.getTotalHitPoints(),
                "Total HitPoints not correct.");
        assertEquals(30, creature.getHitPoints(),
                "Base HitPoints not supposed to change!");
    }

    @Test
    public void testDamageAndHeal_HealthBuff_damageLargerThanBuff() {
        creature.addHealthBuff(20);
        assertEquals(50, creature.getTotalHitPoints(),
                "Health Buff not added correctly.");
        creature.takeDamage(40);
        assertEquals(10, creature.getTotalHitPoints(),
                "Total HitPoints not correct.");
        assertEquals(10, creature.getHitPoints(),
                "Base HitPoints should have changed.");
    }
}