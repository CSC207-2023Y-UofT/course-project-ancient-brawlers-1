package use_cases.game_preparation_use_case;

import java.util.List;

public class GamePrepRequestModel {

    private String playerName1;
    private String playerName2;
    private List<Integer> creatureIds1;
    private List<Integer> creatureIds2;

    public GamePrepRequestModel(String playerName1, String playerName2,
                                List<Integer> creatureIds1, List<Integer> creatureIds2) {
        this.playerName1 = playerName1;
        this.playerName2 = playerName2;
        this.creatureIds1 = creatureIds1;
        this.creatureIds2 = creatureIds2;
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

    public List<Integer> getCreatureIds1() {
        return creatureIds1;
    }

    public void setCreatureIds1(List<Integer> creatureIds1) {
        this.creatureIds1 = creatureIds1;
    }

    public List<Integer> getCreatureIds2() {
        return creatureIds2;
    }

    public void setCreatureIds2(List<Integer> creatureIds2) {
        this.creatureIds2 = creatureIds2;
    }
}
