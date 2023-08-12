package entities.cards;

/**
 * TargetType is used by {@code ActionCard} and {@code StructureCard} to indicate
 * the targets that their effects are used on.
 *
 */
public enum TargetType {

    /**
     * Targets a single friendly creature.
     */
    SINGLE_FRIENDLY,

    /**
     * Targets a single enemy creature.
     */
    SINGLE_ENEMY,

    /**
     * Targets all friendly creatures.
     */
    FRIENDLY,

    /**
     * Targets all enemy creatures.
     */
    ENEMY,

    /**
     * Targets the player who played this card.
     */
    SELF,

    /**
     * Targets the opponent player.
     */
    OPPONENT,

    /**
     * Targets any creature (a single target).
     */
    ANY,

    /**
     * Targets all creatures.
     */
    ALL
}
