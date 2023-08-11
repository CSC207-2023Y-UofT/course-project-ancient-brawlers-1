package interface_adapters.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import use_cases.game_preparation_use_case.GamePrepInteractor;
import use_cases.game_preparation_use_case.GamePrepRequestModel;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.MockitoAnnotations.openMocks;

public class GamePrepControllerTest {
    private GamePrepController gamePrepController;

    @Mock
    private GamePrepInteractor gamePrepInteractor;

    @BeforeEach
    public void setup(){
        openMocks(this);
        gamePrepController = new GamePrepController(gamePrepInteractor);
    }

    @Test
    public void testCreateNewGame(){
        gamePrepController.createNewGame();
        Mockito.verify(gamePrepInteractor).promptPlayerInfo();
    }

    @Test
    public void testInitialGameState(){
        String p1 = "p1";
        String p2 = "p2";
        ArrayList arr1 = new ArrayList<>();
        ArrayList arr2 = new ArrayList<>();
        String card1 = "c1";
        String card2 = "c2";
        arr1.add(card1);
        arr1.add(card2);

        gamePrepController.setInitialGameState(p1, p2, arr1, arr2);
        Mockito.verify(gamePrepInteractor).processPlayerInfo(any(GamePrepRequestModel.class));

    }


}
