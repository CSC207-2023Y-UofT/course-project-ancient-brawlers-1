package entities.cardEffects;

import entities.cards.CreatureCard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StunEffectTest {

    @Test
    void testInvokeEffect() {
        CreatureCard creature = new CreatureCard(1, "some name", 30, 5, 2, 1);
        StunEffect effect = new StunEffect();
        effect.invokeEffect(creature);
        assertTrue(creature.isStunned());
    }
}