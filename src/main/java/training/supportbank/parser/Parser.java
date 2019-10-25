package training.supportbank.parser;

import training.supportbank.model.Transaction;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public interface Parser {
    List<Transaction> parseFile(File file) throws IOException, JAXBException;
}
