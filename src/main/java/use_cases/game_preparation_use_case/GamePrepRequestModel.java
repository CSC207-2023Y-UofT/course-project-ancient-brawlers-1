package use_cases.game_preparation_use_case;

public class GamePrepRequestModel {

    private String playerName1;
    private String playerName2;
    private int[] creatureIds1;
    private int[] creatureIds2;

    public GamePrepRequestModel(String playerName1, String playerName2,
                                int[] creatureIds1, int[] creatureIds2) {
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

    public int[] getCreatureIds1() {
        return creatureIds1;
    }

    public void setCreatureIds1(int[] creatureIds1) {
        this.creatureIds1 = creatureIds1;
    }

    public int[] getCreatureIds2() {
        return creatureIds2;
    }

    public void setCreatureIds2(int[] creatureIds2) {
        this.creatureIds2 = creatureIds2;
    }
}
