package use_cases.game_preparation_use_case;

import java.util.List;

public class StructureCardModel implements CardModel {

    private String name;
    private String description;
    private String targetType;
    private List<CardEffectModel> effects;
    private String triggerEvent;

    public StructureCardModel() {
    }

    public StructureCardModel(String name, String description, String targetType,
                              List<CardEffectModel> effects, String triggerEvent) {
        this.name = name;
        this.description = description;
        this.targetType = targetType;
        this.effects = effects;
        this.triggerEvent = triggerEvent;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
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

    public String getTriggerEvent() {
        return triggerEvent;
    }

    public void setTriggerEvent(String triggerEvent) {
        this.triggerEvent = triggerEvent;
    }
}
