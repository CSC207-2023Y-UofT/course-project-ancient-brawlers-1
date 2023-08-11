package interface_adapters.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import use_cases.attack_use_case.AttackInputBoundary;
import use_cases.attack_use_case.AttackRequestModel;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class AttackControllerTest {
    private AttackController attackController;
    @Mock
    private AttackInputBoundary attackInteractor;

    @BeforeEach
    public void setup() {
        openMocks(this);
        attackController = new AttackController(attackInteractor);
    }

    @Test
    public void testInitiateAttack() {
        int attackerId = 1;
        int targetId = 2;
        attackController.initiateAttack(attackerId, targetId);
        verify(attackInteractor).initiateAttack(any(AttackRequestModel.class));
    }

    @Test
    public void testDefend(){
        int attackerId = 1;
        int targetId = 2;
        attackController.defend(attackerId, targetId);
        verify(attackInteractor).defend(any(AttackRequestModel.class));
    }

    @Test
    public void testProcessAttack(){
        int attackerId = 1;
        int targetId = 2;
        attackController.processAttack(attackerId, targetId);
        Mockito.verify(attackInteractor).proceedAttack(any(AttackRequestModel.class));
    }

}
