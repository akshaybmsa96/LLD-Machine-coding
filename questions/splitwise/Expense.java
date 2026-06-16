package questions.splitwise;

public class Expense {
    private static int counter = 1;

    int id;
    String description;
    double amount;
    User paidBy;   // the person who paid
    User owedBy;   // the person who owes paidBy

    public Expense(String description, double amount, User paidBy, User owedBy) {
        this.id = counter++;
        this.description = description;
        this.amount = amount;
        this.paidBy = paidBy;
        this.owedBy = owedBy;
    }
}
