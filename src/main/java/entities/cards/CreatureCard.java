package entities.cards;

public class CreatureCard extends Card {
    private int hitPoints;
    private int maxHitPoints;
    private int attackDamage;
    private final int attackCost;
    private final int defendCost;
    private int temporaryHealthBoost = 0;
    private int temporaryDamageBoost = 0;
    private boolean stunned = false;

    public CreatureCard(int id, String name, int hitPoints, int attackDamage, int attackCost, int defendCost) {
        super(id, name);
        this.hitPoints = hitPoints;
        this.maxHitPoints = hitPoints;
        this.attackDamage = attackDamage;
        this.attackCost = attackCost;
        this.defendCost = defendCost;
    }

    // general getters and setters
    public int getHitPoints() {
        return hitPoints;
    }

    public int getMaxHitPoints() {
        return maxHitPoints;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public void setMaxHitPoints(int maxHitPoints) {
        this.maxHitPoints = maxHitPoints;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public void modifyMaxHitPoints(int amount) {
        this.maxHitPoints += amount;
        this.hitPoints += amount;
    }

    public void modifyAttackDamage(int amount) {
        this.attackDamage += amount;
    }

    public int getAttackCost() {
        return attackCost;
    }

    public int getDefendCost() {
        return defendCost;
    }

    public boolean isStunned() {
        return stunned;
    }

    public int getTotalHitPoints() {
        return hitPoints + temporaryHealthBoost;
    }

    public int getTotalAttackDamage() {
        return attackDamage + temporaryDamageBoost;
    }

    // Modifications to the creature's stats
    public void heal(int healAmount) {
        hitPoints += healAmount;
        if (hitPoints > maxHitPoints) {
            hitPoints = maxHitPoints;
        }
    }

    public void boostHealth(int boostAmount) {
        temporaryHealthBoost += boostAmount;
    }

    public void boostDamage(int boostAmount) {
        temporaryDamageBoost += boostAmount;
    }

    public void clearHealthBoost() {
        temporaryHealthBoost = 0;
    }

    public void clearDamageBoost() {
        temporaryDamageBoost = 0;
    }

    public void stun() {
        stunned = true;
    }

    public void clearStun() {
        stunned = false;
    }

    public void takeDamage(int damage) {
        if (damage > temporaryHealthBoost) {
            hitPoints = getTotalHitPoints() - damage;
        } else {
            temporaryHealthBoost -= damage;
        }
    }
}
