package training.supportbank.model.xml;

import training.supportbank.model.Transaction;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@XmlRootElement(name = "TransactionList")
@XmlAccessorType(XmlAccessType.FIELD)
public class TransactionList {


    @XmlElement(name = "SupportTransaction")
    private List<SupportTransaction> transactionList;

    public TransactionList() {
    }

    public TransactionList(List<SupportTransaction> transactionList) {
        this.transactionList = transactionList;
    }


    public List<SupportTransaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<SupportTransaction> transactionList) {
        this.transactionList = transactionList;
    }

    public List<Transaction> transformToStandardTransactionData() {
        List<Transaction> standardTransactionList = new ArrayList<>();
        for (SupportTransaction supportTransaction : this.transactionList) {
            standardTransactionList.add(new Transaction(supportTransaction.getDate(),
                    supportTransaction.getParties().getFrom(),
                    supportTransaction.getParties().getTo(),
                    supportTransaction.getDescription(),
                    supportTransaction.getValue()));
        }
        return standardTransactionList;
    }
}