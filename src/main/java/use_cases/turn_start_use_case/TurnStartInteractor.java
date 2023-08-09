package use_cases.turn_start_use_case;

import entities.GameEvent;
import entities.GameState;
import entities.Player;
import entities.cardEffects.CardEffect;
import entities.cardEffects.CreatureStatsEffect;
import entities.cardEffects.PlayerStatsEffect;
import entities.cards.Card;
import entities.cards.CreatureCard;
import entities.cards.StructureCard;
import entities.decks.EssenceDeck;
import entities.decks.PlayerDeck;

import java.util.ArrayList;
import java.util.List;

/**
 * TurnStartInteractor implements the methods needed for the logic of
 * start-of-turn phase.
 */
public class TurnStartInteractor implements TurnStartInputBoundary {

    final GameState gameState;

    final TurnStartOutputBoundary turnStartPresenter;

    public TurnStartInteractor(GameState gameState, TurnStartOutputBoundary turnStartPresenter) {
        this.gameState = gameState;
        this.turnStartPresenter = turnStartPresenter;
    }

    /**
     * Draws cards for the current player: 2 cards from their play deck and 2
     * more cards from their Essence deck (4 in total).
     *
     * @return a DrawCardOutputModel, showing which cards are successfully added
     * to the hand and which are discarded due to hand being full.
     */
    @Override
    public DrawCardOutputModel drawCards() {

        //Getting the player deck from the current player
        Player player = gameState.getCurrentPlayer();
        PlayerDeck deck = player.getPlayerDeck();

        //Getting the essence deck from the current player
        EssenceDeck edeck = player.getEssenceDeck();

        //Drawing twice from the player deck then from the essence deck
        boolean val1;
        boolean val2;
        List<Integer> burntIds = new ArrayList<>();
        List<String> burntNames = new ArrayList<>();
        List<Integer> keptIds = new ArrayList<>();
        List<String> keptNames = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Card card = deck.draw();
            if (card != null) {
                //going to find kept and discarded cards
                val1 = player.addCard(card);
                if (val1) {
                    keptIds.add(card.getId());
                    keptNames.add(card.getName());
                } else {
                    burntIds.add(card.getId());
                    burntNames.add(card.getName());
                }
            } else {
                 break;
            }
        }
        for (int i = 0; i < 2; i++) {
            Card ecard = edeck.draw();
            val2 = player.addCard(ecard);
            if (val2){
                keptIds.add(ecard.getId());
                keptNames.add(ecard.getName());
            } else {
                burntIds.add(ecard.getId());
                burntNames.add(ecard.getName());
            }

        }

        DrawCardOutputModel output = new DrawCardOutputModel(gameState.getCurrentPlayerIndex(), keptIds, keptNames, burntIds, burntNames);
        
        return turnStartPresenter.showDrawResult(output);
    }


    @Override
    public CreatureStatsUpdateModel clearBuffs() {
        Player player = gameState.getCurrentPlayer();
        List<CreatureCard> creatures = player.getCreatures();
        for (CreatureCard creature : creatures) {
            creature.clearDamageBuff();
            creature.clearHealthBuff();
            creature.clearStun();
        }

        CreatureStatsUpdateModel outputData = getCreatureStatsModel();
        return turnStartPresenter.showClearBuffs(outputData);
    }

    /**
     * Trigger the start-of-turn effects in the active structure of the current
     * player, if it does exist.
     * This method returns many values because the effects have a variety of
     * targets, so outcomes also vary.
     *
     * @return a TriggerEffectUpdateModel, a combination of a list of hand card
     * ids and a CreatureStatsUpdateModel.
     */
    @Override
    public TriggerEffectUpdateModel triggerTurnStartEffects() {
        Player player1 = gameState.getCurrentPlayer();
        Player player2 = gameState.getOpposingPlayer();
        if (player1.getStructure() != null) {
            StructureCard structureCard = player1.getStructure();
            List<CreatureCard> effectTargets = new ArrayList<>();
            Player effectplayer = null;
            if (structureCard.getTriggerEvent() == GameEvent.TURN_START) {
                switch (structureCard.getTargetType()) {
                    case ALL:
                        effectTargets.addAll(player1.getCreatures());
                        effectTargets.addAll(player2.getCreatures());
                        break;
                    case FRIENDLY:
                        effectTargets.addAll(player1.getCreatures());
                        break;
                    case ENEMY:
                        effectTargets.addAll(player2.getCreatures());
                        break;
                    case OPPONENT:
                        effectplayer = player2;
                        break;
                    case SELF:
                        effectplayer = player1;
                        break;
                }
            }

            if (effectplayer != null) {
                for (CardEffect effect : structureCard.getEffects()) {
                    ((PlayerStatsEffect) effect).invokeEffect(effectplayer);
                }
            }
            for (CreatureCard card : effectTargets) {
                for (CardEffect effect : structureCard.getEffects()) {
                    ((CreatureStatsEffect) effect).invokeEffect(card);
                }
            }
        }

        List<Integer> handIds = new ArrayList<>();
        List<String> handNames = new ArrayList<>();
        for (Card card : player1.getHand()) {
            handIds.add(card.getId());
            handNames.add(card.getName());
        }

        TriggerEffectUpdateModel outputData = new TriggerEffectUpdateModel(handIds, handNames, getCreatureStatsModel());

        return turnStartPresenter.showEffectUpdates(outputData);
    }

    /**
     * Helper method to create a CreatureStatsUpdateModel directly from the
     * creatures in the current GameState.
     *
     * @return a CreatureStatsUpdateModel, containing the id, hit-points, and
     * attack of all creatures in the GameState (for both players).
     */
    private CreatureStatsUpdateModel getCreatureStatsModel() {

        Player player1 = gameState.getCurrentPlayer();
        Player player2 = gameState.getOpposingPlayer();

        if (gameState.getCurrentPlayerIndex() == 1) {
            player1 = gameState.getOpposingPlayer();
            player2 = gameState.getCurrentPlayer();}

        List<Integer> ids1 = new ArrayList<>();
        List<Integer> ids2 = new ArrayList<>();
        List<Integer> hitPoints1 = new ArrayList<>();
        List<Integer> hitPoints2 = new ArrayList<>();
        List<Integer> attacks1 = new ArrayList<>();
        List<Integer> attacks2 = new ArrayList<>();

        for (CreatureCard c : player1.getCreatures()) {
            ids1.add(c.getId());
            hitPoints1.add(c.getTotalHitPoints());
            attacks1.add(c.getTotalAttackDamage());
        }
        for (CreatureCard c : player2.getCreatures()) {
            ids2.add(c.getId());
            hitPoints2.add(c.getTotalHitPoints());
            attacks2.add(c.getTotalAttackDamage());
        }

        return new CreatureStatsUpdateModel(gameState.getCurrentPlayerIndex(), ids1, ids2, hitPoints1, hitPoints2,
                attacks1, attacks2);
    }
}

