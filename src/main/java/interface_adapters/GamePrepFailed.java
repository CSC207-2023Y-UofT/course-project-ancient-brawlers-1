package interface_adapters;

public class GamePrepFailed extends RuntimeException {

    public GamePrepFailed(String message) {
        super(message);
    }
}
