package data_access;

import data_access.CardImageMapper;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class CardImageMapperTest {

    @Test
    void getImageByName() {
        CardImageMapper imageMapper = new CardImageMapper("./src/gameArt");

        ImageIcon image = imageMapper.getImageByName("Forest of the Elves");

        assertNotNull(image);
    }
}