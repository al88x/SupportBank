package training.supportbank;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private String name;
    private List<Transaction> transactions;


    public Account(String name) {
        this.name = name;
        this.transactions = new ArrayList<>();
    }


    public void processAccountTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        double balance = 0;
        for (Transaction transaction : transactions) {
            if (transaction.getFromAccount().equals(this.name)) {
                balance -= transaction.getAmount();
            } else {
                balance += transaction.getAmount();
            }
        }
        return balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
