package training.supportbank.model;

public class Transaction {

    private String date;
    private String narrative;
    private String fromAccount;
    private String toAccount;
    private String amount;


    public Transaction(String date, String fromAccount, String toAccount, String narrative, String amount) {
        this.date = date;
        this.narrative = narrative;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
    }

    public Transaction(String[] metadata) {
        this.date = metadata[0];
        this.narrative = metadata[3];
        this.fromAccount = metadata[1];
        this.toAccount = metadata[2];
        this.amount = metadata[4];
    }


    public String getFromAccount() {
        return fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public String getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public String getNarrative() {
        return narrative;
    }
}
