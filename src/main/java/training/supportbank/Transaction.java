package training.supportbank;

import java.time.LocalDate;

public class Transaction {

    private LocalDate date;
    private String narrative;
    private String fromAccount;
    private String toAccount;
    private double amount;


    public Transaction(LocalDate date, String fromAccount, String toAccount, String narrative, double amount) {
        this.date = date;
        this.narrative = narrative;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date.toString();
    }

    public String getNarrative() {
        return narrative;
    }
}
