package use_cases.attack_use_case;

public interface AttackOutputBoundary {
    void showDefendInputScreen(AttackResponseModel responseModel);
    FinishAttackResponseModel exitDefendInputScreen(FinishAttackResponseModel responseModel);

    FinishAttackResponseModel displayFailMessage(String message);

}
