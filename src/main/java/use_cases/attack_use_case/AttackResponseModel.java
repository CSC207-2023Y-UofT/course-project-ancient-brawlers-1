package use_cases.attack_use_case;

import java.util.List;

public class AttackResponseModel {

    private int attackerId;
    private int targetId;
    private List<Integer> defenderIds;

    public AttackResponseModel(int attackerId, int targetId, List<Integer> defenderIds) {
        this.attackerId = attackerId;
        this.targetId = targetId;
        this.defenderIds = defenderIds;
    }

    public int getAttackerId() {
        return attackerId;
    }

    public void setAttackerId(int attackerId) {
        this.attackerId = attackerId;
    }

    public int getTargetId() {
        return targetId;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }

    public List<Integer> getDefenderIds() {
        return defenderIds;
    }

    public void setDefenderIds(List<Integer> defenderIds) {
        this.defenderIds = defenderIds;
    }
}
