package entities.cards;

import entities.GameEvent;
import entities.cardEffects.CardEffect;

import java.util.List;

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

    public Card createSingleTargetActionCard(String name,
                                             PlayableCardData playableData) {
        return new SingleTargetActionCard(cardId++, name, playableData);
    }

    public Card createStructureCard(String name, PlayableCardData playableData,
                                    GameEvent triggerEvent) {
        return new StructureCard(cardId++, name, playableData, triggerEvent);
    }
}

