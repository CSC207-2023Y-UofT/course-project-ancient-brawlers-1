package use_cases.turn_start_use_case;

/**
 * TurnStartOutputBoundary provides the interface for the presenter to receive
 * data from the TurnStartInteractor and to update the UI.
 */
public interface TurnStartOutputBoundary {

    /**
     *
     * @param outputData
     * @return
     */
    DrawCardOutputModel showDrawResult(DrawCardOutputModel outputData);

    CreatureStatsUpdateModel showClearBuffs(CreatureStatsUpdateModel outputData);

    TriggerEffectUpdateModel showEffectUpdates(TriggerEffectUpdateModel outputData);
}
