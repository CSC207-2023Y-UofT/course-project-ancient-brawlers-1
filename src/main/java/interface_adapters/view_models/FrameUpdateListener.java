package interface_adapters.view_models;

/**
 * FrameUpdateListener defines the interface for an Observer Pattern being used
 * to relate the game's application window and the model that describes what
 * that window should do.
 * When the model has been updates by the relevant presenters, it will notify
 * the game window to update according to the changes in the model.
 */
public interface FrameUpdateListener {

    void onFrameUpdate();
}
