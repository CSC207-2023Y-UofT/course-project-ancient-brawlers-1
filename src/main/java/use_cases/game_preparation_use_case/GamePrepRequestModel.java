package use_cases.game_preparation_use_case;

import java.util.List;

public class GamePrepRequestModel {

    private String playerName1;
    private String playerName2;
    private List<String> creatureNames1;
    private List<String> creatureNames2;

    public GamePrepRequestModel(String playerName1, String playerName2,
                                List<String> creatureNames1, List<String> creatureNames2) {
        this.playerName1 = playerName1;
        this.playerName2 = playerName2;
        this.creatureNames1 = creatureNames1;
        this.creatureNames2 = creatureNames2;
    }

    public String getPlayerName1() {
        return playerName1;
    }

    public void setPlayerName1(String playerName1) {
        this.playerName1 = playerName1;
    }

    public String getPlayerName2() {
        return playerName2;
    }

    public void setPlayerName2(String playerName2) {
        this.playerName2 = playerName2;
    }

    public List<String> getCreatureNames1() {
        return creatureNames1;
    }

    public void setCreatureNames1(List<String> creatureNames1) {
        this.creatureNames1 = creatureNames1;
    }

    public List<String> getCreatureNames2() {
        return creatureNames2;
    }

    public void setCreatureNames2(List<String> creatureNames2) {
        this.creatureNames2 = creatureNames2;
    }
}
