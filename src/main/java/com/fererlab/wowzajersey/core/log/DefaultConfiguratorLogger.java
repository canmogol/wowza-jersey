package com.fererlab.wowzajersey.core.log;

import java.util.logging.Logger;

public class DefaultConfiguratorLogger extends BaseLogger {

    private Logger logger = Logger.getLogger(getClass().getSimpleName());

    @Override
    protected Logger getLogger() {
        return logger;
    }

    public void couldNotReadConfiguration(String errorMessage) {
        error("Could not read configuration, exception: " + errorMessage);
    }

    public void willLoadConfigurationFile(String configurationFile) {
        log("will load configuration file: " + configurationFile);
    }
}
