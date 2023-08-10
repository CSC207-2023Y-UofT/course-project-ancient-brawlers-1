package use_cases.game_start_use_case;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameStartResponseModelTest {

    GameStartResponseModel data;

    @BeforeEach
    void setUp() {
        List<Integer> cardIds = new ArrayList<>(List.of(1, 2, 3));
        List<String> cardNames = new ArrayList<>(List.of("name1", "name2", "name3"));
        List<String> cardDescs = new ArrayList<>(List.of("", "", ""));
        data = new GameStartResponseModel(0, cardIds, cardNames, cardDescs);
    }

    @Test
    void testGetters() {
        assertEquals(new ArrayList<>(List.of(1, 2, 3)), data.getCardIds());
        assertEquals(new ArrayList<>(), data.getBonusCardIds());
        assertEquals(new ArrayList<>(List.of("name1", "name2", "name3")), data.getCardNames());
        assertEquals(new ArrayList<>(), data.getBonusCardNames());
    }

    @Test
    void testSetters() {
        List<Integer> newIds = new ArrayList<>(List.of(4, 5, 6));
        List<Integer> newBonusIds = new ArrayList<>(List.of(7, 8, 9));
        List<String> newNames = new ArrayList<>(List.of("name4", "name5", "name6"));
        List<String> newBonusNames = new ArrayList<>(List.of("name7", "name8", "name9"));

        data.setCardIds(newIds);
        data.setBonusCardIds(newBonusIds);
        data.setCardNames(newNames);
        data.setBonusCardNames(newBonusNames);

        assertEquals(newIds, data.getCardIds());
        assertEquals(newBonusIds, data.getBonusCardIds());
        assertEquals(newNames, data.getCardNames());
        assertEquals(newBonusNames, data.getBonusCardNames());
    }
}