package use_cases.play_card_use_case;

import use_cases.CreatureCardModel;

import java.util.List;

public class TargetModel {

    private int cardId;
    private String cardName;
    private String cardDescription;
    private List<Integer> targetIds;
    private List<CreatureCardModel> targetData;

    public TargetModel(int cardId, String cardName, String cardDescription, List<Integer> targetIds, List<CreatureCardModel> targetData) {
        this.cardId = cardId;
        this.cardName = cardName;
        this.cardDescription = cardDescription;
        this.targetIds = targetIds;
        this.targetData = targetData;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public List<Integer> getTargetIds() {
        return targetIds;
    }

    public void setTargetIds(List<Integer> targetIds) {
        this.targetIds = targetIds;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardDescription() {
        return cardDescription;
    }

    public void setCardDescription(String cardDescription) {
        this.cardDescription = cardDescription;
    }

    public List<CreatureCardModel> getTargetData() {
        return targetData;
    }

    public void setTargetData(List<CreatureCardModel> targetData) {
        this.targetData = targetData;
    }
}
