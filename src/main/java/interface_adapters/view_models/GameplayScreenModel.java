package interface_adapters.view_models;

public class GameplayScreenModel {

    private PlayerDataModel player1;
    private PlayerDataModel player2;
    private String gameMessage;

    public PlayerDataModel getPlayer1() {
        return player1;
    }

    public void setPlayer1(PlayerDataModel player1) {
        this.player1 = player1;
    }

    public PlayerDataModel getPlayer2() {
        return player2;
    }

    public void setPlayer2(PlayerDataModel player2) {
        this.player2 = player2;
    }

    public String getGameMessage() {
        return gameMessage;
    }

    public void setGameMessage(String gameMessage) {
        this.gameMessage = gameMessage;
    }
}
