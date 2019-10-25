package training.supportbank;

import training.supportbank.model.Account;
import training.supportbank.model.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Bank {
    private Map<String, Account> accountList;
    private List<Transaction> transactionList;


    public Bank() {
        this.accountList = new HashMap<>();
        this.transactionList = new ArrayList<>();
    }


    public void processTransactions(List<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            transactionList.add(transaction);
            updateAccounts(transaction);
        }
    }

    private void updateAccounts(Transaction transaction) {
        Account fromAccount = getAccountByName(transaction.getFromAccount());
        Account toAccount = getAccountByName(transaction.getToAccount());

        fromAccount.processAccountTransaction(transaction);
        toAccount.processAccountTransaction(transaction);
    }

    private Account getAccountByName(String name) {
        if (accountList.get(name) == null) {
            accountList.put(name, new Account(name));
        }
        return accountList.get(name);
    }

    public Map<String, Account> getAccountList() {
        return accountList;
    }
}
