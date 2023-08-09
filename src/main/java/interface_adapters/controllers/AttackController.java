package interface_adapters.controllers;

import use_cases.attack_use_case.AttackInputBoundary;
import use_cases.attack_use_case.AttackRequestModel;
import use_cases.attack_use_case.FinishAttackResponseModel;

public class AttackController {

    final AttackInputBoundary attackInterator;

    public AttackController(AttackInputBoundary attackInterator){
        this.attackInterator = attackInterator;
    }

    public void initiateAttack(int attackerId, int targetId) {
        AttackRequestModel requestModel = new AttackRequestModel(attackerId, targetId);
        attackInterator.initiateAttack(requestModel);
    }

    public FinishAttackResponseModel defend(int attackerId, int defenderId){
        AttackRequestModel requestModel = new AttackRequestModel(attackerId, defenderId);
        return attackInterator.defend(requestModel);
    }

    public FinishAttackResponseModel processAttack(int attackerId, int defenderId){
        AttackRequestModel requestModel = new AttackRequestModel(attackerId, defenderId);
        return attackInterator.proceedAttack(requestModel);
    }
}
