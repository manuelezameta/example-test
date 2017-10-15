package com.test.testproject.util;

import com.test.testproject.newimpl.JobLoggerNew;
import com.test.testproject.domain.LogConfiguration;
import org.testng.annotations.Test;

/**
 *
 * @author ElMaNu
 */
public class JobLoggerNewTest {

    private final static String INVALID_MSG = "Message must be enter";
    private final static String CONFIG_NULL = "Configuration must not be null";
    private final static String INVALID_CONFIG = "Invalid configuration";
    private final static String LOGGER_TYPE_ERROR = "Error or Warning or Message must be specified";

    /**
     * Test of logMessage method setting console configuration.
     */
    @Test
    public void testLogMessageLogConsoleConfigSet() throws Exception {
        System.out.println("logMessage");
        String messageText = "Message Text";
        LogConfiguration configuration = new LogConfiguration();
        configuration.setLogToConsole(true);
        configuration.setLogMessage(true);
        JobLoggerNew.logMessage(messageText, configuration);
    }

    /**
     * Test of logMessage method setting file configuration.
     */
    @Test
    public void testLogMessageLogFileConfigSet() throws Exception {
        System.out.println("logMessage");
        String messageText = "Message Text Test";
        LogConfiguration configuration = new LogConfiguration();
        configuration.setLogToFile(true);
        configuration.setLogMessage(true);
        JobLoggerNew.logMessage(messageText, configuration);
    }

    /**
     * Test of logMessage method setting Db configuration. Before to enable it,
     * the schema and the table must be created first.
     */
    @Test(enabled = false)
    public void testLogMessageLogDbConfigSet() throws Exception {
        System.out.println("logMessage");
        String messageText = "Message Text Db";
        LogConfiguration configuration = new LogConfiguration();
        configuration.setLogToDatabase(true);
        configuration.setLogMessage(true);
        JobLoggerNew.logMessage(messageText, configuration);
    }

    /**
     * Test of logMessage method when messageText is empty.
     */
    @Test(expectedExceptions = Exception.class, expectedExceptionsMessageRegExp = INVALID_MSG)
    public void testLogMessageEmptyMessage() throws Exception {
        System.out.println("logMessage");
        String messageText = "";
        LogConfiguration configuration = null;
        JobLoggerNew.logMessage(messageText, configuration);

    }

    /**
     * Test of logMessage method when configuration is null.
     */
    @Test(expectedExceptions = Exception.class, expectedExceptionsMessageRegExp = CONFIG_NULL)
    public void testLogMessageConfigurationNull() throws Exception {
        System.out.println("logMessage");
        String messageText = "Message Text";
        LogConfiguration configuration = null;
        JobLoggerNew.logMessage(messageText, configuration);

    }

    /**
     * Test of logMessage method when configuration is not set.
     */
    @Test(expectedExceptions = Exception.class, expectedExceptionsMessageRegExp = INVALID_CONFIG)
    public void testLogMessageConfigurationNotSet() throws Exception {
        System.out.println("logMessage");
        String messageText = "Message Text";
        LogConfiguration configuration = new LogConfiguration();
        JobLoggerNew.logMessage(messageText, configuration);
    }

    /**
     * Test of logMessage method when logger type is not set.
     */
    @Test(expectedExceptions = Exception.class, expectedExceptionsMessageRegExp = LOGGER_TYPE_ERROR)
    public void testLogMessageLogTypeNotSet() throws Exception {
        System.out.println("logMessage");
        String messageText = "Message Text";
        LogConfiguration configuration = new LogConfiguration();
        configuration.setLogToConsole(true);
        JobLoggerNew.logMessage(messageText, configuration);
    }

}
