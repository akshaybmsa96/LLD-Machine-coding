package questions.splitwise;

import java.util.List;

public class SplitWiseMain {
    public static void main(String[] args) {
        SplitWiseManager manager = new SplitWiseManager();

        // Users
        User alice = manager.addUser("Alice", "alice@example.com");
        User bob   = manager.addUser("Bob",   "bob@example.com");
        User carol = manager.addUser("Carol", "carol@example.com");
        User dave  = manager.addUser("Dave",  "dave@example.com");

        // Group
        Group trip = manager.createGroup("Goa Trip", List.of(alice, bob, carol, dave));

        // Expense 1: Alice paid 300 on Bob's behalf
        manager.addExpense(trip.id, "Hotel", 300, alice, bob);

        // Expense 2: Bob paid 200 on Carol's behalf
        manager.addExpense(trip.id, "Food", 200, bob, carol);

        // Expense 3: Carol paid 150 on Dave's behalf
        manager.addExpense(trip.id, "Transport", 150, carol, dave);

        // Expense 4: Dave paid 100 on Alice's behalf
        manager.addExpense(trip.id, "Taxi", 100, dave, alice);

        // Preview net balances before simplification
        System.out.println("--- Net Balances ---");
        manager.getNetBalances(trip.id)
               .forEach((user, balance) ->
                       System.out.printf("  %-6s: %.2f%n", user.name, balance));

        // Simplify
        System.out.println("\n--- Simplified Transactions ---");
        List<Transaction> transactions = manager.simplifyExpenses(trip.id);
        transactions.forEach(System.out::println);
    }
}
