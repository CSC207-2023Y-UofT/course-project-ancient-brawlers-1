package use_cases.turn_start_use_case;

import java.util.List;

public class CreatureStatsUpdateModel {

    private int playerIndex;
    private List<Integer> creatureIds1;
    private List<Integer> creatureIds2;
    private List<Integer> hitPoints1;
    private List<Integer> hitPoints2;
    private List<Integer> attacks1;
    private List<Integer> attacks2;

    public CreatureStatsUpdateModel(int playerIndex, List<Integer> creatureIds1, List<Integer> creatureIds2,
                                    List<Integer> hitPoints1, List<Integer> hitPoints2,
                                    List<Integer> attacks1, List<Integer> attacks2) {
        this.playerIndex = playerIndex;
        this.creatureIds1 = creatureIds1;
        this.creatureIds2 = creatureIds2;
        this.hitPoints1 = hitPoints1;
        this.hitPoints2 = hitPoints2;
        this.attacks1 = attacks1;
        this.attacks2 = attacks2;
    }

    public int getPlayerIndex() {
        return playerIndex;
    }

    public void setPlayerIndex(int playerIndex) {
        this.playerIndex = playerIndex;
    }

    public List<Integer> getCreatureIds1() {
        return creatureIds1;
    }

    public void setCreatureIds1(List<Integer> creatureIds1) {
        this.creatureIds1 = creatureIds1;
    }

    public List<Integer> getCreatureIds2() {
        return creatureIds2;
    }

    public void setCreatureIds2(List<Integer> creatureIds2) {
        this.creatureIds2 = creatureIds2;
    }

    public List<Integer> getHitPoints1() {
        return hitPoints1;
    }

    public void setHitPoints1(List<Integer> hitPoints1) {
        this.hitPoints1 = hitPoints1;
    }

    public List<Integer> getHitPoints2() {
        return hitPoints2;
    }

    public void setHitPoints2(List<Integer> hitPoints2) {
        this.hitPoints2 = hitPoints2;
    }

    public List<Integer> getAttacks1() {
        return attacks1;
    }

    public void setAttacks1(List<Integer> attacks1) {
        this.attacks1 = attacks1;
    }

    public List<Integer> getAttacks2() {
        return attacks2;
    }

    public void setAttacks2(List<Integer> attacks2) {
        this.attacks2 = attacks2;
    }
}
