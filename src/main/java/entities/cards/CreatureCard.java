package entities.cards;

public class CreatureCard extends Card {
    private int hitPoints;
    private int maxHitPoints;
    private int attackDamage;
    private final int attackCost;
    private final int defendCost;
    private int healthBuff = 0;
    private int damageBuff = 0;
    private boolean stunned = false;

    public CreatureCard(int id, String name, int hitPoints, int attackDamage,
                        int attackCost, int defendCost) {
        super(id, name);
        this.hitPoints = hitPoints;
        this.maxHitPoints = hitPoints;
        this.attackDamage = attackDamage;
        this.attackCost = attackCost;
        this.defendCost = defendCost;
    }

    // Getters
    public int getHitPoints() {
        return hitPoints;
    }

    public int getMaxHitPoints() {
        return maxHitPoints;
    }

    public int getAttackDamage() {
        return attackDamage;
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

    // Special Getters
    public int getTotalHitPoints() {
        return hitPoints + healthBuff;
    }

    public int getTotalAttackDamage() {
        return attackDamage + damageBuff;
    }

    // Setters or Modifiers
    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public void setMaxHitPoints(int maxHitPoints) {
        this.maxHitPoints = maxHitPoints;
        if (hitPoints > maxHitPoints) {
            hitPoints = maxHitPoints;
        } else if (maxHitPoints == 0) {
            hitPoints = 0;
        }
    }

    public void addHealthBuff(int value) {
        healthBuff += value;
    }

    public void clearHealthBuff() {
        healthBuff = 0;
    }

    public void addDamageBuff(int value) {
        damageBuff += value;
    }

    public void clearDamageBuff() {
        damageBuff = 0;
    }

    public void stun() {
        stunned = true;
    }

    public void clearStun() {
        stunned = false;
    }

    public void heal(int value) {
        hitPoints += value;
        if (hitPoints > maxHitPoints) {
            hitPoints = maxHitPoints;
        }
    }

    public void takeDamage(int damage) {
        if (damage > healthBuff) {
            hitPoints = getTotalHitPoints() - damage;
            healthBuff = 0;
        } else {
            healthBuff -= damage;
        }
    }
}
