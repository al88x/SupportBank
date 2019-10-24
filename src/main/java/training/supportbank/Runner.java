package training.supportbank;

import training.supportbank.printer.Printer;
import training.supportbank.printer.TerminalPrinter;

import javax.security.auth.login.AccountNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Runner {


    private static final String PATTERN_LIST_ACCOUNT = "-list\\[([a-zA-Z]+ [a-zA-Z]+)\\]";
    private static final String LIST_ALL = "--list-all";
    private Bank bank;
    private Printer printer;

    public Runner(String[] args) throws IOException {
        this.bank = new Bank();
        Pattern patternListAccount = Pattern.compile(PATTERN_LIST_ACCOUNT);
        printer = new TerminalPrinter();

        parseFileAndCreateTransactions(args[0]);
        if (args[1].equals(LIST_ALL)) {
            printer.listAll(bank.getAccountList());
        } else if (patternListAccount.matcher(args[1]).matches()) {
            try {
                Matcher matcher = patternListAccount.matcher(args[1]);
                matcher.find();
                printer.listAccountTransactions(this.getAccount(matcher.group(1)));
            } catch (AccountNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private Account getAccount(String name) throws AccountNotFoundException {
        if (bank.getAccountList().get(name) == null) {
            throw new AccountNotFoundException("Account with current name not found");
        }
        return bank.getAccountList().get(name);
    }

    private void parseFileAndCreateTransactions(String arg) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(arg));
        String line = "";
        while ((line = reader.readLine()) != null) {
            bank.processTransaction(line.split(","));

        }
    }
}
