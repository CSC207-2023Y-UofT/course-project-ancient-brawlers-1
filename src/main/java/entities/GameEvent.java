package entities;

/**
 * The GameEvent values represent the different stages of the game.
 * These events are used by some cards to recognize the correct moment to trigger 
 * effects.
 * TURN_START: the start of a turn.
 * TURN_ATTACK: during the moment an attack is initiated.
 * TURN_END: the end of a turn.
 * CREATURE_DEATH: during the moment a Creature is defeated.
 */
public enum GameEvent {
    TURN_START,
    TURN_ATTACK,
    TURN_END,
    CREATURE_DEATH
}
