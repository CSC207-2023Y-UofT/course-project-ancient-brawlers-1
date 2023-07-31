package interface_adapters.view_models;

import java.util.List;

public class TargetSelectScreenModel {

    private String effectiveCardName;
    private int effectiveCardId;
    private List<String> targetNames;
    private List<Integer> targetIds;

    public String getEffectiveCardName() {
        return effectiveCardName;
    }

    public void setEffectiveCardName(String effectiveCardName) {
        this.effectiveCardName = effectiveCardName;
    }

    public int getEffectiveCardId() {
        return effectiveCardId;
    }

    public void setEffectiveCardId(int effectiveCardId) {
        this.effectiveCardId = effectiveCardId;
    }

    public List<String> getTargetNames() {
        return targetNames;
    }

    public void setTargetNames(List<String> targetNames) {
        this.targetNames = targetNames;
    }

    public List<Integer> getTargetIds() {
        return targetIds;
    }

    public void setTargetIds(List<Integer> targetIds) {
        this.targetIds = targetIds;
    }
}
