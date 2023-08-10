package interface_adapters.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import use_cases.play_card_use_case.PlayCardInputBoundary;


import static org.mockito.MockitoAnnotations.openMocks;

public class PlayCardControllerTest {
    private PlayCardController playCardController;

    @Mock
    private PlayCardInputBoundary playCardInteractor;

    @BeforeEach
    public void setup(){
        openMocks(this);
        playCardController = new PlayCardController(playCardInteractor);
    }
    @Test
    public void testPlayCard(){
        int x = 5;
        playCardController.playCard(x);
        Mockito.verify(playCardInteractor).playCard(x);

    }

    @Test
    public void testPlaySingleTarget(){
        playCardController.playSingleTarget(5,5);
        Mockito.verify(playCardInteractor).playSingleTargetCard(5,5);
    }
}
