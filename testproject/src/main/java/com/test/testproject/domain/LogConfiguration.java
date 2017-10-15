package com.test.testproject.domain;

/**
 *
 * @author Manuel Lezameta
 *
 * This class will store the initial configuration of the log
 */
public class LogConfiguration {

    private boolean logToFile;
    private boolean logToConsole;
    private boolean logToDatabase;

    private boolean logMessage;
    private boolean logWarning;
    private boolean logError;

    public boolean isLogToFile() {
        return logToFile;
    }

    public void setLogToFile(boolean logToFile) {
        this.logToFile = logToFile;
    }

    public boolean isLogToConsole() {
        return logToConsole;
    }

    public void setLogToConsole(boolean logToConsole) {
        this.logToConsole = logToConsole;
    }

    public boolean isLogMessage() {
        return logMessage;
    }

    public void setLogMessage(boolean logMessage) {
        this.logMessage = logMessage;
    }

    public boolean isLogWarning() {
        return logWarning;
    }

    public void setLogWarning(boolean logWarning) {
        this.logWarning = logWarning;
    }

    public boolean isLogError() {
        return logError;
    }

    public void setLogError(boolean logError) {
        this.logError = logError;
    }

    public boolean isLogToDatabase() {
        return logToDatabase;
    }

    public void setLogToDatabase(boolean logToDatabase) {
        this.logToDatabase = logToDatabase;
    }
}
