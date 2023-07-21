package entities.cards;

import entities.GameEvent;
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
    private PlayableCardData sampleStructure;

    @BeforeEach
    void setUp() {
        cardFactory = new CardFactory();

        String desc1 = "Deal 2 damage to all enemy creatures.";
        List<CardEffect> effects1 = new ArrayList<>();
        effects1.add(new DamageEffect(2));
        sampleAction = new PlayableCardData(desc1, TargetType.ENEMY, effects1);

        String desc2 = "At the end of your turn," +
                       " heal 2 HP to all friendly creatures";
        List<CardEffect> effects2 = new ArrayList<>();
        effects2.add(new HealEffect(2));
        sampleStructure = new PlayableCardData(desc2, TargetType.FRIENDLY, effects2);
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
    public void testCreateEssenceCard() {
        Card essence = cardFactory.createEssenceCard();
        assertNotNull(essence, "Essence creation failed.");
        assertEquals(1, essence.getId());
        assertEquals("Essence", essence.getName());
    }

    @Test
    public void testCreateActionCard() {
        Card action = cardFactory.createActionCard("A Name", sampleAction);
        assertNotNull(action, "Action creation failed.");
        assertEquals(sampleAction.getEffects(), ((ActionCard) action).getEffects());
    }

    @Test
    public void testCreateStructureCard() {
        Card structure = cardFactory.createStructureCard("Structure", sampleStructure,
                GameEvent.TURN_END);
        assertNotNull(structure, "Structure creation failed.");
        assertEquals(GameEvent.TURN_END, ((StructureCard) structure).getTriggerEvent());
    }

    @Test
    public void testCardId_correctIncrement() {
        Card action = cardFactory.createActionCard("action", sampleAction);
        Card essence = cardFactory.createEssenceCard();
        Card essence2 = cardFactory.createEssenceCard();

        assertEquals(1, action.getId());
        assertEquals(2, essence.getId());
        assertEquals(3, essence2.getId());
    }
}