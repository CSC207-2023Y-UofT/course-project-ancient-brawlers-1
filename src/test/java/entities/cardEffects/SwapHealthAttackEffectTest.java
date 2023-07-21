package entities.cardEffects;

import entities.cards.CreatureCard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SwapHealthAttackEffectTest {

    @Test
    void invokeEffect() {
        CreatureCard creature = new CreatureCard(1, "some name", 30, 5, 2, 1);
        SwapHealthAttackEffect effect = new SwapHealthAttackEffect();
        effect.invokeEffect(creature);
        assertEquals(5, creature.getHitPoints());
        assertEquals(30, creature.getAttackDamage());
    }
}