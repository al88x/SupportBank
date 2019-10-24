package training.supportbank;

public class Transaction {

    private String date;
    private String narrative;
    private String fromAccount;
    private String toAccount;
    private double amount;


    public Transaction(String date, String fromAccount, String toAccount, String narrative, double amount) {
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
        return date;
    }

    public String getNarrative() {
        return narrative;
    }
}
