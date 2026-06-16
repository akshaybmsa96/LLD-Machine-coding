package questions.splitwise;

public class Transaction {
    User from;
    User to;
    double amount;

    public Transaction(User from, User to, double amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return from.name + " pays " + to.name + " -> " + String.format("%.2f", amount);
    }
}
