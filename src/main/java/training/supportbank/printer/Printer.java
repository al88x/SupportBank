package training.supportbank.printer;

import training.supportbank.Account;

import java.util.Map;

public interface Printer {
    void listAll(Map<String, Account> accountList);
    void listAccountTransactions(Account account);
}
