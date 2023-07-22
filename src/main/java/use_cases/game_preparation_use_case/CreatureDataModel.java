package use_cases.game_preparation_use_case;

public class CreatureDataModel {

    private String[] names;
    private int[] ids;
    private int[] hitPoints;
    private int[] attacks;
    private int[] attackCosts;
    private int[] defendCosts;

    public CreatureDataModel(String[] names, int[] ids, int[] hitPoints,
                        int[] attacks, int[] attackCosts, int[] defendCosts) {
        this.names = names;
        this.ids = ids;
        this.hitPoints = hitPoints;
        this.attacks = attacks;
        this.attackCosts = attackCosts;
        this.defendCosts = defendCosts;
    }

    public String[] getNames() {
        return names;
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    public int[] getIds() {
        return ids;
    }

    public void setIds(int[] ids) {
        this.ids = ids;
    }

    public int[] getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int[] hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int[] getAttacks() {
        return attacks;
    }

    public void setAttacks(int[] attacks) {
        this.attacks = attacks;
    }

    public int[] getAttackCosts() {
        return attackCosts;
    }

    public void setAttackCosts(int[] attackCosts) {
        this.attackCosts = attackCosts;
    }

    public int[] getDefendCosts() {
        return defendCosts;
    }

    public void setDefendCosts(int[] defendCosts) {
        this.defendCosts = defendCosts;
    }
}
