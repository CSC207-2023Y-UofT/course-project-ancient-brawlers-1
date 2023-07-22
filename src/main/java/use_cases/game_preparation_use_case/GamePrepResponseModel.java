package use_cases.game_preparation_use_case;

public class GamePrepResponseModel {

    private String playerName1;
    private String playerName2;
    private CreatureDataModel creatureData1;
    private CreatureDataModel creatureData2;

    public GamePrepResponseModel(String playerName1, String playerName2,
                                 CreatureDataModel creatureData1, CreatureDataModel creatureData2) {
        this.playerName1 = playerName1;
        this.playerName2 = playerName2;
        this.creatureData1 = creatureData1;
        this.creatureData2 = creatureData2;
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

    public CreatureDataModel getCreatureData1() {
        return creatureData1;
    }

    public void setCreatureData1(CreatureDataModel creatureData1) {
        this.creatureData1 = creatureData1;
    }

    public CreatureDataModel getCreatureData2() {
        return creatureData2;
    }

    public void setCreatureData2(CreatureDataModel creatureData2) {
        this.creatureData2 = creatureData2;
    }
}
