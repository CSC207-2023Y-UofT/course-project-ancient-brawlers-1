package entities;

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

    public void switchPlayer() {
        currentPlayerIndex ^= 1;
    }

    public void flipPlayOrder() {
        Player temp = players[0];
        players[0] = players[1];
        players[1] = temp;
    }
}
