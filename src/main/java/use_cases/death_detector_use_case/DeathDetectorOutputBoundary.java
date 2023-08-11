package use_cases.death_detector_use_case;

/**
 * DeathDetectorOutputBoundary provides the interface for the presenter to display
 * changes to the game screen of when a creature is defeated.
 */
public interface DeathDetectorOutputBoundary {

    /**
     * Loads data for the two players into the corresponding View Model.
     *
     * @param outputData contains the updated id, hit-points, and attacks for
     *                   all the creatures.
     */
    DeathDetectorResponseModel updateCreatureStats(DeathDetectorResponseModel outputData);
}
