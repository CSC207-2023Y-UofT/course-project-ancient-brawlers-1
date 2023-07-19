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

    CardFactory cardFactory;
    PlayableCardData sampleAction;
    PlayableCardData sampleSingleAction;
    PlayableCardData sampleStructure;

    @BeforeEach
    void setUp() {
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
    void testCreateCreatureCard() {
        Card creature = cardFactory.createCreatureCard("Some name",
                20, 5, 2, 1);
        assertNotNull(creature, "Creature creation failed.");
        assertEquals("Some name", creature.getName());
        assertEquals(1, creature.getId(), "Incorrect card id.");

        assertEquals(20, ((CreatureCard) creature).getHitPoints());
        assertEquals(5, ((CreatureCard) creature).getAttackDamage());
    }

    @Test
    void testCreateEssenceCard() {
        Card essence = cardFactory.createEssenceCard();
        assertNotNull(essence, "Essence creation failed.");
        assertEquals("Essence", essence.getName(), "Incorrect name.");
    }

    @Test
    void testCreateActionCard() {
        Card action = cardFactory.createActionCard("Action1", sampleAction);
        assertNotNull(action, "Action creation failed.");
    }

    @Test
    void testCreateSingleActionCard() {

    }

    @Test
    void testCreateStructureCard() {

    }

    @Test
    void testCardId_correctIncrement() {

    }
}