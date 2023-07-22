package use_cases.game_start_use_case;

import java.util.List;

public class GameStartRequestModel {

    private List<Integer> cardIds;

    public GameStartRequestModel(List<Integer>  cardIds) {
        this.cardIds = cardIds;
    }

    public List<Integer> getCardIds() {
        return cardIds;
    }

    public void setCardIds(List<Integer> cardIds) {
        this.cardIds = cardIds;
    }
}
