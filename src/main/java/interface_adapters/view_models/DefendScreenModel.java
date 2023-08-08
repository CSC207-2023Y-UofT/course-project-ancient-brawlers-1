package interface_adapters.view_models;

import java.util.List;

public class DefendScreenModel {

    private String attackerName, targetName;
    private int attackerId, targetId, attackerHP, attackerATK, targetHP, targetATK;
    private List<String> defenderNames;
    private List<Integer> defenderIds, defenderHP, defenderATK;

    public String getAttackerName() {
        return attackerName;
    }

    public void setAttackerName(String attackerName) {
        this.attackerName = attackerName;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
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

    public int getAttackerHP() {
        return attackerHP;
    }

    public void setAttackerHP(int attackerHP) {
        this.attackerHP = attackerHP;
    }

    public int getAttackerATK() {
        return attackerATK;
    }

    public void setAttackerATK(int attackerATK) {
        this.attackerATK = attackerATK;
    }

    public int getTargetHP() {
        return targetHP;
    }

    public void setTargetHP(int targetHP) {
        this.targetHP = targetHP;
    }

    public int getTargetATK() {
        return targetATK;
    }

    public void setTargetATK(int targetATK) {
        this.targetATK = targetATK;
    }

    public List<String> getDefenderNames() {
        return defenderNames;
    }

    public void setDefenderNames(List<String> defenderNames) {
        this.defenderNames = defenderNames;
    }

    public List<Integer> getDefenderIds() {
        return defenderIds;
    }

    public void setDefenderIds(List<Integer> defenderIds) {
        this.defenderIds = defenderIds;
    }

    public List<Integer> getDefenderHP() {
        return defenderHP;
    }

    public void setDefenderHP(List<Integer> defenderHP) {
        this.defenderHP = defenderHP;
    }

    public List<Integer> getDefenderATK() {
        return defenderATK;
    }

    public void setDefenderATK(List<Integer> defenderATK) {
        this.defenderATK = defenderATK;
    }
}
