package entities.cardEffects;

public class CardEffectFactory {

    public static CardEffect createEffect(String effectType) {
        switch (effectType) {
            case "DestroyStructureEffect":
                return new DestroyStructureEffect();
            case "SwapHealthAttackEffect":
                return new SwapHealthAttackEffect();
            case "StunEffect":
                return new StunEffect();
            default:
                throw new IllegalArgumentException("Invalid effect type: " + effectType);
        }
    }

    public static CardEffect createEffect(String effectType, int effectValue) {
        switch (effectType) {
            case "HealEffect":
                return new HealEffect(effectValue);
            case "HealthModifyEffect":
                return new HealthModifyEffect(effectValue);
            case "DamageBuffEffect":
                return new DamageBuffEffect(effectValue);
            case "DamageEffect":
                return new DamageEffect(effectValue);
            case "DamageModifyEffect":
                return new DamageModifyEffect(effectValue);
            case "HealthBuffEffect":
                return new HealthBuffEffect(effectValue);
            default:
                throw new IllegalArgumentException("Invalid effect type: " + effectType);
        }
    }
    public static CardEffect createEffect(String effectType, int drawNumber, String drawDeck) {
        switch (effectType) {
            case "DrawCardEffect":
                return new DrawCardEffect(drawNumber, drawDeck);
            default:
                throw new IllegalArgumentException("Invalid effect type: " + effectType);
        }
    }
}
