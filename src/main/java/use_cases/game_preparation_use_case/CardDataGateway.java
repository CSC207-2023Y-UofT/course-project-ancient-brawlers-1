package use_cases.game_preparation_use_case;

import use_cases.CreatureCardModel;

import java.util.List;

/**
 * CardDataGateway provides the Data Access Interface, which defines the methods
 * for getting and saving data to databases. It is meant to be implemented by a
 * class in the Frameworks and Drivers Layer (which means it is technically a
 * component of the Interface Adapters Layer, in-between use cases and outer
 * layers). It is put into this use case because no other use cases need to
 * communicate with the database.
 */
public interface CardDataGateway {

    /**
     * Gets the Action card data model based on the card name provided.
     *
     * @param name the String representing the name of a card.
     * @return an ActionCardModel corresponding to the card name.
     */
    ActionCardModel getActionCardByName(String name);

    /**
     * Gets the Creature card data model based on the card name provided.
     *
     * @param name the String representing the name of a card.
     * @return an CreatureCardModel corresponding to the card name.
     */
    CreatureCardModel getCreatureCardByName(String name);

    /**
     * Gets the Structure card data model based on the card name provided.
     *
     * @param name the String representing the name of a card.
     * @return an StructureCardModel corresponding to the card name.
     */
    StructureCardModel getStructureCardByName(String name);

    /**
     * Gets a list of card names, which are all the cards in the deck of one
     * player (there are only two players).
     * The card names will uniquely map to actual card data.
     *
     * @return the list of card names as strings.
     */
    List<String> getPlayerOneDeckData();

    /**
     * Same as {@code getPlayerOneDeckData()} expect the list returned from this
     * method is for the second of the two players.
     *
     * @return the list of card names as strings.
     */
    List<String> getPlayerTwoDeckData();

    /**
     * Return a list of creature names for all the creatures that can be selected
     * for the game.
     *
     * @return a list of strings for all the creature names.
     */
    List<String> getExistingCreatures();
}
