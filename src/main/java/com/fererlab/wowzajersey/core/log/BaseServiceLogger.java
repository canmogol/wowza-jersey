package com.fererlab.wowzajersey.core.log;

import java.util.logging.Logger;

public class BaseServiceLogger extends BaseLogger {

    private Logger logger = Logger.getLogger(getClass().getSimpleName());

    @Override
    protected Logger getLogger() {
        return logger;
    }

    public void emptyRunMethod(String className) {
        log("the service: " + className + " did not implement the run method, remember to start this service");
    }

    public void configure() {
        log("configuration method called");
    }
}
