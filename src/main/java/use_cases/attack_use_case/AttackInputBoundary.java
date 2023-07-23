package use_cases.attack_use_case;

public interface AttackInputBoundary {

    void initiateAttack(AttackRequestModel inputData);

    FinishAttackResponseModel defend(AttackRequestModel inputData);
}
