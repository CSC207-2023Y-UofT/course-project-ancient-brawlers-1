package interface_adapters.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import use_cases.turn_start_use_case.TurnStartInputBoundary;

import static org.mockito.MockitoAnnotations.openMocks;


public class TurnStartControllerTest {
    private TurnStartController turnStartController;

    @Mock
    private TurnStartInputBoundary turnStartInteractor;

    @BeforeEach
    public void setup(){
        openMocks(this);
        turnStartController = new TurnStartController(turnStartInteractor);
    }

    @Test
    public void testProcessTurnStart(){
        turnStartController.processTurnStart();
        Mockito.verify(turnStartInteractor).drawCards();
        Mockito.verify(turnStartInteractor).clearBuffs();
        Mockito.verify(turnStartInteractor).triggerTurnStartEffects();


    }




}
