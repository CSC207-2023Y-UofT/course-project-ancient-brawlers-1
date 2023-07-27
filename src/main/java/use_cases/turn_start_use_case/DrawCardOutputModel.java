package use_cases.turn_start_use_case;

import java.util.List;

public class DrawCardOutputModel {

    private List<Integer> newCardIds;
    private List<String> newCardNames;
    private List<Integer> burntCardIds;
    private List<String> burntCardNames;

    public DrawCardOutputModel(List<Integer> newCardIds, List<String> newCardNames,
                               List<Integer> burntCardIds, List<String> burntCardNames) {
        this.newCardIds = newCardIds;
        this.newCardNames = newCardNames;
        this.burntCardIds = burntCardIds;
        this.burntCardNames = burntCardNames;
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
