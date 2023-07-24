package use_cases.game_preparation_use_case;

import java.util.List;

public interface GamePrepOutputBoundary {

    void showGamePreparationScreen(List<String> creatureNames);

    GamePrepResponseModel showGameplayScreen(GamePrepResponseModel outputData);
}
