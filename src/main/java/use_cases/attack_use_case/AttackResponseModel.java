package use_cases.attack_use_case;

import use_cases.CreatureCardModel;

import java.util.List;

public class AttackResponseModel {

    private int attackerId;
    private CreatureCardModel attackerData;
    private int targetId;
    private CreatureCardModel targetData;
    private List<Integer> defenderIds;
    private List<CreatureCardModel> defenderData;

    public AttackResponseModel(int attackerId, CreatureCardModel attackerData, int targetId, CreatureCardModel targetData, List<Integer> defenderIds, List<CreatureCardModel> defenderData) {
        this.attackerId = attackerId;
        this.attackerData = attackerData;
        this.targetId = targetId;
        this.targetData = targetData;
        this.defenderIds = defenderIds;
        this.defenderData = defenderData;
    }

    public int getAttackerId() {
        return attackerId;
    }

    public void setAttackerId(int attackerId) {
        this.attackerId = attackerId;
    }

    public CreatureCardModel getAttackerData() {
        return attackerData;
    }

    public void setAttackerData(CreatureCardModel attackerData) {
        this.attackerData = attackerData;
    }

    public int getTargetId() {
        return targetId;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }

    public CreatureCardModel getTargetData() {
        return targetData;
    }

    public void setTargetData(CreatureCardModel targetData) {
        this.targetData = targetData;
    }

    public List<Integer> getDefenderIds() {
        return defenderIds;
    }

    public void setDefenderIds(List<Integer> defenderIds) {
        this.defenderIds = defenderIds;
    }

    public List<CreatureCardModel> getDefenderData() {
        return defenderData;
    }

    public void setDefenderData(List<CreatureCardModel> defenderData) {
        this.defenderData = defenderData;
    }
}
