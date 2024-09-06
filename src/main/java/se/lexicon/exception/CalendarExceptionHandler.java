package se.lexicon.exception;

import se.lexicon.util.ConsoleColors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalendarExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(CalendarExceptionHandler.class);

    public static void handleException(Exception exception) {
        if (exception instanceof AuthenticationFailedException) {
            System.out.println(ConsoleColors.YELLOW + exception.getMessage() + ConsoleColors.RESET);
            logger.warn("Authentication failed: {}", exception.getMessage());
        } else if (exception instanceof UserExpiredException) {
            System.out.println(ConsoleColors.RED + exception.getMessage() + ConsoleColors.RESET);
            logger.error("User expired: {}", exception.getMessage());
        } else if (exception instanceof DBConnectionException) {
            System.out.println(ConsoleColors.ORANGE + exception.getMessage() + ConsoleColors.RESET);
            logger.error("Database connection error: {}", exception.getMessage());
        } else if (exception instanceof MySQLException) {
            System.out.println(ConsoleColors.RED + exception.getMessage() + ConsoleColors.RESET);
            logger.error("MySQL error: {}", exception.getMessage());
        } else {
            System.out.println(ConsoleColors.RED + "An unexpected exception occurred." + ConsoleColors.RESET);
            exception.printStackTrace();
            logger.error("Unexpected error", exception);
        }
    }
}
