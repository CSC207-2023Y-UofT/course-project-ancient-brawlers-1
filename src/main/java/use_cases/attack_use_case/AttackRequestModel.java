package use_cases.attack_use_case;

public class AttackRequestModel {
    /**
     * The AttackRequestModel contains the attacking creature id and the defending creature id
     */
    private int attackerId;
    private int targetId;

    public AttackRequestModel(int attackerId, int targetId) {
        this.attackerId = attackerId;
        this.targetId = targetId;
    }

    public int getAttackerId() {
        return attackerId;
    }
    public int getTargetId() {
        return targetId;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }

    public void setAttackerId(int attackerId) {
        this.attackerId = attackerId;
    }
}
