package use_cases.turn_end_use_case;

import java.util.List;

public class TurnEndResponseModel {

    private List<Integer> hitPoints1;
    private List<Integer> hitPoints2;
    private List<Integer> attack1;
    private List<Integer> attack2;
    private List<Integer> creatureIds1;
    private List<Integer> creatureIds2;
    private List<Integer> playerHandIds1;
    private List<Integer> playerHandIds2;
    private List<String> playerHandNames1;
    private List<String> playerHandNames2;
    private List<String> playerHandDescriptions1;
    private List<String> playerHandDescriptions2;

    public TurnEndResponseModel(List<Integer> hitPoints1, List<Integer> hitPoints2,
                                List<Integer> attack1, List<Integer> attack2, List<Integer> creatureIds1,
                                List<Integer> creatureIds2, List<Integer> playerHandIds1, List<Integer> playerHandIds2,
                                List<String> playerHandNames1, List<String> playerHandNames2, List<String> playerHandDescriptions1, List<String> playerHandDescriptions2) {
        this.hitPoints1 = hitPoints1;
        this.hitPoints2 = hitPoints2;
        this.attack1 = attack1;
        this.attack2 = attack2;
        this.creatureIds1 = creatureIds1;
        this.creatureIds2 = creatureIds2;
        this.playerHandIds1 = playerHandIds1;
        this.playerHandIds2 = playerHandIds2;
        this.playerHandNames1 = playerHandNames1;
        this.playerHandNames2 = playerHandNames2;
        this.playerHandDescriptions1 = playerHandDescriptions1;
        this.playerHandDescriptions2 = playerHandDescriptions2;
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

    public List<Integer> getAttack1() {
        return attack1;
    }

    public void setAttack1(List<Integer> attack1) {
        this.attack1 = attack1;
    }

    public List<Integer> getAttack2() {
        return attack2;
    }

    public void setAttack2(List<Integer> attack2) {
        this.attack2 = attack2;
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

    public List<Integer> getPlayerHandIds1() {
        return playerHandIds1;
    }

    public void setPlayerHandIds1(List<Integer> playerHandIds1) {
        this.playerHandIds1 = playerHandIds1;
    }

    public List<Integer> getPlayerHandIds2() {
        return playerHandIds2;
    }

    public void setPlayerHandIds2(List<Integer> playerHandIds2) {
        this.playerHandIds2 = playerHandIds2;
    }

    public List<String> getPlayerHandNames1() {
        return playerHandNames1;
    }

    public void setPlayerHandNames1(List<String> playerHandNames1) {
        this.playerHandNames1 = playerHandNames1;
    }

    public List<String> getPlayerHandNames2() {
        return playerHandNames2;
    }

    public void setPlayerHandNames2(List<String> playerHandNames2) {
        this.playerHandNames2 = playerHandNames2;
    }

    public List<String> getPlayerHandDescriptions1() {
        return playerHandDescriptions1;
    }

    public void setPlayerHandDescriptions1(List<String> playerHandDescriptions1) {
        this.playerHandDescriptions1 = playerHandDescriptions1;
    }

    public List<String> getPlayerHandDescriptions2() {
        return playerHandDescriptions2;
    }

    public void setPlayerHandDescriptions2(List<String> playerHandDescriptions2) {
        this.playerHandDescriptions2 = playerHandDescriptions2;
    }
}
