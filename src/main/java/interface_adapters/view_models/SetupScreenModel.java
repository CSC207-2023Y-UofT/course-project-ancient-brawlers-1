package interface_adapters.view_models;

import java.util.List;

/**
 * One of several data model classes, meant to be seen by both the UI components
 * and the presenters.
 * Presenter modifies the data model, and the UI is in charge of the actual display.
 */
public class SetupScreenModel {

    private List<String> creaturesToChoose;

    public List<String> getCreaturesToChoose() {
        return creaturesToChoose;
    }

    public void setCreaturesToChoose(List<String> creaturesToChoose) {
        this.creaturesToChoose = creaturesToChoose;
    }
}
