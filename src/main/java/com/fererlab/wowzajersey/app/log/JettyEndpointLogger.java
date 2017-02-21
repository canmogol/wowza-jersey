package com.fererlab.wowzajersey.app.log;

import com.fererlab.wowzajersey.core.log.BaseLogger;

import java.util.logging.Logger;

public class JettyEndpointLogger extends BaseLogger {

    private Logger logger = Logger.getLogger(getClass().getSimpleName());

    @Override
    protected Logger getLogger() {
        return logger;
    }

    public void couldNotStartJoin(String errorMessage) {
        error("Could not start/join jetty, exception: " + errorMessage);
    }

    public void couldNotStop(String errorMessage) {
        error("Could not stop jetty, exception: " + errorMessage);
    }
}
