package use_cases.turn_start_use_case;

import java.util.List;

public class DrawCardOutputModel {

    private int playerIndex;
    private List<Integer> newCardIds;
    private List<String> newCardNames;
    private List<String> newCardDescriptions;
    private List<Integer> burntCardIds;
    private List<String> burntCardNames;

    public DrawCardOutputModel(int playerIndex, List<Integer> newCardIds, List<String> newCardNames,
                               List<String> newCardDescriptions, List<Integer> burntCardIds, List<String> burntCardNames) {
        this.playerIndex = playerIndex;
        this.newCardIds = newCardIds;
        this.newCardNames = newCardNames;
        this.newCardDescriptions = newCardDescriptions;
        this.burntCardIds = burntCardIds;
        this.burntCardNames = burntCardNames;
    }

    public int getPlayerIndex() {
        return playerIndex;
    }

    public void setPlayerIndex(int playerIndex) {
        this.playerIndex = playerIndex;
    }

    public List<Integer> getNewCardIds() {
        return newCardIds;
    }

    public void setNewCardIds(List<Integer> newCardIds) {
        this.newCardIds = newCardIds;
    }

    public List<String> getNewCardNames() {
        return newCardNames;
    }

    public void setNewCardNames(List<String> newCardNames) {
        this.newCardNames = newCardNames;
    }

    public List<String> getNewCardDescriptions() {
        return newCardDescriptions;
    }

    public void setNewCardDescriptions(List<String> newCardDescriptions) {
        this.newCardDescriptions = newCardDescriptions;
    }

    public List<Integer> getBurntCardIds() {
        return burntCardIds;
    }

    public void setBurntCardIds(List<Integer> burntCardIds) {
        this.burntCardIds = burntCardIds;
    }

    public List<String> getBurntCardNames() {
        return burntCardNames;
    }

    public void setBurntCardNames(List<String> burntCardNames) {
        this.burntCardNames = burntCardNames;
    }
}
