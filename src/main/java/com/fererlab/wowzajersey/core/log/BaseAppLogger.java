package com.fererlab.wowzajersey.core.log;

import java.util.logging.Logger;

public class BaseAppLogger extends BaseLogger {

    private Logger logger = Logger.getLogger(getClass().getSimpleName());

    @Override
    protected Logger getLogger() {
        return logger;
    }

    public void init() {
        log("will init application");
    }

    public void start() {
        log("will start application");
    }

    public void stop() {
        log("will stop application");
    }
}
