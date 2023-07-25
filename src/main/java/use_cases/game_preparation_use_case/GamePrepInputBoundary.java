package use_cases.game_preparation_use_case;

/**
 * GamePrepInputBoundary provides the interface for handling the setup of a new
 * game.
 */
public interface GamePrepInputBoundary {

    /**
     * Signal the presenter to display a prompt screen with all the available
     * creature cards that the players can choose for the game, and ask the players
     * to enter their names and select the creatures to play.
     * This method will use the CardDataGateway to fetch all the game data, and
     * it is the CardDataGateway's job to access the data stored outside the
     * program.
     */
    void promptPlayerInfo();

    /**
     * Create the Player instances and the Creature instances based on the names
     * provided in the {@code requestModel}, then signal the presenter to display
     * the game screen.
     * This method will send a GamePrepResponseModel containing all the values
     * that need to be shown on the game screen.
     *
     * @param requestModel the GamePrepRequestModel containing the two players'
     *                     names and their corresponding lists of creature names.
     * @return a GamePrepResponseModel containing all necessary stats to be shown
     * on screen (i.e. creature attack and hit-points, player names, etc.)
     */
    GamePrepResponseModel processPlayerInfo(GamePrepRequestModel requestModel);
}
