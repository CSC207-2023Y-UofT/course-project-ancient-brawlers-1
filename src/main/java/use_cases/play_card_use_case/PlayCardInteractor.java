package use_cases.play_card_use_case;

import entities.GameState;
import entities.Player;

public class PlayCardInteractor implements PlayCardInputBoundary {

    final GameState gameState;
    final PlayCardOutputBoundary playCardPresenter;

    public PlayCardInteractor(GameState gameState, PlayCardOutputBoundary playCardPresenter) {
        this.gameState = gameState;
        this.playCardPresenter = playCardPresenter;
    }

    /**
     * Fetch the card object from the player and decide if it needs a target
     * or if it can proceed spontaneously.
     * If it does not need target selection, then targets can be located by using
     * information in the gameState, so this method calls {@code playCard()}.
     * If it does need target, a second input from the player is expected. It will
     * signal the presenter with a TargetModel.
     *
     * @param cardId the id of the card, given from the controller's layer.
     */
    @Override
    public void processCard(int cardId) {
        Player player = gameState.getCurrentPlayer();
    }

    /**
     * Spend the card that the player chose to play, and trigger its effects
     * on the targets from the {@code inputData}.
     * Target selection should be complete when this method is called, so the
     * TargetModel contains not the possible targets but the chosen targets.
     *
     * @param inputData a PlayCardInputModel containing the id of card to be
     *                  played and the list of targets.
     * @return a PlayCardOutputModel, containing all possible changed stats in
     * the gameState.
     */
    @Override
    public PlayCardOutputModel playCard(TargetModel inputData) {
        return null;
    }
}
