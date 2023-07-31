import entities.GameState;
import entities.PlayerFactory;
import entities.cards.CardFactory;
import entities.decks.DeckFactory;
import game_ui.*;
import interface_adapters.controllers.GamePrepController;
import interface_adapters.presenters.GamePrepPresenter;
import use_cases.game_preparation_use_case.*;

import javax.swing.*;
import java.awt.*;

public class AncientBrawlersApp {

    private static final GameState gameState = new GameState();
    private static final CardFactory cardFactory = new CardFactory();
    private static final DeckFactory deckFactory = new DeckFactory();
    private static final PlayerFactory playerFactory = new PlayerFactory();
    private static final CardDataGateway dataAccessor = new TestDataAccessor();

    public static void main(String[] args) {

    }
}
