package training.supportbank.parser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import training.supportbank.model.Transaction;
import training.supportbank.exception.DateFormatException;
import training.supportbank.exception.FieldEmptyException;
import training.supportbank.logger.Logger;
import training.supportbank.validator.TransactionValidator;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JsonParser implements Parser {
    Gson gson;

    public JsonParser() {
        this.gson = new Gson();
    }

    @Override
    public List<Transaction> parseFile(File file) throws IOException {
        List<Transaction> validTransactionList = new ArrayList<>();
        Type transactionListType = new TypeToken<ArrayList<Transaction>>() {
        }.getType();
        List<Transaction> transactions = gson.fromJson(new JsonReader(new FileReader(file.getAbsolutePath())), transactionListType);
        Optional<Transaction> transactionsToBeValidated = Optional.empty();
        int transactionNumber = 1;
        for (Transaction transaction : transactions) {
            try {
                transactionsToBeValidated = TransactionValidator.validate(transaction, file, transactionNumber);
            } catch (DateFormatException | FieldEmptyException | NumberFormatException e) {
                Logger.debug(e.getMessage());
            }
            transactionsToBeValidated.ifPresent(validTransactionList::add);
            transactionNumber++;
        }

        return validTransactionList;
    }
}
