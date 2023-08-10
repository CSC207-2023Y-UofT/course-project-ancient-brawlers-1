package use_cases.game_start_use_case;

import entities.GameState;
import entities.Player;
import entities.cards.Card;
import entities.cards.Playable;
import entities.decks.PlayerDeck;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * GameStartInteractor is the implementation of GameStartInputBoundary, which
 * handles the logic during the start-of-game phase. It decides which player goes
 * first in the game, and handles the mulligan process for the player currently on
 * their turn. These steps are the first events to happen before the main gameplay
 * phase.
 */
public class GameStartInteractor implements GameStartInputBoundary {

    final GameState gameState;
    final GameStartOutputBoundary gameStartPresenter;

    /**
     * Construct a GameStartInteractor, with the given GameState and GameStartOutputBoundary.
     *
     * @param gameState the GameState that records the progress of the current game.
     *                  It should be shared by all use case interactors.
     * @param gameStartPresenter an implementing class of the output boundary that
     *                           handles the communication to the outer layers of
     *                           the program.
     */
    public GameStartInteractor(GameState gameState, GameStartOutputBoundary gameStartPresenter) {
        this.gameState = gameState;
        this.gameStartPresenter = gameStartPresenter;
    }


    /**
     * Randomly decides the players' turn order of the game and notifies the
     * players.
     * This method has a 50% chance of calling the flipPlayOrder() method in
     * the GameState object that is recording the progress of the current game.
     */
    @Override
    public void decidePlayOrder() {
        Random random = new Random();
        if (random.nextBoolean()) {
            gameState.flipPlayOrder();
        }

        Player player = gameState.getCurrentPlayer();
        gameStartPresenter.displayPlayerOrder(player.getName() + " goes first!", gameState.getCurrentPlayer().getName());
    }

    /**
     * Sets up the mulligan phase for the current player, which shows the player
     * their initial hand of three cards.
     * Mulligan phase: The player can select which cards of the initial hand they
     * want to replace, the selected cards will be shuffled back into the deck at
     * random places, and a new card is drawn for each of the shuffled cards.
     *
     * @return the GameStartResponseModel containing the ids and names of the
     * cards in the initial hand.
     */
    @Override
    public GameStartResponseModel prepareMulligan() {
        Player player = gameState.getCurrentPlayer();
        PlayerDeck deck = player.getPlayerDeck();
        List<Integer> cardIds = new ArrayList<>();
        List<String> cardNames = new ArrayList<>();
        List<String> cardDescs = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            Card card = deck.draw();
            cardIds.add(card.getId());
            cardNames.add(card.getName());
            if (card instanceof Playable) {
                cardDescs.add(((Playable) card).getDescription());
            } else {
                cardDescs.add("Essence");
            }
            player.addCard(card);
        }

        GameStartResponseModel output = new GameStartResponseModel(gameState.getCurrentPlayerIndex(), cardIds, cardNames, cardDescs);
        return gameStartPresenter.showMulliganScreen(output);
    }

    /**
     * Processes the shuffling and redrawing actions of the mulligan phase, and
     * returns the final hand of cards for the current player.
     *
     * @param inputData the GameStartRequestModel containing the ids and names
     *                  of the cards that the player wants to replace.
     * @return the GameStartResponseModel containing the ids and names
     * of the cards finalized in the player's hand.
     */
    @Override
    public GameStartResponseModel processMulligan(GameStartRequestModel inputData) {
        Player player = gameState.getCurrentPlayer();
        PlayerDeck deck = player.getPlayerDeck();
        List<Integer> cardIds = new ArrayList<>();
        List<String> cardNames = new ArrayList<>();
        List<String> cardDescs = new ArrayList<>();

        // Shuffle the cards into the deck.
        for (int id : inputData.getCardIds()) {
            Card shuffleCard = player.playCard(id);
            deck.shuffleInto(shuffleCard);
        }
        // Then, record the remaining cards in the player's hand.
        for (Card card : player.getHand()) {
            cardIds.add(card.getId());
            cardNames.add(card.getName());
            if (card instanceof Playable) {
                cardDescs.add(((Playable) card).getDescription());
            } else {
                cardDescs.add("Essence");
            }
        }
        // Next, redraw the same number of cards.
        for (int i = 0; i < inputData.getCardIds().size(); i++) {
            Card newCard = deck.draw();
            cardIds.add(newCard.getId());
            cardNames.add(newCard.getName());
            if (newCard instanceof Playable) {
                cardDescs.add(((Playable) newCard).getDescription());
            } else {
                cardDescs.add("Essence");
            }
            player.addCard(newCard);
        }

        GameStartResponseModel output;

        // If the player is the second to go, give them a bonus Essence card.
        if (gameState.getCurrentPlayerIndex() == 1) {
            List<Integer> bonusCardIds = new ArrayList<>();
            List<String> bonusCardNames = new ArrayList<>();
            List<String> bonusDescs = new ArrayList<>();
            Card bonusCard = player.getEssenceDeck().draw();
            bonusCardIds.add(bonusCard.getId());
            bonusCardNames.add(bonusCard.getName());
            bonusDescs.add("Essence");
            player.addCard(bonusCard);
            output = new GameStartResponseModel(gameState.getCurrentPlayerIndex(), cardIds, bonusCardIds,
                                                cardNames, bonusCardNames, cardDescs, bonusDescs);
        } else {
            output = new GameStartResponseModel(gameState.getCurrentPlayerIndex(), cardIds, cardNames, cardDescs);
        }
        return gameStartPresenter.exitMulliganScreen(output);
    }
}
