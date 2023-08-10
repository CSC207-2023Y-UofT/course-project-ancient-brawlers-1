package interface_adapters.presenters;

import interface_adapters.view_models.GameFrameModel;
import interface_adapters.view_models.GameScreenType;

/**
 * This presenter does not connect to any internal components (use cases). Its
 * job is to switch the screen back to main menu when the player is interacting
 * with the pause game button.
 * This class can be seen as a component of the more classic MVP pattern.
 */
public class GamePausePresenter {

    private final GameFrameModel gameFrameModel;

    public GamePausePresenter(GameFrameModel gameFrameModel) {
        this.gameFrameModel = gameFrameModel;
    }

    public void pauseGame() {
        gameFrameModel.setCurrentScreen(GameScreenType.PAUSE);
    }

    public void resumeGame() {
        gameFrameModel.setCurrentScreen(GameScreenType.GAMEPLAY);
    }

    public void quitGameWithoutSave() {
        // We don't need to reset the gamestate, because the gamestate is an
        // entity. We would have to go extra miles and create intermediate
        // components for it to maintain the architecture. Instead, we just make
        // sure a new game's creation always cleans the gameState first (that's
        // the job of Game Prep use case).
        gameFrameModel.setCurrentScreen(GameScreenType.MENU);
    }
}
