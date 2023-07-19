package entities.cards;

import entities.cardEffects.CardEffect;
import entities.cardEffects.DamageModifyEffect;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SingleTargetActionCardTest extends ActionCardTest {

    SingleTargetActionCard singleTargetAction;
    String desc;
    TargetType targetType;
    List<CardEffect> effects;

    @BeforeEach
    void setUp() {
        desc = "Give a friendly creature +5 attack.";
        targetType = TargetType.SINGLE_FRIENDLY;
        effects = new ArrayList<>();
        effects.add(new DamageModifyEffect(5));
        PlayableCardData cardData = new PlayableCardData(desc, targetType, effects);

        singleTargetAction = new SingleTargetActionCard(3, "Strength", cardData);
    }

    @Test
    void setTarget() {

    }

    @Test
    void getTarget() {
    }
}