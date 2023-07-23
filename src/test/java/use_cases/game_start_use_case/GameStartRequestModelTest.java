package use_cases.game_start_use_case;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameStartRequestModelTest {

    GameStartRequestModel data;

    @BeforeEach
    void setUp() {
        List<Integer> cardIds = new ArrayList<>(List.of(1, 2, 3));
        List<String> cardNames = new ArrayList<>(List.of("name1", "name2", "name3"));
        data = new GameStartRequestModel(cardIds, cardNames);
    }

    @Test
    void testGetters() {
        assertEquals(new ArrayList<>(List.of(1, 2, 3)), data.getCardIds());
        assertEquals(new ArrayList<>(List.of("name1", "name2", "name3")), data.getCardNames());
    }

    @Test
    void testSetters() {
        List<Integer> newIds = new ArrayList<>(List.of(4, 5, 6));
        List<String> newNames = new ArrayList<>(List.of("name4", "name5", "name6"));
        data.setCardIds(newIds);
        data.setCardNames(newNames);
        assertEquals(newIds, data.getCardIds());
        assertEquals(newNames, data.getCardNames());
    }
}