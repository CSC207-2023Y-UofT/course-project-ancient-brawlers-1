package use_cases.game_preparation_use_case;

import entities.GameEvent;
import entities.GameState;
import entities.Player;
import entities.PlayerFactory;
import entities.cardEffects.CardEffect;
import entities.cardEffects.CardEffectFactory;
import entities.cards.*;
import entities.decks.DeckFactory;
import entities.decks.EssenceDeck;
import entities.decks.PlayerDeck;

import java.util.ArrayList;
import java.util.List;

public class GamePrepInteractor implements GamePrepInputBoundary {

    final GameState gameState;
    final CardFactory cardFactory;
    final DeckFactory deckFactory;
    final PlayerFactory playerFactory;
    final CardDataGateway dataAccessor;
    final GamePrepOutputBoundary gamePrepPresenter;

    public GamePrepInteractor(GameState gameState, CardFactory cardFactory,
                              DeckFactory deckFactory, PlayerFactory playerFactory,
                              CardDataGateway dataAccessor, GamePrepOutputBoundary gamePrepPresenter) {
        this.gameState = gameState;
        this.cardFactory = cardFactory;
        this.deckFactory = deckFactory;
        this.playerFactory = playerFactory;
        this.dataAccessor = dataAccessor;
        this.gamePrepPresenter = gamePrepPresenter;
    }

    /**
     * Signal the presenter to display a prompt screen with all the available
     * creature cards that the players can choose for the game, and ask the players
     * to enter their names and select the creatures to play.
     * This method will use the CardDataGateway to fetch all the game data, and
     * it is the CardDataGateway's job to access the data stored outside the
     * program.
     */
    @Override
    public void promptPlayerInfo() {
        gamePrepPresenter.showGamePreparationScreen(dataAccessor.getExistingCreatures());
    }

    /**
     * Create the Player instances and the Creature instances based on the names
     * provided in the {@code requestModel}, then signal the presenter to display
     * the game screen.
     * This method will send a GamePrepResponseModel containing all the values
     * that need to be shown on the game screen.
     *
     * @param requestModel the GamePrepRequestModel containing the two players'
     *                     names and their corresponding lists of creature names.
     * @return a GamePrepResponseModel containing all necessary stats to be shown
     * on screen (i.e. creature attack and hit-points, player names, etc.)
     */
    @Override
    public GamePrepResponseModel processPlayerInfo(GamePrepRequestModel requestModel) {
        if (requestModel.getPlayerName1().isEmpty() || requestModel.getPlayerName2().isEmpty()) {
            return gamePrepPresenter.displayErrorMessage("Please enter a name for each player.");
        } else if (requestModel.getCreatureNames1().size() != 3 || requestModel.getCreatureNames2().size() != 3) {
            return gamePrepPresenter.displayErrorMessage("Please select only 3 creatures.");
        }

        List<CreatureCard> creatures1 = getCreatureList(requestModel.getCreatureNames1());
        List<CreatureCard> creatures2 = getCreatureList(requestModel.getCreatureNames2());

        PlayerDeck playerDeck1 = buildPlayerDeck(dataAccessor.getPlayerOneDeckData());
        PlayerDeck playerDeck2 = buildPlayerDeck(dataAccessor.getPlayerTwoDeckData());
        playerDeck1.shuffleDeck();
        playerDeck2.shuffleDeck();
        EssenceDeck essenceDeck1 = (EssenceDeck) deckFactory.createEssenceDeck(cardFactory);
        EssenceDeck essenceDeck2 = (EssenceDeck) deckFactory.createEssenceDeck(cardFactory);
        Player player1 = playerFactory.createPlayer(requestModel.getPlayerName1(), creatures1, playerDeck1, essenceDeck1);
        Player player2 = playerFactory.createPlayer(requestModel.getPlayerName2(), creatures2, playerDeck2, essenceDeck2);

        gameState.setPlayers(player1, player2);

        GamePrepResponseModel outputData = getResponseModel();

        return gamePrepPresenter.showGameplayScreen(outputData);
    }

    /**
     * Helper method for creating the CreatureCard instances, using the
     * CardDataGateway.
     *
     * @param names the string names of the creatures. These will uniquely map
     *              to creature data models in the CardDataGateway.
     * @return the list of CreatureCard instances made from the input names.
     */
    private List<CreatureCard> getCreatureList(List<String> names) {
        List<CreatureCard> creatures = new ArrayList<>();
        for (String name : names) {
            CreatureCardModel model = dataAccessor.getCreatureCardByName(name);
            Card creature = cardFactory.createCreatureCard(name, model.getHitPoints(),
                    model.getAttackDamage(), model.getAttackCost(), model.getDefendCost());
            creatures.add((CreatureCard) creature);
        }
        return creatures;
    }

    /**
     * Helper method for creating the player deck for a single player.
     *
     * @param names the string names of each card in the deck. These will map
     *              uniquely to the card data models.
     * @return the PlayerDeck instance containing the cards to be created using
     * the input names.
     */
    private PlayerDeck buildPlayerDeck(List<String> names) {
        List<Card> cards = new ArrayList<>();
        for (String name : names) {
            Card card;
            // Card must exist as either an action or a structure (a precondition)
            if (dataAccessor.getActionCardByName(name) != null) {
                ActionCardModel model = dataAccessor.getActionCardByName(name);
                List<CardEffect> effects = getEffectList(model.getEffects());
                PlayableCardData cardData = new PlayableCardData(model.getDescription(),
                        Enum.valueOf(TargetType.class, model.getTargetType()), effects);
                card = cardFactory.createActionCard(name, cardData);
            } else {
                StructureCardModel model = dataAccessor.getStructureCardByName(name);
                List<CardEffect> effects = getEffectList(model.getEffects());
                PlayableCardData cardData = new PlayableCardData(model.getDescription(),
                        Enum.valueOf(TargetType.class, model.getTargetType()), effects);
                card = cardFactory.createStructureCard(name, cardData,
                        Enum.valueOf(GameEvent.class, model.getTriggerEvent()));
            }
            cards.add(card);
        }
        return (PlayerDeck) deckFactory.createPlayerDeck(cards);
    }


    /**
     * Turn the list of CardEffectModel into a list of actual CardEffects.
     *
     * @param effectModels CardEffectModel containing the intermediate data between
     *                     the database and the CardEffectFactory.
     * @return a list of items of type CardEffect.
     */
    private List<CardEffect> getEffectList(List<CardEffectModel> effectModels) {
        List<CardEffect> effects = new ArrayList<>();
        for (CardEffectModel m : effectModels) {
            CardEffect effect;
            if (m.getSpecialKeyword() == null && m.getEffectValue() == 0) {
                effect = CardEffectFactory.createEffect(m.getType());
            } else if (m.getSpecialKeyword() == null && m.getEffectValue() != 0) {
                effect = CardEffectFactory.createEffect(m.getType(), m.getEffectValue());
            } else {
                effect = CardEffectFactory.createEffect(m.getType(), m.getEffectValue(), m.getSpecialKeyword());
            }
            effects.add(effect);
        }
        return effects;
    }

    /**
     * Prepare the response model for the method processPlayerInfo().
     *
     * @return a GamePrepResponseModel containing all the stats needed for the
     * gameplay screen.
     */
    private GamePrepResponseModel getResponseModel() {
        Player player1 = gameState.getCurrentPlayer();
        Player player2 = gameState.getOpposingPlayer();

        List<Integer> creatureIds1 = new ArrayList<>();
        List<Integer> creatureIds2 = new ArrayList<>();
        List<String> creatureNames1 = new ArrayList<>();
        List<String> creatureNames2 = new ArrayList<>();
        List<Integer> hitPoints1 = new ArrayList<>();
        List<Integer> hitPoints2 = new ArrayList<>();
        List<Integer> attacks1 = new ArrayList<>();
        List<Integer> attacks2 = new ArrayList<>();

        for (CreatureCard c : player1.getCreatures()) {
            creatureIds1.add(c.getId());
            creatureNames1.add(c.getName());
            hitPoints1.add(c.getTotalHitPoints());
            attacks1.add(c.getTotalAttackDamage());
        }

        for (CreatureCard c : player2.getCreatures()) {
            creatureIds2.add(c.getId());
            creatureNames2.add(c.getName());
            hitPoints2.add(c.getTotalHitPoints());
            attacks2.add(c.getTotalAttackDamage());
        }

        return new GamePrepResponseModel(player1.getName(), player2.getName(),
                creatureIds1, creatureIds2, creatureNames1, creatureNames2,
                hitPoints1, hitPoints2, attacks1, attacks2);
    }
}
