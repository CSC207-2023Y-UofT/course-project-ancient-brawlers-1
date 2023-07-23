package use_cases.attack_use_case;

public interface AttackInputBoundary {

    FinishAttackResponseModel initiateAttack(AttackRequestModel inputData);

    FinishAttackResponseModel defend(AttackRequestModel inputData);
}
