package training.supportbank.validator;

import training.supportbank.model.Transaction;
import training.supportbank.exception.DateFormatException;
import training.supportbank.exception.NumberFormatException;
import training.supportbank.exception.FieldEmptyException;

import java.io.File;
import java.util.Optional;
import java.util.regex.Pattern;

public class TransactionValidator {

    private static final String DD_MM_YYYY = "([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4}";
    private static final String YYYY_MM_DD = "\\d{4}(-)(((0)[0-9])|((1)[0-2]))(-)([0-2][0-9]|(3)[0-1])";

    public static Optional<Transaction> validate(Transaction transaction, File file, int transactionNumber) {
        if (!Pattern.matches(DD_MM_YYYY + "|" + YYYY_MM_DD, transaction.getDate())) {
            throw new DateFormatException("[File: " + file.getName() + " | Transaction no: " + transactionNumber + "] - " +
                    "DateFormatException" + ": " + "Not a valid date: " + "\"" + transaction.getDate() + "\"");
        }
        if (transaction.getFromAccount().isEmpty()) {
            throw new FieldEmptyException("[File: " + file.getName() + " | Transaction no: " + transactionNumber + "] - " +
                    "FieldEmptyException" + ": " + "Column \"From\" is empty");
        }
        if (transaction.getToAccount().isEmpty()) {
            throw new FieldEmptyException("[File: " + file.getName() + " | Transaction no: " + transactionNumber + "] - " +
                    "FieldEmptyException" + ": " + "Column \"To\" is empty");
        }
        if (!Pattern.matches("[0-9]+|[0-9]*\\.[0-9]*", String.valueOf(transaction.getAmount()))) {
            throw new NumberFormatException("[File: " + file.getName() + " | Transaction no: " + transactionNumber + "] - " +
                    "NumberFormatException" + ": " + "Not a valid number: " + "\"" + transaction.getAmount() + "\"");
        }
        return Optional.of(transaction);
    }
}
