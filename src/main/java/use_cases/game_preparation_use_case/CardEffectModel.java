package use_cases.game_preparation_use_case;

public class CardEffectModel {

    private String type;
    private int effectValue;
    private String specialKeyword;

    public CardEffectModel() {
    }

    public CardEffectModel(String type) {
        this.type = type;
    }

    public CardEffectModel(String type, int effectValue) {
        this.type = type;
        this.effectValue = effectValue;
    }

    public CardEffectModel(String type, int effectValue, String specialKeyword) {
        this.type = type;
        this.effectValue = effectValue;
        this.specialKeyword = specialKeyword;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getEffectValue() {
        return effectValue;
    }

    public void setEffectValue(int effectValue) {
        this.effectValue = effectValue;
    }

    public String getSpecialKeyword() {
        return specialKeyword;
    }

    public void setSpecialKeyword(String specialKeyword) {
        this.specialKeyword = specialKeyword;
    }
}
