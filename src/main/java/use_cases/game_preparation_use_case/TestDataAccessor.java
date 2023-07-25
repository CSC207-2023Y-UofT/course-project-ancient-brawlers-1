package use_cases.game_preparation_use_case;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TestDataAccessor is only for testing the GamePrepInteractor. It provides a
 * mock version of an actual Data Access class. Whereas the actual class needs
 * to have the I/O functionalities, this TestDataAccessor predefines some data.
 * These mock data are used in the unit tests for the GamePrepInteractor, so that
 * we can test the expected behaviours of the interactor.
 */
public class TestDataAccessor implements CardDataGateway {

    private final Map<String, ActionCardModel> actionMap = new HashMap<>();
    private final Map<String, StructureCardModel> structureMap = new HashMap<>();
    private final Map<String, CreatureCardModel> creatureMap = new HashMap<>();
    private final List<String> playerDeck1 = new ArrayList<>();
    private final List<String> playerDeck2 = new ArrayList<>();

    /**
     * Constructor loads the data maps and lists in this class with some
     * predetermined test data.
     * The mock data are:
     * - 2 distinct Action cards
     * - 2 distinct Structure cards
     * - 4 distinct Creature cards
     * Both player decks contain:
     * - 2 copies of the first Action card
     * - 1 copy of the first Structure card
     */
    public TestDataAccessor() {
        List<CardEffectModel> effectModels = List.of(new CardEffectModel("HealEffect", 5));
        ActionCardModel action1 = new ActionCardModel("A1", "D1", "ANY", effectModels);
        ActionCardModel action2 = new ActionCardModel("A2", "D2", "ALL", effectModels);
        StructureCardModel structure1 = new StructureCardModel("S1", "D3", "ALL", effectModels, "TURN_END");
        StructureCardModel structure2 = new StructureCardModel("S2", "D4", "FRIENDLY", effectModels, "TURN_START");
        CreatureCardModel creature1 = new CreatureCardModel("C1", 1, 1, 1, 1);
        CreatureCardModel creature2 = new CreatureCardModel("C2", 2, 2, 2, 2);
        CreatureCardModel creature3 = new CreatureCardModel("C3", 3, 3, 3, 3);
        CreatureCardModel creature4 = new CreatureCardModel("C4", 4, 4, 4, 4);

        actionMap.put(action1.getName(), action1);
        actionMap.put(action2.getName(), action2);
        structureMap.put(structure1.getName(), structure1);
        structureMap.put(structure2.getName(), structure2);
        creatureMap.put(creature1.getName(), creature1);
        creatureMap.put(creature2.getName(), creature2);
        creatureMap.put(creature3.getName(), creature3);
        creatureMap.put(creature4.getName(), creature4);
        playerDeck1.addAll(List.of("A1", "A1", "S1"));
        playerDeck2.addAll(List.of("A1", "A1", "S1"));
    }

    /**
     * Gets the Action card data model based on the card name provided.
     *
     * @param name the String representing the name of a card.
     * @return an ActionCardModel corresponding to the card name.
     */
    @Override
    public ActionCardModel getActionCardByName(String name) {
        return actionMap.get(name);
    }

    /**
     * Gets the Creature card data model based on the card name provided.
     *
     * @param name the String representing the name of a card.
     * @return an CreatureCardModel corresponding to the card name.
     */
    @Override
    public CreatureCardModel getCreatureCardByName(String name) {
        return creatureMap.get(name);
    }

    /**
     * Gets the Structure card data model based on the card name provided.
     *
     * @param name the String representing the name of a card.
     * @return an StructureCardModel corresponding to the card name.
     */
    @Override
    public StructureCardModel getStructureCardByName(String name) {
        return structureMap.get(name);
    }

    /**
     * Gets a list of card names, which are all the cards in the deck of one
     * player (there are only two players).
     * The card names will uniquely map to actual card data.
     *
     * @return the list of card names as strings.
     */
    @Override
    public List<String> getPlayerOneDeckData() {
        return playerDeck1;
    }

    /**
     * Same as {@code getPlayerOneDeckData()} expect the list returned from this
     * method is for the second of the two players.
     *
     * @return the list of card names as strings.
     */
    @Override
    public List<String> getPlayerTwoDeckData() {
        return playerDeck2;
    }
}
