package entities;

/**
 * The GameState represents everything going on in a game. It is the central 
 * piece among all the game objects. It holds two Player instances, and each
 * of the Player instances hold their own data. The GameState also manages
 * the turn-switching and keeps track of the current player and the opposing 
 * player.
 */
public class GameState {

    private final Player[] players = new Player[2];
    private int currentPlayerIndex = 0;

    public void setPlayers(Player player1, Player player2) {
        players[0] = player1;
        players[1] = player2;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public Player getCurrentPlayer() {
        return players[currentPlayerIndex];
    }

    public Player getOpposingPlayer() {
        return players[currentPlayerIndex ^ 1];
    }

    /**
     * Used for giving the turn to the other player.
     */
    public void switchPlayer() {
        currentPlayerIndex ^= 1;
    }

    /**
     * Used when the second player is chosen to go first.
     * (This method is currently not used. The player order is not being
     * randomized at the moment)
     */
    public void flipPlayOrder() {
        Player temp = players[0];
        players[0] = players[1];
        players[1] = temp;
    }

    /**
     * Clears the current GameState, to be used when the game ends and needs a
     * restart.
     */
    public void reset() {
        currentPlayerIndex = 0;
        players[0] = null;
        players[1] = null;
    }
}
