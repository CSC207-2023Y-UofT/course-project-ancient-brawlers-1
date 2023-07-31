package interface_adapters.view_models;

import java.util.List;

public class PlayerDataModel {

    private String playerName;
    private List<String> creatureNames;
    private List<Integer> creatureIds;
    private List<Integer> creatureHPs;
    private List<Integer> creatureAttacks;
    private List<String> handCardNames;
    private List<Integer> handCardIds;
    private List<String> structureName;
    private List<Integer> structureId;

    /**
     * The constructor doesn't need player cards or deck info, because at the start
     * there are no cards distributed yet.
     */
    public PlayerDataModel(String playerName, List<String> creatureNames, List<Integer> creatureIds,
                           List<Integer> creatureHPs, List<Integer> creatureAttacks) {
        this.playerName = playerName;
        this.creatureNames = creatureNames;
        this.creatureIds = creatureIds;
        this.creatureHPs = creatureHPs;
        this.creatureAttacks = creatureAttacks;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public List<String> getCreatureNames() {
        return creatureNames;
    }

    public void setCreatureNames(List<String> creatureNames) {
        this.creatureNames = creatureNames;
    }

    public List<Integer> getCreatureIds() {
        return creatureIds;
    }

    public void setCreatureIds(List<Integer> creatureIds) {
        this.creatureIds = creatureIds;
    }

    public List<Integer> getCreatureHPs() {
        return creatureHPs;
    }

    public void setCreatureHPs(List<Integer> creatureHPs) {
        this.creatureHPs = creatureHPs;
    }

    public List<Integer> getCreatureAttacks() {
        return creatureAttacks;
    }

    public void setCreatureAttacks(List<Integer> creatureAttacks) {
        this.creatureAttacks = creatureAttacks;
    }

    public List<String> getHandCardNames() {
        return handCardNames;
    }

    public void setHandCardNames(List<String> handCardNames) {
        this.handCardNames = handCardNames;
    }

    public List<Integer> getHandCardIds() {
        return handCardIds;
    }

    public void setHandCardIds(List<Integer> handCardIds) {
        this.handCardIds = handCardIds;
    }

    public List<String> getStructureName() {
        return structureName;
    }

    public void setStructureName(List<String> structureName) {
        this.structureName = structureName;
    }

    public List<Integer> getStructureId() {
        return structureId;
    }

    public void setStructureId(List<Integer> structureId) {
        this.structureId = structureId;
    }
}
