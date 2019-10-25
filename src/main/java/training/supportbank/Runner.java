package training.supportbank;

import org.apache.commons.io.FilenameUtils;
import training.supportbank.exception.AccountNotFoundException;
import training.supportbank.exception.ExtensionNotSuportedException;
import training.supportbank.logger.Logger;
import training.supportbank.model.Account;
import training.supportbank.model.Transaction;
import training.supportbank.parser.CsvParser;
import training.supportbank.parser.JsonParser;
import training.supportbank.parser.Parser;
import training.supportbank.parser.XmlParser;
import training.supportbank.printer.Printer;
import training.supportbank.printer.TerminalPrinter;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Runner {

    private static final String PATTERN_LIST_ACCOUNT = "-list\\[([a-zA-Z]+ [a-zA-Z]+)\\]";
    private static final String LIST_ALL = "--list-all";
    private Bank bank;
    private Parser parser;
    private Printer printer;


    public Runner(String[] args) throws IOException, JAXBException {
        this.bank = new Bank();
        this.printer = new TerminalPrinter();
        File fileToParse = new File(args[0]);

        setParserToFileType(fileToParse);

        if (parser != null) {
            List<Transaction> transactionList = parser.parseFile(fileToParse);
            bank.processTransactions(transactionList);
        } else {
            throw new ExtensionNotSuportedException("[File: " + fileToParse.getName() + "]" + " Not supported");
        }

        setListType(args[1]);


    }

    private void setListType(String arg) {
        Pattern patternListAccount = Pattern.compile(PATTERN_LIST_ACCOUNT);
        if (arg.equals(LIST_ALL)) {
            printer.listAll(bank.getAccountList());
        } else if (patternListAccount.matcher(arg).matches()) {
            try {
                Matcher matcher = patternListAccount.matcher(arg);
                matcher.find();
                printer.listAccountTransactions(this.getAccount(matcher.group(1)));
            } catch (AccountNotFoundException e) {
                Logger.debug("[listAccountByName]", e);
            }
        }
    }

    private void setParserToFileType(File file) {
        if (FilenameUtils.getExtension(file.getName()).equals("csv")) {
            parser = new CsvParser();
        } else if (FilenameUtils.getExtension(file.getName()).equals("json")) {
            parser = new JsonParser();
        } else if (FilenameUtils.getExtension(file.getName()).equals("xml")) {
            parser = new XmlParser();
        }
    }

    private Account getAccount(String name) throws AccountNotFoundException {
        if (bank.getAccountList().get(name) == null) {
            throw new AccountNotFoundException("Account with current name not found");
        }
        return bank.getAccountList().get(name);
    }
}
