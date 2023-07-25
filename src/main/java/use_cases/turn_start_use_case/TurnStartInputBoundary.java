package use_cases.turn_start_use_case;

/**
 * TurnStartInputBoundary provides the interface for handling the start-of-turn
 * logic. The start-of-turn events are separated into 3 parts and handled
 * individually.
 * - The player taking their turn draws cards, which requires an update to their
 * hand cards.
 * - The player's creatures should be cleared of their HP and Attack buffs, and
 * this should have its own output data.
 * - The start of turn effects are triggered, if any. This requires a combination
 * of card and stats updates.
 */
public interface TurnStartInputBoundary {

    /**
     * Draws cards for the current player: 2 cards from their play deck and 2
     * more cards from their Essence deck (4 in total).
     *
     * @return a DrawCardOutputModel, showing which cards are successfully added
     * to the hand and which are discarded due to hand being full.
     */
    DrawCardOutputModel drawCards();

    /**
     * Clear the HP and Attack buffs on each creature of the current player.
     *
     * @return a CreatureStatsUpdateModel, containing creature ids and stats
     * like hit-points and attacks.
     */
    CreatureStatsUpdateModel clearBuffs();

    /**
     * Trigger the start-of-turn effects in the active structure of the current
     * player, if it does exist.
     * This method returns many values because the effects have a variety of
     * targets, so outcomes also vary.
     *
     * @return a TriggerEffectUpdateModel, a combination of a list of hand card
     * ids and a CreatureStatsUpdateModel.
     */
    TriggerEffectUpdateModel triggerTurnStartEffects();
}
