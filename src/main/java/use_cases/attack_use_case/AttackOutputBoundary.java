package use_cases.attack_use_case;

/**
 AttackOutputBoundary provides the interface for the presenter to control the UI of the attacking
 stage of the game. It is responsible for showing the defend screen and exiting the defend screen
 */
public interface AttackOutputBoundary {

    /**
     * Displays a screen to show the current attacker, the current target/defender and the the possible
     * defenders based on how much essence the target opponent has
     * @param responseModel contains the information of the attacker, the defender, and the possible
     *                      defender
     */
    void showDefendInputScreen(AttackResponseModel responseModel);

    /**
     * Exits the defend screen and sends all the updated stats that occurred during the defend screen
     * @param responseModel
     * @return the updated stats for the players hand and their creatures hitpoints
     */
    FinishAttackResponseModel exitDefendInputScreen(FinishAttackResponseModel responseModel);

    /**
     * Displays the fail message based on what type of error it is
     * @param message
     */

    void displayFailMessage(String message);
}
