package use_cases.game_start_use_case;

/**
 * GameStartInputBoundary provides the interface for handling the logic in the
 * start-of-game phase.
 */
public interface GameStartInputBoundary {

    /**
     * Randomly decides the players' turn order of the game and notifies the
     * players.
     * This method has a 50% chance of calling the flipPlayOrder() method in
     * the GameState object that is recording the progress of the current game.
     */
    void decidePlayOrder();

    /**
     * Sets up the mulligan phase for the current player, which shows the player
     * their initial hand of three cards.
     * Mulligan phase: The player can select which cards of the initial hand they
     * want to replace, the selected cards will be shuffled back into the deck at
     * random places, and a new card is drawn for each of the shuffled cards.
     *
     * @return the GameStartResponseModel containing the ids and names of the
     * cards in the initial hand.
     */
    GameStartResponseModel prepareMulligan();

    /**
     * Processes the shuffling and redrawing actions of the mulligan phase, and
     * returns the final hand of cards for the current player.
     *
     * @param inputData the GameStartRequestModel containing the ids and names
     *                  of the cards that the player wants to replace.
     * @return the GameStartResponseModel containing the ids and names
     * of the cards finalized in the player's hand.
     */
    GameStartResponseModel processMulligan(GameStartRequestModel inputData);
}
