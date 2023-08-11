package interface_adapters.presenters;

import interface_adapters.PlayCardException;
import interface_adapters.view_models.GameFrameModel;
import interface_adapters.view_models.GameScreenType;
import interface_adapters.view_models.GameplayScreenModel;
import interface_adapters.view_models.TargetSelectScreenModel;
import use_cases.CreatureCardModel;
import use_cases.play_card_use_case.PlayCardOutputBoundary;
import use_cases.play_card_use_case.PlayCardOutputModel;
import use_cases.play_card_use_case.TargetModel;

import java.util.ArrayList;
import java.util.List;

public class PlayCardPresenter implements PlayCardOutputBoundary {

    private GameFrameModel gameFrameModel;
    private GameplayScreenModel gameplayScreenModel;
    private TargetSelectScreenModel targetSelectModel;

    public PlayCardPresenter(GameFrameModel gameFrameModel, GameplayScreenModel gameplayScreenModel, TargetSelectScreenModel targetSelectModel) {
        this.gameFrameModel = gameFrameModel;
        this.gameplayScreenModel = gameplayScreenModel;
        this.targetSelectModel = targetSelectModel;
    }

    /**
     * To be used in the playCard() method in the interactor, for cases when
     * the card cannot be played.
     *
     * @param message the string message to show on the screen.
     */
    @Override
    public PlayCardOutputModel displayErrorMessage(String message) {
        throw new PlayCardException(message);
    }

    /**
     * Display a target request screen, showing the card to be played and the
     * possible targets to choose from. The input from this screen will be sent
     * to the {@code playSingleTargetCard()} method of the interactor.
     *
     * @param requestModel a data model containing the id of the card to be played
     *                     and the list of possible targets.
     */
    @Override
    public PlayCardOutputModel showTargetSelectionScreen(TargetModel requestModel) {
        targetSelectModel.setCardId(requestModel.getCardId());
        targetSelectModel.setCardName(requestModel.getCardName());
        targetSelectModel.setCardDescription(requestModel.getCardDescription());
        targetSelectModel.setTargetIds(requestModel.getTargetIds());
        targetSelectModel.setTargetHP(getTargetHPs(requestModel.getTargetData()));
        targetSelectModel.setTargetATK(getTargetATKs(requestModel.getTargetData()));
        targetSelectModel.setTargetNames(getTargetNames(requestModel.getTargetData()));

        gameFrameModel.setCurrentScreen(GameScreenType.TARGET_SELECTION);
        return null;
    }

    /**
     * Bring back the gameplay screen and add all information in {@code outputData}
     * into the view.
     *
     * @param outputData a PlayCardOutputModel containing all values that reflect
     *                   the changes in the gameState.
     * @return the same as the input parameter, for consistency with the interactor.
     */
    @Override
    public PlayCardOutputModel updateGameScreen(PlayCardOutputModel outputData) {
        if (gameplayScreenModel.getCurrentPlayer().equals(gameplayScreenModel.getPlayer1().getPlayerName())) {
            gameplayScreenModel.getPlayer1().setHandCardIds(outputData.getPlayHandIds());
            gameplayScreenModel.getPlayer1().setHandCardNames(outputData.getPlayHandNames());
            gameplayScreenModel.getPlayer1().setHandCardDescriptions(outputData.getPlayHandDescriptions());
        } else {
            gameplayScreenModel.getPlayer2().setHandCardIds(outputData.getPlayHandIds());
            gameplayScreenModel.getPlayer2().setHandCardNames(outputData.getPlayHandNames());
            gameplayScreenModel.getPlayer2().setHandCardDescriptions(outputData.getPlayHandDescriptions());
        }
        gameplayScreenModel.getPlayer1().setCreatureIds(outputData.getCreatureIds1());
        gameplayScreenModel.getPlayer2().setCreatureIds(outputData.getCreatureIds2());
        gameplayScreenModel.getPlayer1().setCreatureHPs(outputData.getHitPoints1());
        gameplayScreenModel.getPlayer2().setCreatureHPs(outputData.getHitPoints2());
        gameplayScreenModel.getPlayer1().setCreatureAttacks(outputData.getAttacks1());
        gameplayScreenModel.getPlayer2().setCreatureAttacks(outputData.getAttacks2());
        gameplayScreenModel.getPlayer1().setStructureName(outputData.getStructure1());
        gameplayScreenModel.getPlayer2().setStructureName(outputData.getStructure2());

        gameFrameModel.setCurrentScreen(GameScreenType.GAMEPLAY);
        return null;
    }

    private List<Integer> getTargetHPs(List<CreatureCardModel> targetData) {
        List<Integer> targetHPs = new ArrayList<>();
        for (CreatureCardModel target : targetData) {
            if (target != null) {
                targetHPs.add(target.getHitPoints());
            } else {
                targetHPs.add(0);
            }
        }
        return targetHPs;
    }

    private List<Integer> getTargetATKs(List<CreatureCardModel> targetData) {
        List<Integer> targetATKs = new ArrayList<>();
        for (CreatureCardModel target : targetData) {
            if (target != null) {
                targetATKs.add(target.getAttackDamage());
            } else {
                targetATKs.add(0);
            }
        }
        return targetATKs;
    }

    private List<String> getTargetNames(List<CreatureCardModel> targetData) {
        List<String> targetNames = new ArrayList<>();
        for (CreatureCardModel target : targetData) {
            if (target != null) {
                targetNames.add(target.getName());
            } else {
                targetNames.add("Defeat");
            }
        }
        return targetNames;
    }
}
