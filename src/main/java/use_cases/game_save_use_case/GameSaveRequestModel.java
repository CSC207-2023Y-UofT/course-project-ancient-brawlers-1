package use_cases.game_save_use_case;

/**
 * The GameSaveRequestModel just contains the file path to save or load a game.
 */
public class GameSaveRequestModel {

    private String filePath;
    private String saveName;

    public GameSaveRequestModel(String filePath, String saveName) {
        this.filePath = filePath;
        this.saveName = saveName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getSaveName() {
        return saveName;
    }

    public void setSaveName(String saveName) {
        this.saveName = saveName;
    }
}
