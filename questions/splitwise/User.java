package questions.splitwise;

public class User {
    private static int counter = 1;

    int id;
    String name;
    String email;

    public User(String name, String email) {
        this.id = counter++;
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return name;
    }
}
