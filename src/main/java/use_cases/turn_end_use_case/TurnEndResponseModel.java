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

    public TurnEndResponseModel(List<Integer> hitPoints1, List<Integer> hitPoints2, List<Integer> attack1, List<Integer> attack2, List<Integer> creatureIds1, List<Integer> creatureIds2, List<Integer> playerHandIds1, List<Integer> playerHandIds2, List<String> playerHandNames1, List<String> playerHandNames2) {
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
    }

    public List<Integer> getCreatureIds1() {
        return this.creatureIds1;
    }

    public List<Integer> getCreatureIds2() {
        return this.creatureIds2;
    }

    public List<Integer> getAttack1() {
        return this.attack1;
    }

    public List<Integer> getAttack2() {
        return this.attack2;
    }

    public List<Integer> getHitPoints1() {
        return this.hitPoints1;
    }

    public List<Integer> getHitPoints2() {
        return this.hitPoints2;
    }

    public List<Integer> getPlayerHandIds1() {
        return this.playerHandIds1;
    }

    public List<Integer> getPlayerHandIds2() {
        return this.playerHandIds2;
    }

    public List<String> getPlayerHandNames1() {
        return this.playerHandNames1;
    }

    public List<String> getPlayerHandNames2() {
        return this.playerHandNames2;
    }

    public void setAttack1(List<Integer> attack1) {
        this.attack1 = attack1;
    }

    public void setAttack2(List<Integer> attack2) {
        this.attack2 = attack2;
    }

    public void setHitPoints1(List<Integer> hitPoints1) {
        this.hitPoints1 = hitPoints1;
    }

    public void setHitPoints2(List<Integer> hitPoints2) {
        this.hitPoints2 = hitPoints2;
    }

    public void setCreatureIds1(List<Integer> creatureIds1) {
        this.creatureIds1 = creatureIds1;
    }

    public void setCreatureIds2(List<Integer> creatureIds2) {
        this.creatureIds2 = creatureIds2;
    }

    public void setPlayerHandsIds1(List<Integer> playerHandIds1) {
        this.playerHandIds1 = playerHandIds1;
    }

    public void setPlayerHandsIds2(List<Integer> playerHandIds2) {
        this.playerHandIds2 = playerHandIds2;
    }

    public void setPlayerHandsNames1(List<String> playerHandNames1) {
        this.playerHandNames1 = playerHandNames1;
    }

    public void setPlayerHandsNames2(List<String> playerHandNames2) {
        this.playerHandNames2 = playerHandNames2;
    }
    
}
