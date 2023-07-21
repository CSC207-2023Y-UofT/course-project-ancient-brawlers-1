package entities.cardEffects;

import entities.cards.CreatureCard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DamageBuffEffectTest {

    @Test
    void invokeEffect() {
        CreatureCard creature = new CreatureCard(1, "some name", 30, 5, 2, 1);
        DamageBuffEffect effect = new DamageBuffEffect(5);
        effect.invokeEffect(creature);
        assertEquals(10, creature.getTotalAttackDamage());
        assertEquals(5, creature.getAttackDamage());
    }
}