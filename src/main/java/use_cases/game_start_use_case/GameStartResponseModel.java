package use_cases.game_start_use_case;

import java.util.List;

public class GameStartResponseModel {

    private List<Integer> cardIds;
    private List<Integer> bonusCardIds;

    public GameStartResponseModel(List<Integer> cardIds, List<Integer> bonusCardIds) {
        this.cardIds = cardIds;
        this.bonusCardIds = bonusCardIds;
    }

    public List<Integer>  getCardIds() {
        return cardIds;
    }

    public void setCardIds(List<Integer>  cardIds) {
        this.cardIds = cardIds;
    }

    public List<Integer> getBonusCardIds() {
        return bonusCardIds;
    }

    public void setBonusCardIds(List<Integer> bonusCardIds) {
        this.bonusCardIds = bonusCardIds;
    }
}
