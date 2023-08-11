package use_cases.turn_end_use_case;

public interface TurnEndInputBoundary {


    void passTurn();

    /**
     * Trigger the end-of-turn effects in the active structure of the current
     * player, if it does exist.
     *
     * @return a TurnEndResponseModel containing all the data (creature stats,
     * player stats) that may have been affected by the end-of-turn effects.
     */
    TurnEndResponseModel triggerEndTurnEffects();
}
