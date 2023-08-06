package interface_adapters.presenters;

import use_cases.turn_end_use_case.TurnEndOutputBoundary;
import use_cases.turn_end_use_case.TurnEndResponseModel;

public class TurnEndPresenter implements TurnEndOutputBoundary {

    @Override
    public TurnEndResponseModel showTurnEndScreen(TurnEndResponseModel responseModel) {
        return null;
    }
}
