package use_cases.turn_start_use_case;

import java.util.List;

public class TriggerEffectUpdateModel {

    private List<Integer> finalHandIds;
    private CreatureStatsUpdateModel allCreatureStats;

    public TriggerEffectUpdateModel(List<Integer> finalHandIds, CreatureStatsUpdateModel allCreatureStats) {
        this.finalHandIds = finalHandIds;
        this.allCreatureStats = allCreatureStats;
    }

    public List<Integer> getFinalHandIds() {
        return finalHandIds;
    }

    public void setFinalHandIds(List<Integer> finalHandIds) {
        this.finalHandIds = finalHandIds;
    }

    public CreatureStatsUpdateModel getAllCreatureStats() {
        return allCreatureStats;
    }

    public void setAllCreatureStats(CreatureStatsUpdateModel allCreatureStats) {
        this.allCreatureStats = allCreatureStats;
    }
}
