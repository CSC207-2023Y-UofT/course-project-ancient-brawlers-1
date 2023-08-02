package interface_adapters.view_models;

import java.util.List;

public class DefendScreenModel {

    private String attackerName;
    private int attackerId;
    private List<String> possibleDefenderNames;
    private List<Integer> possibleDefenderIds;

    public String getAttackerName() {
        return attackerName;
    }

    public void setAttackerName(String attackerName) {
        this.attackerName = attackerName;
    }

    public int getAttackerId() {
        return attackerId;
    }

    public void setAttackerId(int attackerId) {
        this.attackerId = attackerId;
    }

    public List<String> getPossibleDefenderNames() {
        return possibleDefenderNames;
    }

    public void setPossibleDefenderNames(List<String> possibleDefenderNames) {
        this.possibleDefenderNames = possibleDefenderNames;
    }

    public List<Integer> getPossibleDefenderIds() {
        return possibleDefenderIds;
    }

    public void setPossibleDefenderIds(List<Integer> possibleDefenderIds) {
        this.possibleDefenderIds = possibleDefenderIds;
    }
}
