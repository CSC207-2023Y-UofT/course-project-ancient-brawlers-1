package interface_adapters.view_models;

/**
 * ScreenUpdateListener provides the interface for an Observer pattern used to
 * relate the GameFrame with the different screen classes. When the GameFrame
 * needs to update, typically it means a screen change is required. So it will
 * notify all screens to update themselves according to their own view models.
 */
public interface ScreenUpdateListener {

    void onScreenUpdate();
}
