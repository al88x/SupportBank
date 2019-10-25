package training.supportbank.parser;

import training.supportbank.model.Transaction;
import training.supportbank.exception.DateFormatException;
import training.supportbank.exception.FieldEmptyException;
import training.supportbank.exception.NumberFormatException;
import training.supportbank.logger.Logger;
import training.supportbank.validator.TransactionValidator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CsvParser implements Parser {

    @Override
    public List<Transaction> parseFile(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = "";
        int lineNumber = 1;
        String[] metadata;
        List<Transaction> transactionList = new ArrayList<>();
        Optional<Transaction> validatedTransactions = Optional.empty();
        while ((line = reader.readLine()) != null) {
            metadata = line.split(",");
            try {
                validatedTransactions  = TransactionValidator.validate(new Transaction(metadata), file, lineNumber);
            } catch (DateFormatException | FieldEmptyException | NumberFormatException e) {
                Logger.debug(e.getMessage());
            }

            validatedTransactions.ifPresent(transactionList::add);
            lineNumber++;
        }
        return transactionList;
    }
}
