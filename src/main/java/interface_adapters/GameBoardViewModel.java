package interface_adapters;

public interface GameBoardViewModel {

    void setInitialGameState(String player1, String player2, String[] creatures1, String[] creatures2);

    /**
     * Change the values for the HP and attack of each creature on the game board.
     */
    void UpdateCreatureStats(int[] hitPoints1, int[] hitPoints2, int[] attacks1, int[] attacks2);

    /**
     * Change the cards in the players' hands.
     */
    void UpdatePlayerHands(int[] cardIds, String[] cardNames);

    /**
     * Add or remove structure card shown on the board.
     */
    void UpdateStructures(int structureId1, String structureName1, int structureId2, String structureName2);
}
