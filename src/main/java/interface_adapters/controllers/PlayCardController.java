package interface_adapters.controllers;

import use_cases.play_card_use_case.PlayCardInputBoundary;

public class PlayCardController {

    private final PlayCardInputBoundary playCardInteractor;

    public PlayCardController(PlayCardInputBoundary playCardInteractor){
        this.playCardInteractor = playCardInteractor;
    }

    public void playCard(int cardId) {
        playCardInteractor.playCard(cardId);
    }

    public void playSingleTarget(int cardId, int targetId){
        playCardInteractor.playSingleTargetCard(cardId, targetId);
    }
}