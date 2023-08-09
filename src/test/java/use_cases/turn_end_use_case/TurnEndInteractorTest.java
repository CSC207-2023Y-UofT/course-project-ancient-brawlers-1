package use_cases.turn_end_use_case;

import entities.GameEvent;
import entities.GameState;
import entities.Player;
import entities.PlayerFactory;
import entities.cardEffects.CardEffect;
import entities.cardEffects.DamageEffect;
import entities.cardEffects.StunEffect;
import entities.cards.CardFactory;
import entities.cards.CreatureCard;
import entities.cards.PlayableCardData;
import entities.cards.StructureCard;
import entities.cards.TargetType;
import entities.decks.EssenceDeck;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TurnEndInteractorTest {
    GameState gameState;
    TurnEndInteractor turnEndInteractor;

    @BeforeEach
    void setUp() {
        CardFactory cardFactory = new CardFactory();
        PlayerFactory playerFactory = new PlayerFactory();

        List<CreatureCard> creatures1 = new ArrayList<>();
        creatures1.add((CreatureCard) cardFactory.createCreatureCard("Creature1", 30, 5, 2, 1));
        creatures1.add((CreatureCard) cardFactory.createCreatureCard("Creature2", 50, 5, 1, 2));
        creatures1.add((CreatureCard) cardFactory.createCreatureCard("Creature3", 20, 5, 3, 1));
        List<CreatureCard> creatures2 = new ArrayList<>();
        creatures2.add((CreatureCard) cardFactory.createCreatureCard("Creature4", 30, 5, 2, 1));
        creatures2.add((CreatureCard) cardFactory.createCreatureCard("Creature5", 50, 5, 1, 2));
        creatures2.add((CreatureCard) cardFactory.createCreatureCard("Creature6", 10, 5, 3, 1));

        Player player1 = playerFactory.createPlayer("Player 1", creatures1, null, new EssenceDeck(cardFactory));
        Player player2 = playerFactory.createPlayer("Player 2", creatures2, null, new EssenceDeck(cardFactory));

        gameState = new GameState();
        gameState.setPlayers(player1, player2);
    }

    @Test
    void testPassTurn() {
        Integer initIndex = gameState.getCurrentPlayerIndex();
        Player initPlayer = gameState.getCurrentPlayer();

        TurnEndOutputBoundary presenter = new TurnEndOutputBoundary() {
            @Override
            public TurnEndResponseModel showTurnEndScreen(TurnEndResponseModel responseModel) {
                fail("passTurn() should not call showTurnEndScreen().");
                return null;
            }

            @Override
            public void notifyTurnChange(String nextPlayer) {

            }
        };
        TurnEndInteractor interactor = new TurnEndInteractor(gameState, presenter);
        interactor.passTurn();

        Integer currentIndex = gameState.getCurrentPlayerIndex();
        Player currentPlayer = gameState.getCurrentPlayer();

        assertFalse(initIndex == currentIndex);
        assertFalse(initPlayer == currentPlayer);
    }

    @Test
    void testTriggerEndTurnEffects() {
        Player currentPlayer = gameState.getCurrentPlayer();
        CreatureCard currentCreature = currentPlayer.getCreatures().get(0);

        StunEffect effect = new StunEffect();
        effect.invokeEffect(currentCreature);
        assertTrue(currentCreature.isStunned());

        // Structure card
        List<CardEffect> effects = new ArrayList<>(List.of(new DamageEffect(10)));
        PlayableCardData cardData = new PlayableCardData("desc", TargetType.ALL, effects);
        StructureCard structure = new StructureCard(100, "struct", cardData, GameEvent.TURN_END);
        currentPlayer.setStructure(structure);

        TurnEndOutputBoundary presenter = new TurnEndOutputBoundary() {
            @Override
            public TurnEndResponseModel showTurnEndScreen(TurnEndResponseModel responseModel) {
                assertEquals(List.of(20, 40, 10), responseModel.getHitPoints1());
                assertEquals(List.of(20, 40, 0), responseModel.getHitPoints2());
                return null;
            }

            @Override
            public void notifyTurnChange(String nextPlayer) {

            }
        };

        TurnEndInteractor interactor = new TurnEndInteractor(gameState, presenter);
        interactor.triggerEndTurnEffects();

        assertFalse(currentCreature.isStunned());
    }
}
