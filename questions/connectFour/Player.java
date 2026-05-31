package questions.connectFour;

public class Player {
    private final int playerId;
    private final String name;
    private final DiscColor color;

    public DiscColor getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public int getPlayerId() {
        return playerId;
    }

    public Player(int playerId, String name, DiscColor color) {
        this.playerId = playerId;
        this.name = name;
        this.color = color;
    }
}
