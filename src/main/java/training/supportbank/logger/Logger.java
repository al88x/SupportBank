package training.supportbank.logger;

import org.apache.logging.log4j.LogManager;


public class Logger {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();

    public static void debug(String methodName, RuntimeException e) {
        LOGGER.debug("[" + methodName + "]: " + e.getMessage());
    }

    public static void debug(String message) {
        LOGGER.debug(message);
    }
}
