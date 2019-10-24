package training.supportbank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Bank {
    private static int DATE = 0;
    private static int FROM = 1;
    private static int TO = 2;
    private static int NARRATIVE = 3;
    private static int AMOUNT = 4;
    private Map<String, Account> accountList;
    private List<Transaction> transactionList;


    public Bank() {
        this.accountList = new HashMap<>();
        this.transactionList = new ArrayList<>();
    }

    public void processTransaction(String[] transactionMetadata) {
        Transaction transaction = new Transaction(
                transactionMetadata[DATE],
                transactionMetadata[FROM],
                transactionMetadata[TO],
                transactionMetadata[NARRATIVE],
                Double.parseDouble(transactionMetadata[AMOUNT]));

        transactionList.add(transaction);
        updateAccounts(transaction);
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
