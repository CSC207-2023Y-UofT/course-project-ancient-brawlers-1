package use_cases.game_preparation_use_case;

import java.util.List;

public interface CardDataGateway {

    ActionCardModel getActionCardByName(String name);

    CreatureCardModel getCreatureCardByName(String name);

    StructureCardModel getStructureCardByName(String name);

    List<String> getPlayerDeckData(String playerTag);
}
