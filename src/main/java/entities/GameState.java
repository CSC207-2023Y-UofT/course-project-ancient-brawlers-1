package entities;

import entities.decks.EssenceDeck;
import entities.decks.PlayerDeck;

public class GameState {

    private final Player[] players = new Player[2];
    private final PlayerDeck[] playerDecks = new PlayerDeck[2];
    private final EssenceDeck[] essenceDecks = new EssenceDeck[2];
    private int currentPlayerIndex = 0;

    public void setPlayers(Player player1, Player player2) {
        players[0] = player1;
        players[1] = player2;
    }

    public void setPlayerDecks(PlayerDeck deck1, PlayerDeck deck2) {
        playerDecks[0] = deck1;
        playerDecks[1] = deck2;
    }

    public void setEssenceDecks(EssenceDeck deck1, EssenceDeck deck2) {
        essenceDecks[0] = deck1;
        essenceDecks[1] = deck2;
    }

    public PlayerDeck getCurrentPlayerDeck() {
        return playerDecks[currentPlayerIndex];
    }

    public EssenceDeck getCurrentEssenceDeck() {
        return essenceDecks[currentPlayerIndex];
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

        PlayerDeck tempDeck = playerDecks[0];
        playerDecks[0] = playerDecks[1];
        playerDecks[1] = tempDeck;

        EssenceDeck tempDeck2 = essenceDecks[0];
        essenceDecks[0] = essenceDecks[1];
        essenceDecks[1] = tempDeck2;
    }
}
