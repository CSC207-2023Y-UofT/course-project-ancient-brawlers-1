package use_cases.turn_end_use_case;

public interface TurnEndInputBoundary {
    /**
     * Handles basic mechanics of swiftly passing the turn to the next player in line.
     * No params needed here
     */
     void passTurn();

    /**
     * Onset any effects from action, structure, essence
     */

     TurnEndResponseModel triggerEndTurnEffects();
}
