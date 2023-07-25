package use_cases.play_card_use_case;

import java.util.List;

public class PlayCardOutputModel {

    private List<Integer> playHandIds;
    private String structure1 = "";
    private String structure2 = "";
    private List<Integer> creatureIds1;
    private List<Integer> creatureIds2;
    private List<Integer> hitPoints1;
    private List<Integer> hitPoints2;
    private List<Integer> attacks1;
    private List<Integer> attacks2;

    public PlayCardOutputModel(List<Integer> playHandIds, String structure1, String structure2,
                               List<Integer> creatureIds1, List<Integer> creatureIds2, List<Integer> hitPoints1,
                               List<Integer> hitPoints2, List<Integer> attacks1, List<Integer> attacks2) {
        this.playHandIds = playHandIds;
        this.structure1 = structure1;
        this.structure2 = structure2;
        this.creatureIds1 = creatureIds1;
        this.creatureIds2 = creatureIds2;
        this.hitPoints1 = hitPoints1;
        this.hitPoints2 = hitPoints2;
        this.attacks1 = attacks1;
        this.attacks2 = attacks2;
    }

    public List<Integer> getPlayHandIds() {
        return playHandIds;
    }

    public void setPlayHandIds(List<Integer> playHandIds) {
        this.playHandIds = playHandIds;
    }

    public String getStructure1() {
        return structure1;
    }

    public void setStructure1(String structure1) {
        this.structure1 = structure1;
    }

    public String getStructure2() {
        return structure2;
    }

    public void setStructure2(String structure2) {
        this.structure2 = structure2;
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
