package use_cases.turn_start_use_case;

import entities.GameState;
import entities.Player;
import entities.cards.Card;
import entities.cards.CreatureCard;
import entities.decks.EssenceDeck;
import entities.decks.PlayerDeck;

import java.util.ArrayList;
import java.util.List;


public class TurnStartInteractor implements TurnStartInputBoundary {

    final GameState gameState;

    final TurnStartOutputBoundary turnStartPresenter;

    public TurnStartInteractor(GameState gameState, TurnStartOutputBoundary turnStartPresenter) {
        this.gameState = gameState;
        this.turnStartPresenter = turnStartPresenter;
    }


    @Override
    public TurnStartResponseModel drawCards() {

        //Getting the player deck from the current player
        Player player = gameState.getCurrentPlayer();
        PlayerDeck deck = player.getPlayerDeck();

        //Getting the essence deck from the current player
        EssenceDeck edeck = player.getEssenceDeck();

        //Drawing twice from the player deck then from the essence deck
        for(int i = 0; i < 2; i++){
            player.addCard(deck.draw());
            player.addCard(edeck.draw());

        }

        //Getting a list of all cards in the player hands and returning it
        List<Card> hand = player.getHand();
        List<Integer> handIds = new ArrayList<>();
        for (int i = 0; i < hand.size(); i++){
            handIds.add(hand.get(i).getId());
        }

        TurnStartResponseModel output = new TurnStartResponseModel(handIds);
        return turnStartPresenter.updateScreen(output);
    }

    @Override
    public TurnStartResponseModel clearTemporaryEffects() {

        Player player = gameState.getCurrentPlayer();
        List<CreatureCard> creatures = player.getCreatures();
        List<Integer> creatureIds = new ArrayList<>();
        List<Integer> hitPoints = new ArrayList<>();
        List<Integer> attacks = new ArrayList<>();
        for (int i = 0; i < creatures.size(); i++){
            creatures.get(i).clearDamageBuff();
            creatures.get(i).clearHealthBuff();
            creatures.get(i).clearStun();
            creatureIds.add(creatures.get(i).getId());
            hitPoints.add(creatures.get(i).getTotalHitPoints());
            attacks.add(creatures.get(i).getTotalAttackDamage());
        }

        TurnStartResponseModel output = new TurnStartResponseModel(new ArrayList<>(), new ArrayList<>(),
                creatureIds, new ArrayList<>(),
                hitPoints,new ArrayList<>(),
                attacks, new ArrayList<>());
        return turnStartPresenter.updateScreen(output);
    }

    @Override
    public TurnStartResponseModel triggerTurnStartEffects() {
        return null;
    }
}
