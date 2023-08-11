package interface_adapters.presenters;

import interface_adapters.AttackException;
import interface_adapters.view_models.*;
import use_cases.CreatureCardModel;
import use_cases.attack_use_case.AttackOutputBoundary;
import use_cases.attack_use_case.AttackResponseModel;
import use_cases.attack_use_case.FinishAttackResponseModel;

import java.util.ArrayList;

public class AttackPresenter implements AttackOutputBoundary {

    private final GameFrameModel gameFrameModel;
    private final GameplayScreenModel gameplayScreenModel;
    private final DefendScreenModel defendScreenModel;

    public AttackPresenter(GameFrameModel gameFrameModel, GameplayScreenModel gameplayScreenModel, DefendScreenModel defendScreenModel) {
        this.gameFrameModel = gameFrameModel;
        this.gameplayScreenModel = gameplayScreenModel;
        this.defendScreenModel = defendScreenModel;
    }

    /**
     * Sends instruction for displaying the defend input screen that shows the 
     * attacker, the target, and the possible defenders giving the opponent a 
     * choice to defend or pass.
     * 
     * @param responseModel contains the information of the attacker, the target, 
     *                      and the possible defenders.
     */
    @Override
    public void showDefendInputScreen(AttackResponseModel responseModel) {
        defendScreenModel.setAttackerName(responseModel.getAttackerData().getName());
        defendScreenModel.setTargetName(responseModel.getTargetData().getName());
        defendScreenModel.setAttackerId(responseModel.getAttackerId());
        defendScreenModel.setTargetId(responseModel.getTargetId());
        defendScreenModel.setAttackerHP(responseModel.getAttackerData().getHitPoints());
        defendScreenModel.setAttackerATK(responseModel.getAttackerData().getAttackDamage());
        defendScreenModel.setTargetHP(responseModel.getTargetData().getHitPoints());
        defendScreenModel.setTargetATK(responseModel.getTargetData().getAttackDamage());

        defendScreenModel.setDefenderIds(responseModel.getDefenderIds());
        defendScreenModel.setDefenderNames(new ArrayList<>());
        defendScreenModel.setDefenderHP(new ArrayList<>());
        defendScreenModel.setDefenderATK(new ArrayList<>());

        for (CreatureCardModel defenderData : responseModel.getDefenderData()) {
            defendScreenModel.getDefenderNames().add(defenderData.getName());
            defendScreenModel.getDefenderHP().add(defenderData.getHitPoints());
            defendScreenModel.getDefenderATK().add(defenderData.getAttackDamage());
        }

        gameFrameModel.setCurrentScreen(GameScreenType.DEFEND);
    }

    /**
     * Sends instruction to exit the Defend Screen and back to GamePlayScreen.
     * 
     * @param responseModel the data model containing the creatures hit-points, 
     *                      ids, both players' hand of cards and descriptions.
     */
    @Override
    public FinishAttackResponseModel exitDefendInputScreen(FinishAttackResponseModel responseModel) {
        PlayerDataModel player1 = gameplayScreenModel.getPlayer1();
        player1.setCreatureHPs(responseModel.getHitPoints1());
        player1.setHandCardIds(responseModel.getHandCardsIds1());
        player1.setHandCardDescriptions(responseModel.getHandCardsDescription1());

        PlayerDataModel player2 = gameplayScreenModel.getPlayer2();
        player2.setCreatureHPs(responseModel.getHitPoints2());
        player2.setHandCardIds(responseModel.getHandCardsIds2());
        player2.setHandCardDescriptions(responseModel.getHandCardsDescription2());

        gameFrameModel.setCurrentScreen(GameScreenType.GAMEPLAY);
        return responseModel;
    }
    /**
     * To be used in the initiateAttack() method in the interactor, for cases when a player cannot attack.
     * A player is unable to attack if they do not have enough essence or if  they select their own creatures
     *
     * @param message the string message to show on the screen.
     */
    @Override
    public void displayFailMessage(String message) {
        throw new AttackException(message);
    }
}
