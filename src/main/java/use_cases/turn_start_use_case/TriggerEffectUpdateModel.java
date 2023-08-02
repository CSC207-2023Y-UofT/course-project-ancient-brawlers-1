package use_cases.turn_start_use_case;

import java.util.List;

public class TriggerEffectUpdateModel {

    private List<Integer> finalHandIds;
    private List<String> finalHandNames;
    private CreatureStatsUpdateModel allCreatureStats;

    public TriggerEffectUpdateModel(List<Integer> finalHandIds, List<String> finalHandNames, CreatureStatsUpdateModel allCreatureStats) {
        this.finalHandIds = finalHandIds;
        this.finalHandNames = finalHandNames;
        this.allCreatureStats = allCreatureStats;
    }

    public List<Integer> getFinalHandIds() {
        return finalHandIds;
    }

    public void setFinalHandIds(List<Integer> finalHandIds) {
        this.finalHandIds = finalHandIds;
    }

    public List<String> getFinalHandNames() {
        return finalHandNames;
    }

    public void setFinalHandNames(List<String> finalHandNames) {
        this.finalHandNames = finalHandNames;
    }

    public CreatureStatsUpdateModel getAllCreatureStats() {
        return allCreatureStats;
    }

    public void setAllCreatureStats(CreatureStatsUpdateModel allCreatureStats) {
        this.allCreatureStats = allCreatureStats;
    }
}
