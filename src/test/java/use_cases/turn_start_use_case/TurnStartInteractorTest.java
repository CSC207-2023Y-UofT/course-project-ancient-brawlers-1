package use_cases.turn_start_use_case;

import entities.GameEvent;
import entities.GameState;
import entities.Player;
import entities.PlayerFactory;
import entities.cardEffects.*;
import entities.cards.*;
import entities.decks.Deck;
import entities.decks.DeckFactory;
import entities.decks.EssenceDeck;
import entities.decks.PlayerDeck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TurnStartInteractorTest {

    GameState gameState;

    @BeforeEach
    void setUp() {
        CardFactory cardFactory = new CardFactory();
        PlayerFactory playerFactory = new PlayerFactory();
        DeckFactory deckFactory = new DeckFactory();

        List<CardEffect> effects = new ArrayList<>(List.of(new HealEffect(10)));
        PlayableCardData cardData = new PlayableCardData("Some card", TargetType.ANY, effects);
        Card card1 = cardFactory.createActionCard("name1", cardData);

        // Note that card1 should have id 1, so right now all the play deck cards have id 1.
        Deck playerDeck1 = deckFactory.createPlayerDeck(new ArrayList<>(List.of(card1, card1, card1)));
        Deck playerDeck2 = deckFactory.createPlayerDeck(new ArrayList<>(List.of(card1, card1, card1)));
        Deck essenceDeck1 = deckFactory.createEssenceDeck(cardFactory);
        Deck essenceDeck2 = deckFactory.createEssenceDeck(cardFactory);

        // note that creature1 should have id 2, and creature2 has id 3.
        CreatureCard creature1 = (CreatureCard) cardFactory.createCreatureCard("Name1", 1, 1, 1, 1);
        CreatureCard creature2 = (CreatureCard) cardFactory.createCreatureCard("Name2", 1, 1, 1, 1);

        Player player1 = playerFactory.createPlayer("Player 1", new ArrayList<>(List.of(creature1)),
                (PlayerDeck) playerDeck1, (EssenceDeck) essenceDeck1);
        Player player2 = playerFactory.createPlayer("Player 2", new ArrayList<>(List.of(creature2)),
                (PlayerDeck) playerDeck2, (EssenceDeck) essenceDeck2);

        // Leave Player 1 with an empty hand, but give Player 2 a hand of 9 cards.
        for (int i = 0; i < 9; i++) {
            player2.addCard(player2.getEssenceDeck().draw());
        }
        gameState = new GameState();
        gameState.setPlayers(player1, player2);
    }

    @Test
    void testDrawCards_BelowHandCapacity() {
        TurnStartOutputBoundary presenter = new TurnStartOutputBoundary() {
            @Override
            public DrawCardOutputModel showDrawResult(DrawCardOutputModel outputData) {
                assertEquals(4, outputData.getNewCardIds().size(),
                        "2 play deck cards and 2 Essence cards should be drawn.");
                assertEquals(0, outputData.getBurntCardIds().size(),
                        "No cards should be discarded because hand is not full.");
                return null;
            }

            @Override
            public CreatureStatsUpdateModel showClearBuffs(CreatureStatsUpdateModel outputData) {
                fail("drawCards() should not call showClearBuffs().");
                return null;
            }

            @Override
            public TriggerEffectUpdateModel showEffectUpdates(TriggerEffectUpdateModel outputData) {
                fail("drawCards() should not call showEffectUpdates().");
                return null;
            }
        };
        TurnStartInteractor interactor = new TurnStartInteractor(gameState, presenter);
        interactor.drawCards();
    }

    @Test
    void testDrawCards_ExceedHandCapacity() {
        TurnStartOutputBoundary presenter = new TurnStartOutputBoundary() {
            @Override
            public DrawCardOutputModel showDrawResult(DrawCardOutputModel outputData) {
                assertEquals(1, outputData.getNewCardIds().size(),
                        "1 play deck card should be drawn.");
                assertEquals(1, outputData.getNewCardIds().size(),
                        "The card drawn should have id 1, which comes from the PlayerDeck, not EssenceDeck.");
                assertTrue(outputData.getNewCardNames().contains("name1"),
                        "The output should contain the name of the card.");
                assertEquals(3, outputData.getBurntCardIds().size(),
                        "3 cards should be discarded because hand is full.");
                assertTrue(outputData.getBurntCardNames().contains("Essence"),
                        "There should be cards with name 'Essence' in the discarded cards.");
                return null;
            }

            @Override
            public CreatureStatsUpdateModel showClearBuffs(CreatureStatsUpdateModel outputData) {
                fail("drawCards() should not call showClearBuffs().");
                return null;
            }

            @Override
            public TriggerEffectUpdateModel showEffectUpdates(TriggerEffectUpdateModel outputData) {
                fail("drawCards() should not call showEffectUpdates().");
                return null;
            }
        };
        gameState.switchPlayer();  // this test is for Player 2.
        TurnStartInteractor interactor = new TurnStartInteractor(gameState, presenter);
        interactor.drawCards();
    }

    @Test
    void testClearBuffs() {
        // The current player's creatures are given a health buff of 100,
        // We expect the buff to be cleared when this method is run.
        Player player = gameState.getCurrentPlayer();
        for (CreatureCard c : player.getCreatures()) {
            c.addHealthBuff(100);
        }
        TurnStartOutputBoundary presenter = new TurnStartOutputBoundary() {
            @Override
            public DrawCardOutputModel showDrawResult(DrawCardOutputModel outputData) {
                fail("clearBuff() should not call showDrawResult().");
                return null;
            }

            @Override
            public CreatureStatsUpdateModel showClearBuffs(CreatureStatsUpdateModel outputData) {
                assertTrue(outputData.getCreatureIds1().contains(2), "Creature id should be 2.");
                assertTrue(outputData.getAttacks1().contains(1), "Creature attack should remain 1.");
                assertTrue(outputData.getHitPoints1().contains(1), "Creature hitPoints should be back to 1.");
                assertTrue(outputData.getCreatureIds2().contains(3), "Player 2 creatures should have the original stats.");
                assertTrue(outputData.getAttacks2().contains(1), "Player 2 creatures should have the original stats.");
                assertTrue(outputData.getHitPoints2().contains(1), "Player 2 creatures should have the original stats.");
                return null;
            }

            @Override
            public TriggerEffectUpdateModel showEffectUpdates(TriggerEffectUpdateModel outputData) {
                fail("clearBuff() should not call showEffectUpdates().");
                return null;
            }
        };
        TurnStartInteractor interactor = new TurnStartInteractor(gameState, presenter);
        interactor.clearBuffs();
    }

    @Test
    void testTriggerTurnStartEffects_correctEvent() {
        List<CardEffect> effects = new ArrayList<>(List.of(new DamageEffect(10)));
        PlayableCardData cardData = new PlayableCardData("desc", TargetType.ALL, effects);
        StructureCard structure = new StructureCard(100, "struct", cardData, GameEvent.TURN_START);
        gameState.getCurrentPlayer().setStructure(structure);

        TurnStartOutputBoundary presenter = new TurnStartOutputBoundary() {
            @Override
            public DrawCardOutputModel showDrawResult(DrawCardOutputModel outputData) {
                fail("triggerTurnStartEffects() should not call showDrawResult().");
                return null;
            }

            @Override
            public CreatureStatsUpdateModel showClearBuffs(CreatureStatsUpdateModel outputData) {
                fail("triggerTurnStartEffects() should not call showClearBuffs().");
                return null;
            }

            @Override
            public TriggerEffectUpdateModel showEffectUpdates(TriggerEffectUpdateModel outputData) {
                CreatureStatsUpdateModel creatureStats = outputData.getAllCreatureStats();
                assertEquals(List.of(-9), creatureStats.getHitPoints1());
                assertEquals(List.of(-9), creatureStats.getHitPoints2());
                return null;
            }
        };
        TurnStartInteractor interactor = new TurnStartInteractor(gameState, presenter);
        interactor.triggerTurnStartEffects();
    }

    @Test
    void testTriggerTurnStartEffects_irrelevantEvent() {
        List<CardEffect> effects = new ArrayList<>(List.of(new DamageEffect(10)));
        PlayableCardData cardData = new PlayableCardData("desc", TargetType.ALL, effects);
        // this one triggers at TURN_END, so it should not be effective here.
        StructureCard structure = new StructureCard(100, "struct", cardData, GameEvent.TURN_END);
        gameState.getCurrentPlayer().setStructure(structure);

        TurnStartOutputBoundary presenter = new TurnStartOutputBoundary() {
            @Override
            public DrawCardOutputModel showDrawResult(DrawCardOutputModel outputData) {
                fail("triggerTurnStartEffects() should not call showDrawResult().");
                return null;
            }

            @Override
            public CreatureStatsUpdateModel showClearBuffs(CreatureStatsUpdateModel outputData) {
                fail("triggerTurnStartEffects() should not call showClearBuffs().");
                return null;
            }

            @Override
            public TriggerEffectUpdateModel showEffectUpdates(TriggerEffectUpdateModel outputData) {
                CreatureStatsUpdateModel creatureStats = outputData.getAllCreatureStats();
                assertEquals(List.of(1), creatureStats.getHitPoints1());
                assertEquals(List.of(1), creatureStats.getHitPoints2());
                return null;
            }
        };
        TurnStartInteractor interactor = new TurnStartInteractor(gameState, presenter);
        interactor.triggerTurnStartEffects();
    }
    @Test
    void testTriggerTurnStartEffects_playerEvent() {
        List<CardEffect> effects = new ArrayList<>(List.of(new DrawCardEffect(2, "ESSENCE_DECK")));
        PlayableCardData cardData = new PlayableCardData("desc", TargetType.SELF, effects);
        StructureCard structure = new StructureCard(100, "struct", cardData, GameEvent.TURN_START);
        gameState.getCurrentPlayer().setStructure(structure);

        TurnStartOutputBoundary presenter = new TurnStartOutputBoundary() {
            @Override
            public DrawCardOutputModel showDrawResult(DrawCardOutputModel outputData) {
                fail("triggerTurnStartEffects() should not call showDrawResult().");
                return null;
            }

            @Override
            public CreatureStatsUpdateModel showClearBuffs(CreatureStatsUpdateModel outputData) {
                fail("triggerTurnStartEffects() should not call showClearBuffs().");
                return null;
            }

            @Override
            public TriggerEffectUpdateModel showEffectUpdates(TriggerEffectUpdateModel outputData) {
                CreatureStatsUpdateModel creatureStats = outputData.getAllCreatureStats();
                assertEquals(List.of(1), creatureStats.getHitPoints1());
                assertEquals(List.of(1), creatureStats.getHitPoints2());
                assertEquals(List.of(1), creatureStats.getAttacks1());
                assertEquals(List.of(1), creatureStats.getAttacks2());
                assertEquals(List.of(13,14),outputData.getFinalHandIds());
                return null;
            }
        };
        TurnStartInteractor interactor = new TurnStartInteractor(gameState, presenter);
        interactor.triggerTurnStartEffects();
    }

}

