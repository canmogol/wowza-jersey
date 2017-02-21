package com.fererlab.wowzajersey.app.log;

import java.util.logging.Logger;

public class RWSModuleControllerLogger extends LoggingModuleControllerLogger {

    private Logger logger = Logger.getLogger(getClass().getSimpleName());

    @Override
    protected Logger getLogger() {
        return logger;
    }

    public void isAuthorized(boolean isAuthorized) {
        log("authorization service response,  isAuthorized: " + isAuthorized);
    }

    public void isAuthenticated(boolean isAuthenticated) {
        log("authentication service response,  isAuthenticated: " + isAuthenticated);
    }

}
