package entities.cardEffects;

import entities.cards.CreatureCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DamageEffectTest {

    @Test
    void testInvokeEffect() {
        CreatureCard creature = new CreatureCard(1, "some name", 30, 5, 2, 1);
        DamageEffect effect = new DamageEffect(5);
        effect.invokeEffect(creature);
        assertEquals(25, creature.getHitPoints());
    }
}