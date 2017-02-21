package com.fererlab.wowzajersey.core.log;

import java.util.logging.Logger;

public class ControllerCreatorLogger extends BaseLogger {

    private Logger logger = Logger.getLogger(getClass().getSimpleName());

    @Override
    protected Logger getLogger() {
        return logger;
    }

    public void couldNotCreateController(String controllerName, String errorMessage) {
        error("Could not create controller: " + controllerName + " exception: " + errorMessage);
    }

}
