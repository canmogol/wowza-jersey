package com.fererlab.wowzajersey.core.log;

import java.util.logging.Logger;

public class ServiceCreatorLogger extends BaseLogger {

    private Logger logger = Logger.getLogger(getClass().getSimpleName());

    @Override
    protected Logger getLogger() {
        return logger;
    }

    public void couldNotCreateService(String serviceName, String errorMessage) {
        error("Could not create service: " + serviceName + " exception: " + errorMessage);
    }

}
