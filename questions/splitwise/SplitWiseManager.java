package questions.splitwise;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class SplitWiseManager {

    Map<Integer, User> users = new HashMap<>();
    Map<Integer, Group> groups = new HashMap<>();

    User addUser(String name, String email) {
        User user = new User(name, email);
        users.put(user.id, user);
        return user;
    }

    Group createGroup(String name, List<User> members) {
        Group group = new Group(name);
        members.forEach(group::addMember);
        groups.put(group.id, group);
        return group;
    }

    Expense addExpense(int groupId, String description, double amount, User paidBy, User owedBy) {
        Group group = groups.get(groupId);
        if (group == null) throw new IllegalArgumentException("Group not found: " + groupId);

        Expense expense = new Expense(description, amount, paidBy, owedBy);
        group.addExpense(expense);
        return expense;
    }

    // Computes net balance per user in the group.
    // Positive = user is owed money. Negative = user owes money.
    // Use this inside simplifyExpenses to build your debtor/creditor lists.
    Map<User, Double> getNetBalances(int groupId) {
        Group group = groups.get(groupId);
        if (group == null) throw new IllegalArgumentException("Group not found: " + groupId);

        Map<User, Double> balances = new HashMap<>();
        for (User member : group.members) balances.put(member, 0.0);

        for (Expense expense : group.expenses) {
            // payer is owed the amount, ower is in debt for the amount
            balances.merge(expense.paidBy, expense.amount, Double::sum);
            balances.merge(expense.owedBy, -expense.amount, Double::sum);
        }
        return balances;
    }

    List<Transaction> simplifyExpenses(Integer groupId) {
        Map<User, Double> balances = getNetBalances(groupId);

        // Max-heaps: creditors keyed by how much they are owed,
        // debtors keyed by how much they owe (stored as a positive magnitude).
        PriorityQueue<Map.Entry<User, Double>> creditors =
                new PriorityQueue<>(Comparator.comparingDouble((Map.Entry<User, Double> e) -> e.getValue()).reversed());
        PriorityQueue<Map.Entry<User, Double>> debtors =
                new PriorityQueue<>(Comparator.comparingDouble((Map.Entry<User, Double> e) -> e.getValue()).reversed());

        double epsilon = 0.01; // ignore sub-cent rounding noise
        for (Map.Entry<User, Double> entry : balances.entrySet()) {
            double balance = entry.getValue();
            if (balance > epsilon) {
                creditors.add(Map.entry(entry.getKey(), balance));
            } else if (balance < -epsilon) {
                debtors.add(Map.entry(entry.getKey(), -balance)); // store positive magnitude
            }
        }

        List<Transaction> transactions = new ArrayList<>();

        // Greedily match the largest debtor with the largest creditor.
        while (!debtors.isEmpty() && !creditors.isEmpty()) {
            Map.Entry<User, Double> debtor = debtors.poll();
            Map.Entry<User, Double> creditor = creditors.poll();

            double amount = Math.min(debtor.getValue(), creditor.getValue());
            transactions.add(new Transaction(debtor.getKey(), creditor.getKey(), amount));

            double debtorRemaining = debtor.getValue() - amount;
            double creditorRemaining = creditor.getValue() - amount;

            // Whoever still has a balance goes back into the heap.
            if (debtorRemaining > epsilon) debtors.add(Map.entry(debtor.getKey(), debtorRemaining));
            if (creditorRemaining > epsilon) creditors.add(Map.entry(creditor.getKey(), creditorRemaining));
        }

        return transactions;
    }
}
