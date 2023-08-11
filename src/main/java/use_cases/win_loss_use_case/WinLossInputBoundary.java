package use_cases.win_loss_use_case;

/**
 * WinLossInputBoundary provides the interface for checking if there is a winner at the end of a turn.
 */
public interface WinLossInputBoundary {
    /**
     * Checks if a player has run out of alive creatures. The player who still 
     * has creatures is the winner.
     */
    WinLossResponseModel detectWinLoss();
}
