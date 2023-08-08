package interface_adapters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import use_cases.game_preparation_use_case.ActionCardModel;
import use_cases.game_preparation_use_case.CardModel;
import use_cases.CreatureCardModel;
import use_cases.game_preparation_use_case.StructureCardModel;
import use_cases.game_preparation_use_case.CardDataGateway;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CardDataMapper implements CardDataGateway {

    private final Map<String, ActionCardModel> actionMap = new LinkedHashMap<>();
    private final Map<String, StructureCardModel> structureMap = new LinkedHashMap<>();
    private final Map<String, CreatureCardModel> creatureMap = new LinkedHashMap<>();
    private final List<String> playerDeck1 = new ArrayList<>();
    private final List<String> playerDeck2 = new ArrayList<>();

    public CardDataMapper(String creaturesPath, String actionsPath, String structuresPath,
                          String deckOnePath, String deckTwoPath) {
        loadCardData(creaturesPath, creatureMap, CreatureCardModel.class);
        loadCardData(actionsPath, actionMap, ActionCardModel.class);
        loadCardData(structuresPath, structureMap, StructureCardModel.class);
        loadDeckData(deckOnePath, playerDeck1);
        loadDeckData(deckTwoPath, playerDeck2);
    }

    private <T extends CardModel> void loadCardData(String filePath, Map<String, T> map, Class<T> classType) {
        try {
            File file = new File(filePath);
            ObjectMapper objectMapper = new ObjectMapper();
            CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, classType);
            List<T> cards = objectMapper.readValue(file, collectionType);
            for (T card : cards) {
                map.put(card.getName(), card);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadDeckData(String filePath, List<String> deck) {
        try {
            File file = new File(filePath);
            ObjectMapper objectMapper = new ObjectMapper();
            CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, String.class);
            List<String> cardNames = objectMapper.readValue(file, collectionType);
            deck.addAll(cardNames);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    /**
     * Return a list of creature names for all the creatures that can be selected
     * for the game.
     *
     * @return a list of strings for all the creature names.
     */
    @Override
    public List<String> getExistingCreatures() {
        return new ArrayList<>(creatureMap.keySet());
    }
}
