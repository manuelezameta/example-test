/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.testproject.old;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

//doesn't have javadoc to know what this class will be used for
public class JobLogger {

    //From my point of view, all these variables are constants and 
    //their names don't accomplish Java naming conventions
    //I will create a pojo with this variables
    /**/
    private static boolean logToFile;
    private static boolean logToConsole;
    private static boolean logMessage;
    private static boolean logWarning;
    private static boolean logError;
    private static boolean logToDatabase;
    /**/

    private boolean initialized; // attribute never used
    private static Map dbParams;
    private static Logger logger;

    //the constants must be initialize at the begining of the class.
    //However, if these variable are considered as attributes, the static part must be remove.
    //in order to have this constructor.
    public JobLogger(boolean logToFileParam, boolean logToConsoleParam, boolean logToDatabaseParam,
            boolean logMessageParam, boolean logWarningParam, boolean logErrorParam,
            Map dbParamsMap) {
        logger = Logger.getLogger("MyLog");
        logError = logErrorParam;
        logMessage = logMessageParam;
        logWarning = logWarningParam;
        logToDatabase = logToDatabaseParam;
        logToFile = logToFileParam;
        logToConsole = logToConsoleParam;
        dbParams = dbParamsMap;
    }

    //method name doesn't accomplish java naming conventions
    //it should be in cammel case
    //static should be revomed because the initialization must be done before call this method.
    public static void LogMessage(String messageText, boolean message, boolean warning, boolean error) throws Exception {
        messageText.trim(); //this part will fail because it doesn't validate if the variable is null or not

        if (messageText == null || messageText.length() == 0) {
            return;
        }

        if (!logToConsole && !logToFile && !logToDatabase) {
            throw new Exception("Invalid configuration");
        }

        //from my point of view, this validation is redundant.
        //must be one of them
        if ((!logError && !logMessage && !logWarning) || (!message && !warning && !error)) {
            throw new Exception("Error or Warning or Message must be specified");
        }

        //All this part can be separated in another method and 
        //get the connection when the log is going to save into DB
        Connection connection = null;
        //These values can be set in constants or in a property file
        Properties connectionProps = new Properties();
        connectionProps.put("user", dbParams.get("userName"));
        connectionProps.put("password", dbParams.get("password"));
        connection = DriverManager.getConnection("jdbc:" + dbParams.get("dbms") + "://" + dbParams.get("serverName")
                + ":" + dbParams.get("portNumber") + "/", connectionProps);

        //what does "t" means? I think it must have better name like "type"
        int t = 0;

        //I think it must be preferable to use ENUM
        if (message && logMessage) {
            t = 1;
        }
        if (error && logError) {
            t = 2;
        }
        if (warning && logWarning) {
            t = 3;
        }

        //I should consider to use prepareStament to prevent sql injection attacks
        Statement stmt = connection.createStatement();

        //what "l" means? it could be like "line"
        String l = null;

        //name of the log could be gotten from a constant variable
        File logFile = new File(dbParams.get("logFileFolder") + "/logFile.txt");

        if (!logFile.exists()) {
            logFile.createNewFile();
        }

        //name of these varibles must be improved.
        FileHandler fh = new FileHandler(dbParams.get("logFileFolder") + "/logFile.txt");
        ConsoleHandler ch = new ConsoleHandler();

        /* This block is useless. "l" variable is never used after the value assignation.
         * I think "l" value must be place in the log method instead of "messageText" 
         */
        if (error && logError) {
            l = l + "error " + DateFormat.getDateInstance(DateFormat.LONG).format(new Date())
                    + messageText;
        }
        if (warning && logWarning) {
            l = l + "warning " + DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + messageText;
        }
        if (message && logMessage) {
            l = l + "message " + DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + messageText;
        }

        if (logToFile) {
            logger.addHandler(fh);
            logger.log(Level.INFO, messageText);
        }
        if (logToConsole) {
            logger.addHandler(ch);
            logger.log(Level.INFO, messageText);
        }
        if (logToDatabase) {
            //perhaps the execution command could be set in a variable
            stmt.executeUpdate("insert into Log_Values('" + message + "', " + String.valueOf(t) + ")");
        }
    }
}
