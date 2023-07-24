package use_cases.win_loss_use_case;

import entities.GameState;
import entities.Player;
import entities.PlayerFactory;
import entities.cards.CardFactory;
import entities.cards.CreatureCard;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WinLossInteractorTest {

    GameState gameState;

    @BeforeEach
    void setUp() {
        CardFactory cardFactory = new CardFactory();
        PlayerFactory playerFactory = new PlayerFactory();
        CreatureCard creature = (CreatureCard) cardFactory.createCreatureCard("creature1", 10, 5, 1, 2);
        Player player1 = playerFactory.createPlayer("player1", List.of(creature), null, null);
        Player player2 = playerFactory.createPlayer("player2", new ArrayList<>(), null, null);
        gameState = new GameState();
        gameState.setPlayers(player1, player2);
    }

    @Test
    void testDetectWinLoss() {
        WinLossOutputBoundary presenter = new WinLossOutputBoundary() {
            @Override
            public WinLossResponseModel showVictoryScreen(WinLossResponseModel outputData) {
                assertEquals("player1", outputData.getWinnerName());
                return null;
            }
        };
        WinLossInteractor winLossInteractor = new WinLossInteractor(gameState, presenter);
        winLossInteractor.detectWinLoss();
    }
}