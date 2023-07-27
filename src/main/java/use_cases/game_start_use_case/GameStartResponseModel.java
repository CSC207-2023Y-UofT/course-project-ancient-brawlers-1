package use_cases.game_start_use_case;

import java.util.ArrayList;
import java.util.List;

public class GameStartResponseModel {

    private List<Integer> cardIds;
    private List<Integer> bonusCardIds;
    private List<String> cardNames;
    private List<String> bonusCardNames;

    public GameStartResponseModel(List<Integer> cardIds, List<String> cardNames) {
        this.cardIds = cardIds;
        this.cardNames = cardNames;
        this.bonusCardIds = new ArrayList<>();
        this.bonusCardNames = new ArrayList<>();
    }

    public GameStartResponseModel(List<Integer> cardIds, List<Integer> bonusCardIds,
                                  List<String> cardNames, List<String> bonusCardNames) {
        this.cardIds = cardIds;
        this.bonusCardIds = bonusCardIds;
        this.cardNames = cardNames;
        this.bonusCardNames = bonusCardNames;
    }

    public List<Integer> getCardIds() {
        return cardIds;
    }

    public void setCardIds(List<Integer> cardIds) {
        this.cardIds = cardIds;
    }

    public List<Integer> getBonusCardIds() {
        return bonusCardIds;
    }

    public void setBonusCardIds(List<Integer> bonusCardIds) {
        this.bonusCardIds = bonusCardIds;
    }

    public List<String> getCardNames() {
        return cardNames;
    }

    public void setCardNames(List<String> cardNames) {
        this.cardNames = cardNames;
    }

    public List<String> getBonusCardNames() {
        return bonusCardNames;
    }

    public void setBonusCardNames(List<String> bonusCardNames) {
        this.bonusCardNames = bonusCardNames;
    }
}
