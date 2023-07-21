package entities.cardEffects;

import entities.cards.CreatureCard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HealEffectTest {

    @Test
    void testInvokeEffect() {
        CreatureCard creature = new CreatureCard(1, "some name", 30, 5, 2, 1);
        HealEffect effect = new HealEffect(10);
        creature.takeDamage(15);
        effect.invokeEffect(creature);
        assertEquals(25, creature.getHitPoints());
    }

}