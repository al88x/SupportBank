package training.supportbank.printer;

import training.supportbank.model.Account;

import java.util.Map;

public interface Printer {
    void listAll(Map<String, Account> accountList);

    void listAccountTransactions(Account account);
}
