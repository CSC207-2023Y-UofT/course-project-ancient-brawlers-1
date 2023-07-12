package entities.cards;

import entities.cardEffects.CardEffect;
import entities.cardEffects.DamageEffect;
import entities.cardEffects.HealEffect;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CardFactoryTest {

    private CardFactory cardFactory;
    private PlayableCardData sampleAction;
    private PlayableCardData sampleSingleAction;
    private PlayableCardData sampleStructure;

    @BeforeEach
    public void setUp() {
        cardFactory = new CardFactory();

        String desc1 = "Deal 2 damage to all enemy creatures.";
        List<CardEffect> effects1 = new ArrayList<>();
        effects1.add(new DamageEffect(2));
        sampleAction = new PlayableCardData(desc1, TargetType.ENEMY, effects1);

        String desc2 = "Deal 5 damage to an enemy creature.";
        List<CardEffect> effects2 = new ArrayList<>();
        effects2.add(new DamageEffect(5));
        sampleSingleAction = new PlayableCardData(desc2, TargetType.SINGLE_ENEMY,
                                                  effects2);

        String desc3 = "At the end of your turn," +
                       " heal 2 HP to all friendly creatures";
        List<CardEffect> effects3 = new ArrayList<>();
        effects3.add(new HealEffect(2));
        sampleStructure = new PlayableCardData(desc3, TargetType.FRIENDLY, effects3);
    }

    @Test
    public void testCreateCreatureCard() {
        Card creature = cardFactory.createCreatureCard("Some name",
                20, 5, 2, 1);
        assertNotNull(creature, "Creature creation failed.");
        assertEquals("Some name", creature.getName());
        assertEquals(1, creature.getId(), "Card id not assigned correctly.");

        assertEquals(20, ((CreatureCard) creature).getHitPoints());
        assertEquals(5, ((CreatureCard) creature).getAttackDamage());
    }

    @Test
    public void testCreateEssenceCard() {

    }

    @Test
    public void testCreateActionCard() {

    }

    @Test
    public void testCreateSingleActionCard() {

    }

    @Test
    public void testCreateStructureCard() {

    }

    @Test
    public void testCardId_correctIncrement() {

    }
}