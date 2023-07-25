package use_cases.turn_start_use_case;

public interface TurnStartInputBoundary {
    TurnStartResponseModel drawCards();

    TurnStartResponseModel clearTemporaryEffects();

    TurnStartResponseModel triggerTurnStartEffects();
}
