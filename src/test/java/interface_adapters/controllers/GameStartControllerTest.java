package interface_adapters.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import use_cases.game_start_use_case.GameStartInputBoundary;
import use_cases.game_start_use_case.GameStartRequestModel;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.MockitoAnnotations.openMocks;

public class GameStartControllerTest {
    private GameStartController gameStartController;

    @Mock
    private GameStartInputBoundary gameStartInteractor;


    @BeforeEach
    public void setup(){
        openMocks(this);
        gameStartController = new GameStartController(gameStartInteractor);
    }

    @Test
    public void testDecidePlayOrder(){
        gameStartController.decidePlayOrder();
        Mockito.verify(gameStartInteractor).decidePlayOrder();
    }

    @Test
    public void testStartMulligan(){
        gameStartController.startMulligan();
        Mockito.verify(gameStartInteractor).prepareMulligan();
    }

    @Test
    public void testEndMulligan(){
        String id1 = "id1";
        String id2 = "id2";
        ArrayList cardID= new ArrayList<>();
        ArrayList cardName = new ArrayList<>();
        String card1 = "c1";
        String card2 = "c2";
        cardID.add(id1);
        cardID.add(id2);
        cardName.add(card1);
        cardName.add(card2);
        gameStartController.endMulligan(cardID, cardName);
        Mockito.verify(gameStartInteractor).processMulligan(any(GameStartRequestModel.class));
    }
}
