package use_cases.turn_start_use_case;

import entities.GameState;
import entities.Player;
import entities.cards.Card;
import entities.cards.CreatureCard;
import entities.cards.StructureCard;
import entities.decks.EssenceDeck;
import entities.decks.PlayerDeck;
import entities.cardEffects.CreatureStatsEffect;
import entities.cardEffects.CardEffect;
import entities.GameEvent;

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
        Player player1 = gameState.getCurrentPlayer();
        Player player2 = gameState.getOpposingPlayer();
        if (player1.getStructure() != null){
            StructureCard structureCard = player1.getStructure();
            List<CreatureCard> effectTargets= new ArrayList<>();
            if(structureCard.getTriggerEvent() == GameEvent.TURN_START){
                switch (structureCard.getTargetType()){
                    case ALL:
                        effectTargets.addAll(player1.getCreatures());
                        effectTargets.addAll(player2.getCreatures());
                        break;

                    case FRIENDLY:
                        effectTargets.addAll(player1.getCreatures());
                        break;

                    case OPPONENT:
                        effectTargets.addAll(player2.getCreatures());
                        break;
                }
            }
            for (CreatureCard card: effectTargets){
                for (CardEffect effect: structureCard.getEffects()){
                    if (effect instanceof CreatureStatsEffect){
                        ((CreatureStatsEffect) effect).invokeEffect(card);
                    }
                }
            }
        }
        List<Integer> hitPoints1 = new ArrayList<>();
        List<Integer> hitPoints2 = new ArrayList<>();
        List<Integer> creatureIds1 = new ArrayList<>();
        List<Integer> creatureIds2 = new ArrayList<>();

        List<Integer> handIds1 = new ArrayList<>();
        List<Integer> handIds2 = new ArrayList<>();
        List<Integer> attackIds1 = new ArrayList<>();
        List<Integer> attackIds2 = new ArrayList<>();

        for (CreatureCard creature : player1.getCreatures()) {
            hitPoints1.add(creature.getTotalHitPoints());
            creatureIds1.add(creature.getId());
            attackIds1.add(creature.getAttackDamage());
        }

        for (CreatureCard creature : player2.getCreatures()) {
            hitPoints2.add(creature.getTotalHitPoints());
            creatureIds2.add(creature.getId());
            attackIds2.add(creature.getAttackDamage());
        }

        for (Card card : player1.getHand()) {
            handIds1.add(card.getId());
        }

        for (Card card : player2.getHand()) {
            handIds2.add(card.getId());
        }
        TurnStartResponseModel turnStartResModel = new TurnStartResponseModel(handIds1, handIds2, creatureIds1,
                creatureIds2, hitPoints1, hitPoints2, attackIds1, attackIds2);


        return turnStartPresenter.updateScreen(turnStartResModel);
    }
}

