package use_cases.attack_use_case;

import java.util.ArrayList;
import java.util.List;
public class
AttackResponseModel {

    private int attackerId;
    private List<Integer> defenderIds;
    private int targetId;

    public AttackResponseModel(int attackerId, List<Integer> defenderIds, int targetId) {
        this.attackerId = attackerId;
        this.defenderIds = defenderIds;
        this.targetId = targetId;
    }
    public int getAttackerId() {
        return attackerId;
    }

    public void setAttackerId(int attackerId) {
        this.attackerId = attackerId;
    }

    public List<Integer> getDefenderIds() {
        return defenderIds;
    }

    public void setDefenderIds(List<Integer> defenderIds) {
        this.defenderIds = defenderIds;
    }

    public int getTargetId() { return targetId; }
}
