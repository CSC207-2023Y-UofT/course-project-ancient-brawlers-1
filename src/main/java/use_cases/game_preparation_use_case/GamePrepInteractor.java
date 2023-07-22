package use_cases.game_preparation_use_case;

import entities.PlayerFactory;
import entities.cards.CardFactory;
import entities.decks.DeckFactory;

public class GamePrepInteractor implements GamePrepInputBoundary {

    final CardFactory cardFactory;
    final DeckFactory deckFactory;
    final PlayerFactory playerFactory;
    final GamePrepOutputBoundary gamePrepPresenter;

    public GamePrepInteractor(CardFactory cardFactory, DeckFactory deckFactory,
                              PlayerFactory playerFactory, GamePrepOutputBoundary gamePrepPresenter) {
        this.cardFactory = cardFactory;
        this.deckFactory = deckFactory;
        this.playerFactory = playerFactory;
        this.gamePrepPresenter = gamePrepPresenter;
    }

    @Override
    public void promptPlayerInfo() {

    }

    @Override
    public GamePrepResponseModel processPlayerInfo(GamePrepRequestModel requestModel) {
        return null;
    }
}
