package entities.cards;

/**
 * CreatureCard represents a Creature in the game, with all the information
 * belonging to that Creature. The information includes: HP, attack, costs (for
 * attacking and defending), and additional stats like buffs (added together
 * with the base HP or attack) or the stun state (indicating whether the Creature
 * can do an attack/defend).
 */
public class CreatureCard extends Card {

    private int hitPoints;
    private int maxHitPoints;
    private int attackDamage;
    private final int attackCost;
    private final int defendCost;
    private int healthBuff = 0;
    private int damageBuff = 0;
    private boolean stunned = false;

    /**
     * Constructs a CreatureCard instance. The only required parameters are the
     * base stats, which does not include the buffs or the stun state. The
     * default {@code maxHitPoints} value is the same as the input {@code hitPoints}
     * value.
     *
     * @param id           the id of this CreatureCard.
     * @param name         the name of this CreatureCard.
     * @param hitPoints    the hit-points of this CreatureCard, the maxHitPoints
     *                     will also be set to this value.
     * @param attackDamage the attack damage of this CreatureCard.
     * @param attackCost   the number of Essence needed to attack.
     * @param defendCost   the number of Essence needed to defend.
     */
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

    /**
     * Return the total hit-points of this creature. The total includes the buff.
     *
     * @return the total hit-points value.
     */
    public int getTotalHitPoints() {
        return hitPoints + healthBuff;
    }

    /**
     * Return the total attack of this creature. The total includes the buff.
     *
     * @return the total attack value.
     */
    public int getTotalAttackDamage() {
        return attackDamage + damageBuff;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    /**
     * Setter for the {@code maxHitPoints} attribute of this creature.
     * Changing the {@code maxHitPoints} may affect the {@code hitPoints},
     * in that the hitPoints need to be less or equal to the maxHitPoints.
     *
     * @param maxHitPoints the new maxHitPoints value.
     */
    public void setMaxHitPoints(int maxHitPoints) {
        this.maxHitPoints = maxHitPoints;
        if (hitPoints > maxHitPoints) {
            hitPoints = maxHitPoints;
        } else if (maxHitPoints == 0) {
            hitPoints = 0;
        }
    }

    /**
     * Adding a buff value for the {@code hitPoints}.
     * Note, the buff can be negative.
     */
    public void addHealthBuff(int value) {
        healthBuff += value;
    }

    public void clearHealthBuff() {
        healthBuff = 0;
    }

    /**
     * Adding a buff value for the {@code attackDamage}.
     * Note, the buff can be negative.
     */
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

    /**
     * Heal this creature by the given {@code value}, which means adding the
     * value to the {@code hitPoints}.
     * Note, the final value cannot exceed {@code maxHitPoints}.
     *
     * @param value the amount to be healed by.
     */
    public void heal(int value) {
        hitPoints += value;
        if (hitPoints > maxHitPoints) {
            hitPoints = maxHitPoints;
        }
    }

    /**
     * Damage the creature by the given {@code damage}, which means subtracting
     * the value from the {@code hitPoints}.
     * Note, when the {@code healthBuff} is nonzero, the damage is subtracted
     * from the healthBuff first. If there are remaining damage after the
     * healthBuff has been spent, then it will be subtracted from hitPoints.
     *
     * @param damage the amount to be damaged by.
     */
    public void takeDamage(int damage) {
        if (damage > healthBuff) {
            hitPoints = getTotalHitPoints() - damage;
            healthBuff = 0;
        } else {
            healthBuff -= damage;
        }
    }
}
