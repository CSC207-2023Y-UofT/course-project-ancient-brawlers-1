package use_cases.game_preparation_use_case;

import java.util.List;

public class ActionCardModel {

    private String name;
    private String description;
    private String targetType;
    private List<CardEffectModel> effects;

    public ActionCardModel(String name, String description, String targetType,
                           List<CardEffectModel> effects) {
        this.name = name;
        this.description = description;
        this.targetType = targetType;
        this.effects = effects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public List<CardEffectModel> getEffects() {
        return effects;
    }

    public void setEffects(List<CardEffectModel> effects) {
        this.effects = effects;
    }
}
