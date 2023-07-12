package entities.cardEffects;

import entities.cards.CreatureCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DamageModifyEffectTest {

    private CreatureCard creature;
    private DamageModifyEffect effect;

    @BeforeEach
    public void setUp() {
        creature = new CreatureCard(1, "some name", 30, 5, 2, 1);
        effect = new DamageModifyEffect(5);
    }

    @Test
    public void testInvokeEffect_noBuff() {
        effect.invokeEffect(creature);
        assertEquals(10, creature.getAttackDamage(),
                "Base attack damage not correct.");
        assertEquals(10, creature.getTotalAttackDamage(),
                "Total attack damage not correct.");
    }

    @Test
    public void testInvokeEffect_withBuff() {
        // add a buff so now creature's total attack should be 15
        creature.addDamageBuff(10);
        assertEquals(15, creature.getTotalAttackDamage());

        // apply effect, the total increases, but base attack should also change
        effect.invokeEffect(creature);
        assertEquals(10, creature.getAttackDamage(),
                "Base attack incorrect.");
        assertEquals(20, creature.getTotalAttackDamage(),
                "Total attack incorrect.");

        // after buff is gone, base attack should still remain modified
        creature.clearDamageBuff();
        assertEquals(10, creature.getAttackDamage(),
                "Base attack incorrect.");
        assertEquals(10, creature.getTotalAttackDamage(),
                "Total attack incorrect.");
    }
}