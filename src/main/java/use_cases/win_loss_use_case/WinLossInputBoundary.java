package use_cases.win_loss_use_case;

/**
 * WinLossInputBoundary provides the interface for checking if there is a winner at the end of a turn.
 */
public interface WinLossInputBoundary {
    /**
     * Checks to see if there is a winner, that is a winner that still has creatures and their opponents
     * have no creatures left
     * @return
     */
    WinLossResponseModel detectWinLoss();
}
