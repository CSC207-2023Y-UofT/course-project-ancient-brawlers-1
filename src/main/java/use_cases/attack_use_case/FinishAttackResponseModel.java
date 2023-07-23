package use_cases.attack_use_case;

import entities.cards.CreatureCard;

import java.util.List;

public class FinishAttackResponseModel {
    private List<Integer> hitPoints1;
    private List<Integer> hitPoints2;
    private List<Integer> creatureIds1;
    private List<Integer> creatureIds2;

    private List<Integer> handCardsIds1;
    private List<Integer> handCardsIds2;

    public FinishAttackResponseModel(List<Integer> hitPoints1, List<Integer> hitPoints2,
                                     List<Integer> creatureIds1,  List<Integer> creatureIds2,
                                     List<Integer> handCardsIds1, List<Integer> handCardsIds2) {
        this.hitPoints1 = hitPoints1;
        this.hitPoints2 = hitPoints2;
        this.creatureIds1 = creatureIds1;
        this.creatureIds2 = creatureIds2;
        this.handCardsIds1 = handCardsIds1;
        this.handCardsIds2 = handCardsIds2;
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

    public List<Integer> getHandCardsIds1() {
        return handCardsIds1;
    }

    public void setHandCardsIds1(List<Integer> handCardsIds1) {
        this.handCardsIds1 = handCardsIds1;
    }

    public List<Integer> getHandCardsIds2() {
        return handCardsIds2;
    }

    public void setHandCardsIds2(List<Integer> handCardsIds2) {
        this.handCardsIds2 = handCardsIds2;
    }

}
