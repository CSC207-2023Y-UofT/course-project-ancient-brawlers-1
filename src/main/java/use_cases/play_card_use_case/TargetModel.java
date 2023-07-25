package use_cases.play_card_use_case;

import java.util.List;

public class TargetModel {

    private int cardId;
    private List<Integer> targetIds;

    public TargetModel(int cardId, List<Integer> targetIds) {
        this.cardId = cardId;
        this.targetIds = targetIds;
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
}
