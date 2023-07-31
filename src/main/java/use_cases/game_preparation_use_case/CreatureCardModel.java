package use_cases.game_preparation_use_case;

public class CreatureCardModel implements CardModel {

    private String name;
    private int attackCost;
    private int defendCost;
    private int attackDamage;
    private int hitPoints;

    public CreatureCardModel() {
        // default constructor for json object mapper
    }

    public CreatureCardModel(String name, int attackCost, int defendCost, int attackDamage, int hitPoints) {
        this.name = name;
        this.attackCost = attackCost;
        this.defendCost = defendCost;
        this.attackDamage = attackDamage;
        this.hitPoints = hitPoints;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public int getAttackCost() {
        return attackCost;
    }

    public void setAttackCost(int attackCost) {
        this.attackCost = attackCost;
    }

    public int getDefendCost() {
        return defendCost;
    }

    public void setDefendCost(int defendCost) {
        this.defendCost = defendCost;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }
}
