package use_cases.death_detector_use_case;

import entities.GameEvent;
import entities.GameState;
import entities.Player;
import entities.PlayerFactory;
import entities.cardEffects.DamageModifyEffect;
import entities.cards.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_cases.win_loss_use_case.WinLossInputBoundary;
import use_cases.win_loss_use_case.WinLossOutputBoundary;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeathDetectorInteractorTest {

    GameState gameState;

    @BeforeEach
    void setUp() {
        CardFactory cardFactory = new CardFactory();
        PlayerFactory playerFactory = new PlayerFactory();
        CreatureCard creature = (CreatureCard) cardFactory.createCreatureCard("creature", -5, 1, 1, 1);
        CreatureCard testCreature1 = (CreatureCard) cardFactory.createCreatureCard("creatureT1", 5, 1, 1, 1);
        CreatureCard testCreature2 = (CreatureCard) cardFactory.createCreatureCard("creatureT2", 5, 1, 1, 1);

        PlayableCardData data = new PlayableCardData("", TargetType.FRIENDLY, List.of(new DamageModifyEffect(5)));
        StructureCard structure1 = (StructureCard) cardFactory.createStructureCard("name", data, GameEvent.CREATURE_DEATH);
        StructureCard structure2 = (StructureCard) cardFactory.createStructureCard("name", data, GameEvent.TURN_END);


        Player player1 = playerFactory.createPlayer("player1", new ArrayList<>(List.of(creature, testCreature1)), null, null);
        Player player2 = playerFactory.createPlayer("player2", new ArrayList<>(List.of(creature, testCreature2)), null, null);
        player1.setStructure(structure1);
        player2.setStructure(structure2);
        gameState = new GameState();
        gameState.setPlayers(player1, player2);
    }

    @Test
    void testDetectCreatureDeath() {
        DeathDetectorOutputBoundary presenter = new DeathDetectorOutputBoundary() {
            @Override
            public DeathDetectorResponseModel updateCreatureStats(DeathDetectorResponseModel outputData) {
                assertEquals(List.of(0, 6), outputData.getP1CreatureAttack());
                assertEquals(List.of(0, 1), outputData.getP2CreatureAttack());
                assertEquals(2, outputData.getP1CreatureIds().size());
                assertEquals(2, outputData.getP2CreatureIds().size());
                assertEquals(List.of(0, 5), outputData.getP1CreatureHitPoints());
                assertEquals(List.of(0, 5), outputData.getP2CreatureHitPoints());
                return null;
            }
        };
        DeathDetectorInteractor deathDetectorInteractor = new DeathDetectorInteractor(gameState, presenter);
        deathDetectorInteractor.detectCreatureDeath();
    }
}