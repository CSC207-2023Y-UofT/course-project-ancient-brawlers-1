package use_cases.attack_use_case;

/**
 * AttackInputBoundary provides the interface for handling the logic of the
 * attack phase of the game.
 */
public interface AttackInputBoundary {

    /**
     * Gathers and displays information about the attacking creature, target
     * creature, and possible defender creatures, to the player under attack.
     * This method sends to the presenter an AttackResponseModel consisting of
     * the ids of the creatures described above. The presenter will then ask the
     * player under attack to make the next decision.
     *
     * @param inputData the AttackRequestModel containing the attacking creature's
     *                  id, and the target creature's id.
     */
    void initiateAttack(AttackRequestModel inputData);

    /**
     * Process the attack using the attacker and target in the AttackRequestModel.
     * The target in this AttackRequestModel must be the final target to receive
     * the attack.
     *
     * @param inputData the AttackRequestModel containing the attacking creature's
     *                  id, and the target creature's id.
     * @return the FinishAttackResponseModel reporting the updates to the stats
     * of creatures and stats of players in the game.
     */
    FinishAttackResponseModel proceedAttack(AttackRequestModel inputData);

    /**
     * Process the attack using the attacker and target in the AttackRequestModel,
     * where the target in this case is the defender. The player who chose to
     * defend will also be deducted the corresponding number of Essence cards
     * from their hand.
     *
     * @param inputData the AttackRequestModel containing the attacking creature's
     *                  id, and the target (defender) creature's id.
     * @return the FinishAttackResponseModel reporting the updates to the stats
     * of creatures and stats of players in the game.
     */
    FinishAttackResponseModel defend(AttackRequestModel inputData);
}
