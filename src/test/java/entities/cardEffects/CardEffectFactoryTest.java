package entities.cardEffects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardEffectFactoryTest {

    @Test
    void testCreateEffect1() {
        CardEffect effect = CardEffectFactory.createEffect("HealEffect", 5);
        assertTrue(effect instanceof HealEffect);
        CardEffect effect2 = CardEffectFactory.createEffect("DamageModifyEffect", 5);
        assertTrue(effect2 instanceof DamageModifyEffect);
    }

    @Test
    void testCreateEffect2() {
        CardEffect effect = CardEffectFactory.createEffect("StunEffect");
        assertTrue(effect instanceof StunEffect);
    }

    @Test
    void testCreateEffect3() {
        CardEffect effect = CardEffectFactory.createEffect("DrawCardEffect", 2, "PLAYER_DECK");
        assertTrue(effect instanceof DrawCardEffect);
    }
}