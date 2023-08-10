package use_cases.death_detector_use_case;

/**
 *DeathDetectorInputBoundary provides the interface for checking to see if a creature has dropped below 0 health.
 * That is after the attacking
 *
 */
public interface DeathDetectorInputBoundary {

    /**
     * Used to check if a creature has dropped below 0 health so that the players creature stats
     * is updated accordingly
     * @return DeathDetectorResponseModel contains the updated stats of both the current player and the opponents
     *      * creature
     */
    DeathDetectorResponseModel detectCreatureDeath();
}
