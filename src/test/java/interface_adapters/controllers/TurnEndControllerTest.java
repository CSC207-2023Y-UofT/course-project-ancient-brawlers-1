package interface_adapters.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import use_cases.turn_end_use_case.TurnEndInputBoundary;

import static org.mockito.MockitoAnnotations.openMocks;

public class TurnEndControllerTest{

    private TurnEndController turnEndController;

    @Mock
    private TurnEndInputBoundary turnEndInteractor;

    @BeforeEach
    public void setup(){
        openMocks(this);
        turnEndController = new TurnEndController(turnEndInteractor);
    }

    @Test
    public void testPassTurn(){
        turnEndController.passTurn();
        Mockito.verify(turnEndInteractor).passTurn();
    }

}