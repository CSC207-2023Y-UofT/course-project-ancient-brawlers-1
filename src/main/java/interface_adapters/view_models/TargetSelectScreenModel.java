package interface_adapters.view_models;

import java.util.List;

public class TargetSelectScreenModel {

    private String cardName;
    private int cardId;
    private String cardDescription;
    private List<Integer> targetIds;
    private List<Integer> targetHP;
    private List<Integer> targetATK;
    private List<String> targetNames;

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getCardDescription() {
        return cardDescription;
    }

    public void setCardDescription(String cardDescription) {
        this.cardDescription = cardDescription;
    }

    public List<Integer> getTargetIds() {
        return targetIds;
    }

    public void setTargetIds(List<Integer> targetIds) {
        this.targetIds = targetIds;
    }

    public List<Integer> getTargetHP() {
        return targetHP;
    }

    public void setTargetHP(List<Integer> targetHP) {
        this.targetHP = targetHP;
    }

    public List<Integer> getTargetATK() {
        return targetATK;
    }

    public void setTargetATK(List<Integer> targetATK) {
        this.targetATK = targetATK;
    }

    public List<String> getTargetNames() {
        return targetNames;
    }

    public void setTargetNames(List<String> targetNames) {
        this.targetNames = targetNames;
    }
}
