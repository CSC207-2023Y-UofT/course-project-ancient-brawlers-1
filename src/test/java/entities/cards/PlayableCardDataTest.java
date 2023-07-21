package entities.cards;

import entities.cardEffects.CardEffect;
import entities.cardEffects.HealEffect;
import entities.cardEffects.HealthModifyEffect;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayableCardDataTest {

    PlayableCardData cardData;
    String desc;
    TargetType targetType;
    List<CardEffect> effects;

    @BeforeEach
    void setUp() {
        desc = "Give +5 HP to a creature";
        targetType = TargetType.ANY;
        effects = new ArrayList<>();
        effects.add(new HealEffect(5));
        effects.add(new HealthModifyEffect(5));
        cardData = new PlayableCardData(desc, targetType, effects);
    }

    @Test
    void getDescription() {
        assertEquals(desc, cardData.getDescription());
    }

    @Test
    void getTargetType() {
        assertEquals(targetType, cardData.getTargetType());
    }

    @Test
    void getEffects() {
        assertEquals(effects, cardData.getEffects());
    }
}
