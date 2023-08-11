package interface_adapters.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import use_cases.death_detector_use_case.DeathDetectorInputBoundary;
import use_cases.win_loss_use_case.WinLossInputBoundary;

import static org.mockito.MockitoAnnotations.openMocks;
public class EndGameControllerTest {
    private EndGameController endGameController;
    @Mock
    private WinLossInputBoundary winLossInteractor;
    @Mock
    private DeathDetectorInputBoundary deathInteractor;

    @BeforeEach
    public void setup(){
        openMocks(this);
        endGameController = new EndGameController(deathInteractor, winLossInteractor);
    }

    @Test
    public void testCheckEndGame(){
        endGameController.checkEndGame();

        //Checking to see if the two methods in the two different interactors are called
        Mockito.verify(deathInteractor).detectCreatureDeath();
        Mockito.verify(winLossInteractor).detectWinLoss();

    }


}
