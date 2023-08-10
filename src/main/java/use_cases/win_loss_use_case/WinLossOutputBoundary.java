package use_cases.win_loss_use_case;

/**
 * WinLossOutputBoundary provides the interface for the presenter to display who the winner when a destroys all
 * of their opponents creatures
 */
public interface WinLossOutputBoundary {
    /**
     * Displays the victory screen for of the player that is the winner
     * @param outputData a WinLossResponseModel  that contains the name of the player that is the winner
     */
    WinLossResponseModel showVictoryScreen(WinLossResponseModel outputData);
}
