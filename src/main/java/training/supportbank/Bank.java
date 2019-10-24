package training.supportbank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import training.supportbank.exception.DateFormatException;
import training.supportbank.exception.FieldEmptyException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;


public class Bank {
    private static int DATE = 0;
    private static int FROM = 1;
    private static int TO = 2;
    private static int NARRATIVE = 3;
    private static int AMOUNT = 4;
    private Map<String, Account> accountList;
    private List<Transaction> transactionList;

    private static final Logger LOGGER = LogManager.getLogger();

    public Bank() {
        this.accountList = new HashMap<>();
        this.transactionList = new ArrayList<>();
    }

    public void processTransaction(String[] transactionMetadata) throws DateFormatException, FieldEmptyException, NumberFormatException {
        LocalDate date;
        try {
            date = LocalDate.parse(transactionMetadata[DATE], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeParseException e) {
            throw new DateFormatException("Not a valid date: " + "\"" + transactionMetadata[DATE] + "\"");
        }
        if (transactionMetadata[FROM].isEmpty()) {
            throw new FieldEmptyException("Column \"From\" is empty");
        }
        if (transactionMetadata[TO].isEmpty()) {
            throw new FieldEmptyException("Column \"To\" is empty");
        }
        if (!Pattern.matches("[0-9]+|[0-9]*\\.[0-9]*", transactionMetadata[AMOUNT])) {
            throw new NumberFormatException("Not a valid number: " + "\"" + transactionMetadata[AMOUNT] + "\"");
        }
        Transaction transaction = new Transaction(
                date,
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
