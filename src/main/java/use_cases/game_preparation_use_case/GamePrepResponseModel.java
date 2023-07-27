package use_cases.game_preparation_use_case;

import java.util.List;

public class GamePrepResponseModel {

    private String playerName1;
    private String playerName2;
    private List<Integer> creatureIds1;
    private List<Integer> creatureIds2;
    private List<String> creatureNames1;
    private List<String> creatureNames2;
    private List<Integer> hitPoints1;
    private List<Integer> hitPoints2;
    private List<Integer> attacks1;
    private List<Integer> attacks2;

    public GamePrepResponseModel(String playerName1, String playerName2,
                                 List<Integer> creatureIds1, List<Integer> creatureIds2,
                                 List<String> creatureNames1, List<String> creatureNames2,
                                 List<Integer> hitPoints1, List<Integer> hitPoints2,
                                 List<Integer> attacks1, List<Integer> attacks2) {
        this.playerName1 = playerName1;
        this.playerName2 = playerName2;
        this.creatureIds1 = creatureIds1;
        this.creatureIds2 = creatureIds2;
        this.creatureNames1 = creatureNames1;
        this.creatureNames2 = creatureNames2;
        this.hitPoints1 = hitPoints1;
        this.hitPoints2 = hitPoints2;
        this.attacks1 = attacks1;
        this.attacks2 = attacks2;
    }

    public String getPlayerName1() {
        return playerName1;
    }

    public void setPlayerName1(String playerName1) {
        this.playerName1 = playerName1;
    }

    public String getPlayerName2() {
        return playerName2;
    }

    public void setPlayerName2(String playerName2) {
        this.playerName2 = playerName2;
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

    public List<String> getCreatureNames1() {
        return creatureNames1;
    }

    public void setCreatureNames1(List<String> creatureNames1) {
        this.creatureNames1 = creatureNames1;
    }

    public List<String> getCreatureNames2() {
        return creatureNames2;
    }

    public void setCreatureNames2(List<String> creatureNames2) {
        this.creatureNames2 = creatureNames2;
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