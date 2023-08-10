package use_cases.turn_start_use_case;

/**
 * TurnStartOutputBoundary provides the interface for the presenter to implement. It is split into
 * three different sections.
 * - Showing the draw results
 * - Showing the cards after the buffs have removed
 * - Showing the creatures after the buffs have been implemented
 *
 */
public interface TurnStartOutputBoundary {

    /**
     *
     * @param outputData
     */
    DrawCardOutputModel showDrawResult(DrawCardOutputModel outputData);

    CreatureStatsUpdateModel showClearBuffs(CreatureStatsUpdateModel outputData);

    TriggerEffectUpdateModel showEffectUpdates(TriggerEffectUpdateModel outputData);
}
