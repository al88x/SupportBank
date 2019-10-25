package training.supportbank.parser;

import training.supportbank.model.Transaction;
import training.supportbank.model.xml.TransactionList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class XmlParser implements Parser {
    @Override
    public List<Transaction> parseFile(File file) throws IOException, JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(TransactionList.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        TransactionList xmlTransactions = (TransactionList) jaxbUnmarshaller.unmarshal(new FileReader(file));

        return xmlTransactions.transformToStandardTransactionData();
    }
}
