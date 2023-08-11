package use_cases.death_detector_use_case;

/**
 * DeathDetectorInputBoundary provides the interface for checking to see if a
 * creature has dropped below 0 health. That is after the attacking
 *
 */
public interface DeathDetectorInputBoundary {

    /**
     * Checks for both players if any creature's hit-points has dropped to 0 or
     * below, and set them to some state that indicates they are defeated.
     * At the moment, an id of -1 indicates defeat.
     *
     * @return a DeathDetectorResponseModel containing the updated stats of both
     * players' creatures.
     */
    DeathDetectorResponseModel detectCreatureDeath();
}
