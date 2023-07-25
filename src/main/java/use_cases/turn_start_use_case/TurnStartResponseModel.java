package use_cases.turn_start_use_case;

import java.util.ArrayList;
import java.util.List;
public class TurnStartResponseModel {

    private List<Integer> handIds1;
    private List<Integer> handIds2;
    private List<Integer> creatureIds1;
    private List<Integer> creatureIds2;
    private List<Integer> hitPoints1;
    private List<Integer> hitPoints2;
    private List<Integer> attacks1;
    private List<Integer> attacks2;

    public TurnStartResponseModel(List<Integer> handIds1, List<Integer> handIds2,
                                  List<Integer> creatureIds1, List<Integer> creatureIds2,
                                  List<Integer> hitPoints1, List<Integer> hitPoints2,
                                  List<Integer> attacks1, List<Integer> attacks2) {
        this.handIds1 = handIds1;
        this.handIds2 = handIds2;
        this.creatureIds1 = creatureIds1;
        this.creatureIds2 = creatureIds2;
        this.hitPoints1 = hitPoints1;
        this.hitPoints2 = hitPoints2;
        this.attacks1 = attacks1;
        this.attacks2 = attacks2;
    }

    public List<Integer> getHandIds1() {
        return handIds1;
    }

    public void setHandIds1(List<Integer> handIds1) {
        this.handIds1 = handIds1;
    }

    public List<Integer> getHandIds2() {
        return handIds2;
    }

    public void setHandIds2(List<Integer> handIds2) {
        this.handIds2 = handIds2;
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
