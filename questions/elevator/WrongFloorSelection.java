package questions.elevator;

public class WrongFloorSelection extends RuntimeException {
    public WrongFloorSelection(String message) {
        super(message);
    }
}
