package use_cases.game_save_use_case;

import use_cases.CreatureCardModel;
import use_cases.HandCardModel;

import java.util.List;

/**
 * This data class contains everything needed to send out to the presenter, which
 * will then be put onto the game screen.
 */
public class GameSaveOutputModel {

    private String playerName1;
    private String playerName2;
    private List<CreatureCardModel> creatures1;
    private List<CreatureCardModel> creatures2;
    private List<HandCardModel> hand1;
    private List<HandCardModel> hand2;

    public GameSaveOutputModel(String playerName1, String playerName2,
                               List<CreatureCardModel> creatures1, List<CreatureCardModel> creatures2,
                               List<HandCardModel> hand1, List<HandCardModel> hand2) {
        this.playerName1 = playerName1;
        this.playerName2 = playerName2;
        this.creatures1 = creatures1;
        this.creatures2 = creatures2;
        this.hand1 = hand1;
        this.hand2 = hand2;
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

    public List<CreatureCardModel> getCreatures1() {
        return creatures1;
    }

    public void setCreatures1(List<CreatureCardModel> creatures1) {
        this.creatures1 = creatures1;
    }

    public List<CreatureCardModel> getCreatures2() {
        return creatures2;
    }

    public void setCreatures2(List<CreatureCardModel> creatures2) {
        this.creatures2 = creatures2;
    }

    public List<HandCardModel> getHand1() {
        return hand1;
    }

    public void setHand1(List<HandCardModel> hand1) {
        this.hand1 = hand1;
    }

    public List<HandCardModel> getHand2() {
        return hand2;
    }

    public void setHand2(List<HandCardModel> hand2) {
        this.hand2 = hand2;
    }
}
