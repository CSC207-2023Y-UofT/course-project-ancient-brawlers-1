package entities.cards;

import entities.GameEvent;

/**
 * CardFactory is a factory class used to separate the object construction from
 * the rest of the code.
 * CardFactory also has a special usage, that is to assign unique card ids to
 * all the card objects created during a game. Every time a card is constructed,
 * the {@code cardId} attribute will be incremented by 1. Positive values are
 * used to indicate cards that are in normal conditions (for example, creatures
 * with positive ids are still alive).
 */
public class CardFactory {

    private int cardId = 1;

    public Card createCreatureCard(String name, int hitPoints, int attackDamage,
                                   int attackCost, int defendCost) {
        return new CreatureCard(cardId++, name, hitPoints, attackDamage,
                                attackCost, defendCost);
    }

    public Card createEssenceCard() {
        return new EssenceCard(cardId++);
    }

    public Card createActionCard(String name, PlayableCardData playableData) {
        return new ActionCard(cardId++, name, playableData);
    }

    public Card createStructureCard(String name, PlayableCardData playableData,
                                    GameEvent triggerEvent) {
        return new StructureCard(cardId++, name, playableData, triggerEvent);
    }
}
