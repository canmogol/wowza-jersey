package com.fererlab.wowzajersey.app.log;

import com.fererlab.wowzajersey.core.log.BaseLogger;

import java.util.logging.Logger;

public class LoggingClientControllerLogger extends BaseLogger {

    private Logger logger = Logger.getLogger(getClass().getSimpleName());

    @Override
    protected Logger getLogger() {
        return logger;
    }

    public void configure() {
        log("configuration method called");
    }

    public void disconnectClient(String uniqueId) {
        log("disconnect method called for uniqueId: " + uniqueId);
    }
}
