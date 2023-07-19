package entities.cards;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EssenceCardTest {

    EssenceCard essence;

    @BeforeEach
    void setUp() {
        essence = new EssenceCard(5);
    }

    @Test
    void testIdAndName() {
        assertEquals(5, essence.getId());
        assertEquals("Essence", essence.getName());
    }
}