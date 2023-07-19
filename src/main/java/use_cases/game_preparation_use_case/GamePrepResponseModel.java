package use_cases.game_preparation_use_case;

public class GamePrepResponseModel {

    private String playerName1;
    private String playerName2;
    private int[] creatureIds1;
    private int[] creatureIds2;
    private int[] hitPoints1;
    private int[] hitPoints2;
    private int[] attacks1;
    private int[] attacks2;
    private int[] attackCosts1;
    private int[] attackCosts2;
    private int[] defendCosts1;
    private int[] defendCosts2;

    public GamePrepResponseModel(String playerName1, String playerName2,
                                 int[] creatureIds1, int[] creatureIds2,
                                 int[] hitPoints1, int[] hitPoints2,
                                 int[] attacks1, int[] attacks2,
                                 int[] attackCosts1, int[] attackCosts2,
                                 int[] defendCosts1, int[] defendCosts2) {
        this.playerName1 = playerName1;
        this.playerName2 = playerName2;
        this.creatureIds1 = creatureIds1;
        this.creatureIds2 = creatureIds2;
        this.hitPoints1 = hitPoints1;
        this.hitPoints2 = hitPoints2;
        this.attacks1 = attacks1;
        this.attacks2 = attacks2;
        this.attackCosts1 = attackCosts1;
        this.attackCosts2 = attackCosts2;
        this.defendCosts1 = defendCosts1;
        this.defendCosts2 = defendCosts2;
    }
}
