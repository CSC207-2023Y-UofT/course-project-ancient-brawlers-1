package use_cases.game_start_use_case;

import java.util.ArrayList;
import java.util.List;

public class GameStartResponseModel {

    private int playerIndex;
    private List<Integer> cardIds;
    private List<Integer> bonusCardIds;
    private List<String> cardNames;
    private List<String> bonusCardNames;
    private List<String> cardDescs;
    private List<String> bonusCardDescs;

    public GameStartResponseModel(int playerIndex, List<Integer> cardIds, List<String> cardNames, List<String> cardDescs) {
        this.playerIndex = playerIndex;
        this.cardIds = cardIds;
        this.cardNames = cardNames;
        this.cardDescs = cardDescs;
        this.bonusCardIds = new ArrayList<>();
        this.bonusCardNames = new ArrayList<>();
        this.bonusCardDescs = new ArrayList<>();
    }

    public GameStartResponseModel(int playerIndex, List<Integer> cardIds, List<Integer> bonusCardIds,
                                  List<String> cardNames, List<String> bonusCardNames,
                                  List<String> cardDescs, List<String> bonusCardDescs) {
        this.playerIndex = playerIndex;
        this.cardIds = cardIds;
        this.bonusCardIds = bonusCardIds;
        this.cardNames = cardNames;
        this.cardDescs = cardDescs;
        this.bonusCardNames = bonusCardNames;
        this.bonusCardDescs = bonusCardDescs;
    }

    public int getPlayerIndex() {
        return playerIndex;
    }

    public void setPlayerIndex(int playerIndex) {
        this.playerIndex = playerIndex;
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

    public List<String> getCardDescs() {
        return cardDescs;
    }

    public void setCardDescs(List<String> cardDescs) {
        this.cardDescs = cardDescs;
    }

    public List<String> getBonusCardDescs() {
        return bonusCardDescs;
    }

    public void setBonusCardDescs(List<String> bonusCardDescs) {
        this.bonusCardDescs = bonusCardDescs;
    }
}
