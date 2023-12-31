package interface_adapters.view_models;

import java.util.ArrayList;
import java.util.List;

/**
 * The data class shared by the Frameworks layer and Interface layer.
 * The presenters put strings and integers into this data model, and the screen
 * classes pick up data from here. This ensures the View is dependent on the
 * interfaces and not the reverse.
 * The other view model classes behave similar to this as well.
 */
public class GameFrameModel {

    private GameScreenType currentScreen;
    private List<FrameUpdateListener> listeners = new ArrayList<>();

    public void addListener(FrameUpdateListener listener) {
        listeners.add(listener);
    }

    public GameScreenType getCurrentScreen() {
        return currentScreen;
    }

    /**
     * When a screen switch action takes place, notify the observers to make
     * updates accordingly.
     */
    public void setCurrentScreen(GameScreenType currentScreen) {
        this.currentScreen = currentScreen;
        for (FrameUpdateListener listener : listeners) {
            listener.onFrameUpdate();
        }
    }

    public void refresh() {
        for (FrameUpdateListener listener : listeners) {
            listener.onFrameUpdate();
        }
    }
}
