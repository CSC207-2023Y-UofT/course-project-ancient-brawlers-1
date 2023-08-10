package use_cases.death_detector_use_case;
/**
 * DeathDetectorOutputBoundary provides the interface for the presenter to display
 * changes to the game screen of when a creature has dropped to less than or equal 0 health
 */
public interface DeathDetectorOutputBoundary {

    /**
     * Response for setting the updated data for the two players in the View Model
     * @param outputData
     * @return DeathDetectorResponseModel
     */
    DeathDetectorResponseModel updateCreatureStats(DeathDetectorResponseModel outputData);
}
