package use_cases.attack_use_case;

import java.util.List;

/**
 * The FinishAttackResponseModel contains the updated hit-points of the creatures,
 * their id's, the players cards, and what other cards are left in each player's
 * hand.
 */
public class FinishAttackResponseModel {

    private List<Integer> hitPoints1;
    private List<Integer> hitPoints2;
    private List<Integer> creatureIds1;
    private List<Integer> creatureIds2;
    private List<Integer> handCardsIds1;
    private List<Integer> handCardsIds2;
    private List<String> handNames1;
    private List<String> handNames2;
    private List<String> handCardsDescription1;
    private List<String> handCardsDescription2;

    public FinishAttackResponseModel(List<Integer> hitPoints1, List<Integer> hitPoints2,
                                     List<Integer> creatureIds1, List<Integer> creatureIds2,
                                     List<Integer> handCardsIds1, List<Integer> handCardsIds2,
                                     List<String> handNames1, List<String> handNames2, List<String> handCardsDescription1, List<String> handCardsDescription2) {
        this.hitPoints1 = hitPoints1;
        this.hitPoints2 = hitPoints2;
        this.creatureIds1 = creatureIds1;
        this.creatureIds2 = creatureIds2;
        this.handCardsIds1 = handCardsIds1;
        this.handCardsIds2 = handCardsIds2;
        this.handNames1 = handNames1;
        this.handNames2 = handNames2;
        this.handCardsDescription1 = handCardsDescription1;
        this.handCardsDescription2 = handCardsDescription2;
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

    public List<String> getHandCardsDescription1() {
        return handCardsDescription1;
    }

    public void setHandCardsDescription1(List<String> handCardsDescription1) {
        this.handCardsDescription1 = handCardsDescription1;
    }

    public List<String> getHandCardsDescription2() {
        return handCardsDescription2;
    }

    public void setHandCardsDescription2(List<String> handCardsDescription2) {
        this.handCardsDescription2 = handCardsDescription2;
    }

    public List<String> getHandNames1() {
        return handNames1;
    }

    public void setHandNames1(List<String> handNames1) {
        this.handNames1 = handNames1;
    }

    public List<String> getHandNames2() {
        return handNames2;
    }

    public void setHandNames2(List<String> handNames2) {
        this.handNames2 = handNames2;
    }
}
