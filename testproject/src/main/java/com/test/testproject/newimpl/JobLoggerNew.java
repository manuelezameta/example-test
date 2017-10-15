package com.test.testproject.newimpl;

import com.test.testproject.domain.LogConfiguration;
import com.test.testproject.domain.TypeEnum;
import com.test.testproject.util.ConnectionFactory;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Manuel Lezameta
 */
public final class JobLoggerNew {

    private final static Logger LOGGER = Logger.getLogger("MyLog");
    private final static String INVALID_MSG = "Message must be enter";
    private final static String CONFIG_NULL = "Configuration must not be null";
    private final static String INVALID_CONFIG = "Invalid configuration";
    private final static String LOGGER_TYPE_ERROR = "Error or Warning or Message must be specified";

    private static final String FILE_PATH = System.getProperty("user.home");
    private final static String FILE_NAME = "logFile.txt";

    private final static String LOG_TYPE_MESSAGE = "message ";
    private final static String LOG_TYPE_ERROR = "error ";
    private final static String LOG_TYPE_WARN = "warning ";

    private JobLoggerNew() {
    }

    /**
     * This method will log the messageText according the configuration set.
     *
     * @param messageText
     * @param configuration
     * @throws Exception
     *
     */
    public static void logMessage(String messageText, LogConfiguration configuration) throws Exception {

        if (messageText == null || messageText.trim().length() == 0) {
            throw new Exception(INVALID_MSG);
        }

        if (configuration == null) {
            throw new Exception(CONFIG_NULL);
        }

        if (!configuration.isLogToConsole()
                && !configuration.isLogToFile()
                && !configuration.isLogToDatabase()) {
            throw new Exception(INVALID_CONFIG);
        }

        if (!configuration.isLogMessage()
                && !configuration.isLogWarning()
                && !configuration.isLogError()) {
            throw new Exception(LOGGER_TYPE_ERROR);
        }

        String logType = "";

        if (configuration.isLogMessage()) {
            logType = LOG_TYPE_MESSAGE;
        } else if (configuration.isLogError()) {
            logType = LOG_TYPE_ERROR;
        } else if (configuration.isLogWarning()) {
            logType = LOG_TYPE_WARN;
        }

        String line = getLine(logType, messageText);

        if (configuration.isLogToConsole()) {
            logInConsole(line);
        } else if (configuration.isLogToFile()) {
            logInFile(line);
        } else if (configuration.isLogToDatabase()) {
            logInDb(configuration, line);
        }
    }

    /**
     * Method to get the text with format
     *
     * @param logType
     * @param messageText
     */
    private static String getLine(String logType, String messageText) {
        return logType
                + DateFormat.getDateInstance(DateFormat.LONG).format(new Date())
                + " " + messageText;
    }

    /**
     * Method to log the message into console
     *
     * @param line text to log
     */
    private static void logInConsole(String line) {
        LOGGER.addHandler(new ConsoleHandler());
        LOGGER.log(Level.INFO, line);
    }

    /**
     * Method to log the message into file
     *
     * @param line text to log
     */
    private static void logInFile(String line) throws IOException {
        String fileLocation = FILE_PATH + File.separator + FILE_NAME;
        System.out.println("" + fileLocation);
        File logFile = new File(fileLocation);

        if (!logFile.exists()) {
            logFile.createNewFile();
        }

        FileHandler fileHandler = new FileHandler(fileLocation);
        LOGGER.addHandler(fileHandler);
        LOGGER.log(Level.INFO, line);
    }

    /**
     * Method to log the message into database
     *
     * @param configuration configuration set
     * @param line text to log
     */
    private static void logInDb(LogConfiguration configuration, String line) {
        int type = 0;

        if (configuration.isLogMessage()) {
            type = TypeEnum.MESSAGE.getValue();
        } else if (configuration.isLogError()) {
            type = TypeEnum.ERROR.getValue();
        } else if (configuration.isLogWarning()) {
            type = TypeEnum.WARN.getValue();
        }

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = null;

        try {
            String sql = "insert into Log_Values(message,type) values (?,?)";

            statement = connection.prepareStatement(sql);
            statement.setString(1, line);
            statement.setInt(2, type);

            statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
