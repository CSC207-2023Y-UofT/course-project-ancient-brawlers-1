package use_cases.win_loss_use_case;

import entities.GameState;
import entities.Player;
import entities.cards.CreatureCard;

public class WinLossInteractor implements WinLossInputBoundary {

    final GameState gameState;
    final WinLossOutputBoundary winLossPresenter;

    public WinLossInteractor(GameState gameState, WinLossOutputBoundary winLossPresenter) {
        this.gameState = gameState;
        this.winLossPresenter = winLossPresenter;
    }

    /**
     * Checks if a player has run out of alive creatures. The player who still 
     * has creatures is the winner.
     */
    @Override
    public WinLossResponseModel detectWinLoss() {
        Player player1 = gameState.getCurrentPlayer();
        Player player2 = gameState.getOpposingPlayer();
        int p1Creature = getAliveCreatures(player1);
        int p2Creature = getAliveCreatures(player2);

        WinLossResponseModel outputData;
        if (p1Creature == 0 && p2Creature > 0) {
            outputData = new WinLossResponseModel(player2.getName());
        } else if (p2Creature == 0 && p1Creature > 0) {
            outputData = new WinLossResponseModel(player1.getName());
        } else if (p1Creature == 0 && p2Creature == 0) {
            outputData = new WinLossResponseModel("No one");  // draw :)
        } else {
            outputData = new WinLossResponseModel("");
        }
        return winLossPresenter.showVictoryScreen(outputData);
    }

    /**
     * Return the number of alive creatures that a player has.
     * A creature whose id is not -1 is still active.
     *
     * @param player a Player object
     * @return the number of alive creatures of the player
     */
    private int getAliveCreatures(Player player) {
        int alive = 0;
        for (CreatureCard c : player.getCreatures()) {
            if (c.getId() != -1) {
                alive++;
            }
        }
        return alive;
    }
}
