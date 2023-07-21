package entities.cardEffects;

import entities.cards.CreatureCard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HealthBuffEffectTest {

    @Test
    void invokeEffect() {
        CreatureCard creature = new CreatureCard(1, "some name", 30, 5, 2, 1);
        HealthBuffEffect effect = new HealthBuffEffect(10);
        effect.invokeEffect(creature);
        assertEquals(40, creature.getTotalHitPoints());
        assertEquals(30, creature.getHitPoints());
    }
}