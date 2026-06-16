package questions.splitwise;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private static int counter = 1;

    int id;
    String name;
    List<User> members;
    List<Expense> expenses;

    public Group(String name) {
        this.id = counter++;
        this.name = name;
        this.members = new ArrayList<>();
        this.expenses = new ArrayList<>();
    }

    void addMember(User user) {
        members.add(user);
    }

    void addExpense(Expense expense) {
        expenses.add(expense);
    }
}
