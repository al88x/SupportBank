package training.supportbank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.security.auth.login.AccountNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String args[]) throws IOException {

        Runner runner = new Runner(args);
    }
}
