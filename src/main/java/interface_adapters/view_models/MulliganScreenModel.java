package interface_adapters.view_models;

import java.util.List;

public class MulliganScreenModel {

    private List<String> cardNames;
    private List<Integer> cardIds;

    public List<String> getCardNames() {
        return cardNames;
    }

    public void setCardNames(List<String> cardNames) {
        this.cardNames = cardNames;
    }

    public List<Integer> getCardIds() {
        return cardIds;
    }

    public void setCardIds(List<Integer> cardIds) {
        this.cardIds = cardIds;
    }
}
