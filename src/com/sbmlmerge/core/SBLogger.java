package com.sbmlmerge.core;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class SBLogger {
    private FileHandler fh;
    private static Logger logger;
    
    public SBLogger(String logFilePath) {
        try {
            // This block configure the logger with ch and formatter
            fh = new FileHandler(logFilePath);
            logger = Logger.getLogger("MergeSBML");
            logger.addHandler(fh);
            for (Handler handler : logger.getHandlers()) {
                handler.setFormatter(new Formatter() {
                    @Override
                    public String format(LogRecord record) {
                        return record.getMessage() + "\n";
                    }
                });
            }
            for (Handler handler : logger.getParent().getHandlers()) {
                handler.setFormatter(new Formatter() {
                    @Override
                    public String format(final LogRecord record) {
                        return record.getMessage() + "\n";
                    }
                });
            }
        } catch (IOException | SecurityException e) {
            logger.info(e.getMessage());
        }
    }
    
    public String info(String log){
        logger.info(log);
        return log;
    }
    
    public String separator(){
        String sepLine =  "-----------------------------------------------------"
                        + "-----------------------------------------------------";
        logger.info(sepLine);
        return sepLine;
    }
}