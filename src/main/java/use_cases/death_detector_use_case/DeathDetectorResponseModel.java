package use_cases.death_detector_use_case;

import java.util.List;

public class DeathDetectorResponseModel {

    private List<Integer> p1CreatureIds;
    private List<Integer> p2CreatureIds;
    private List<Integer> p1CreatureHitPoints;
    private List<Integer> p2CreatureHitPoints;
    private List<Integer> p1CreatureAttack;
    private List<Integer> p2CreatureAttack;

    public DeathDetectorResponseModel(List<Integer> p1CreatureIds, List<Integer> p2CreatureIds,
                                      List<Integer> p1CreatureHitPoints, List<Integer> p2CreatureHitPoints,
                                      List<Integer> p1CreatureAttack, List<Integer> p2CreatureAttack) {
        this.p1CreatureIds = p1CreatureIds;
        this.p2CreatureIds = p2CreatureIds;
        this.p1CreatureHitPoints = p1CreatureHitPoints;
        this.p2CreatureHitPoints = p2CreatureHitPoints;
        this.p1CreatureAttack = p1CreatureAttack;
        this.p2CreatureAttack = p2CreatureAttack;
    }

    public List<Integer> getP1CreatureIds() {
        return p1CreatureIds;
    }

    public void setP1CreatureIds(List<Integer> p1CreatureIds) {
        this.p1CreatureIds = p1CreatureIds;
    }

    public List<Integer> getP2CreatureIds() {
        return p2CreatureIds;
    }

    public void setP2CreatureIds(List<Integer> p2CreatureIds) {
        this.p2CreatureIds = p2CreatureIds;
    }

    public List<Integer> getP1CreatureHitPoints() {
        return p1CreatureHitPoints;
    }

    public void setP1CreatureHitPoints(List<Integer> p1CreatureHitPoints) {
        this.p1CreatureHitPoints = p1CreatureHitPoints;
    }

    public List<Integer> getP2CreatureHitPoints() {
        return p2CreatureHitPoints;
    }

    public void setP2CreatureHitPoints(List<Integer> p2CreatureHitPoints) {
        this.p2CreatureHitPoints = p2CreatureHitPoints;
    }

    public List<Integer> getP1CreatureAttack() {
        return p1CreatureAttack;
    }

    public void setP1CreatureAttack(List<Integer> p1CreatureAttack) {
        this.p1CreatureAttack = p1CreatureAttack;
    }

    public List<Integer> getP2CreatureAttack() {
        return p2CreatureAttack;
    }

    public void setP2CreatureAttack(List<Integer> p2CreatureAttack) {
        this.p2CreatureAttack = p2CreatureAttack;
    }
}
