package training.supportbank.printer;

import training.supportbank.Account;
import training.supportbank.Transaction;

import java.util.Map;

public class TerminalPrinter implements Printer {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    private static final String ANSI_BLACK_TEXT = "\u001B[30m";
    private static final String ANSI_GREEN_TEXT = "\u001B[92m";
    private static final String ANSI_RED_TEXT = "\u001B[91m";
    private static final String WIDTH_DATE = "|%-10s|";
    private static final String WIDTH_DESCRIPTION = "|%-40s|";
    private static final String WIDTH_MONEY = "|%-11s|";
    private static final String WIDTH_RECIPIENT = "|%-25s|";
    private static final String WIDTH_MONEY_DECIMAL = "|£%-10.2f|";
    private static final String SEPARATOR = "----------------------------------------------------" +
            "-------------------------------------------------------";
    public static final String WIDTH_COLUMN = "|%-12s";

    @Override
    public void listAll(Map<String, Account> accountList) {
        System.out.println();
        System.out.printf(ANSI_WHITE_BACKGROUND + ANSI_BLACK_TEXT + WIDTH_COLUMN, "Name");
        System.out.printf(WIDTH_COLUMN + " |", "Balance");
        System.out.printf(ANSI_RESET + "%n");

        for (Map.Entry<String, Account> entry : accountList.entrySet()) {
            System.out.printf("|%-12s|", entry.getValue().getName());
            System.out.printf("£%-12.2f| %n", entry.getValue().getBalance());
        }
    }

    @Override
    public void listAccountTransactions(Account account) {
        System.out.println();
        System.out.printf(ANSI_WHITE_BACKGROUND + ANSI_BLACK_TEXT + "%-51s", "Name: " + account.getName());
        System.out.printf(ANSI_WHITE_BACKGROUND + ANSI_BLACK_TEXT + "%51s" + "%.2f", "Balance: £", account.getBalance());
        System.out.printf(ANSI_RESET + "%n");
        System.out.println();
        System.out.println("TRANSACTIONS");
        System.out.printf(WIDTH_DATE, "Date");
        System.out.printf(WIDTH_DESCRIPTION, "Description");
        System.out.printf(WIDTH_RECIPIENT, "Recipient");
        System.out.printf(ANSI_GREEN_TEXT + WIDTH_MONEY, "Money In");
        System.out.printf(ANSI_RED_TEXT + WIDTH_MONEY, "Money Out");
        System.out.printf(ANSI_RESET + "%n");

        System.out.println(SEPARATOR);
        for (Transaction transaction : account.getTransactions()) {


            System.out.printf(WIDTH_DATE, transaction.getDate());
            System.out.printf(WIDTH_DESCRIPTION, transaction.getNarrative());

            if (account.getName().equals(transaction.getFromAccount())) {
                System.out.printf(WIDTH_RECIPIENT, "paid to " + transaction.getToAccount());
            } else {
                System.out.printf(WIDTH_RECIPIENT, "received from " + transaction.getFromAccount());
            }


            if (account.getName().equals(transaction.getFromAccount())) {
                System.out.printf(ANSI_GREEN_TEXT + WIDTH_MONEY, "");
            } else {
                System.out.printf(ANSI_GREEN_TEXT + WIDTH_MONEY_DECIMAL, transaction.getAmount());
            }
            if (account.getName().equals(transaction.getToAccount())) {
                System.out.printf(ANSI_RED_TEXT + WIDTH_MONEY, "");
            } else {
                System.out.printf(ANSI_RED_TEXT + WIDTH_MONEY_DECIMAL, transaction.getAmount());
            }
            System.out.printf(ANSI_RESET + "%n");
        }
    }
}
