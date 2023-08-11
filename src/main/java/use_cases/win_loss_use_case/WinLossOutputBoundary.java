package use_cases.win_loss_use_case;

/**
 * WinLossOutputBoundary provides the interface for the presenter to display
 * the winner of the game.
 */
public interface WinLossOutputBoundary {

    /**
     * Sends instruction to display the Victory Screen when a winner appears.
     * 
     * @param outputData a WinLossResponseModel that contains the name of the 
     *                   winner.
     * @return the same responseModel that was passed in
     */
    WinLossResponseModel showVictoryScreen(WinLossResponseModel outputData);
}
