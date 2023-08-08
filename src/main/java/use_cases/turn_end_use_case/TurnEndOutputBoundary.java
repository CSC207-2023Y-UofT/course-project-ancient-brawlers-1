package use_cases.turn_end_use_case;

public interface TurnEndOutputBoundary {

    TurnEndResponseModel showTurnEndScreen(TurnEndResponseModel responseModel);

    void notifyTurnChange(String nextPlayer);
}
