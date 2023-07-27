package use_cases.game_start_use_case;

import java.util.List;

public class GameStartRequestModel {

    private List<Integer> cardIds;
    private List<String> cardNames;

    public GameStartRequestModel(List<Integer> cardIds, List<String> cardNames) {
        this.cardIds = cardIds;
        this.cardNames = cardNames;
    }

    public List<Integer> getCardIds() {
        return cardIds;
    }

    public void setCardIds(List<Integer> cardIds) {
        this.cardIds = cardIds;
    }

    public List<String> getCardNames() {
        return cardNames;
    }

    public void setCardNames(List<String> cardNames) {
        this.cardNames = cardNames;
    }
}
