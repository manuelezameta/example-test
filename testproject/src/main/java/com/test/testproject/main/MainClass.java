package com.test.testproject.main;

import com.test.testproject.domain.LogConfiguration;
import com.test.testproject.newimpl.JobLoggerNew;

/**
 *
 * @author Manuel Lezameta
 */
public class MainClass {
    
    public static void main(String[] args) throws Exception {
        LogConfiguration configuration = new LogConfiguration();
        configuration.setLogError(true);
        configuration.setLogToConsole(true);
        
        String messageText = "Message text";
        
        JobLoggerNew.logMessage(messageText, configuration);
    }
}
