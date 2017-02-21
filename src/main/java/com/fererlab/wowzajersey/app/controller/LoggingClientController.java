package com.fererlab.wowzajersey.app.controller;

import com.fererlab.wowzajersey.app.log.LoggingClientControllerLogger;
import com.fererlab.wowzajersey.core.config.Configuration;
import com.fererlab.wowzajersey.core.controller.ClientController;

/**
 * logger implementation
 */
public class LoggingClientController implements ClientController {

    LoggingClientControllerLogger logger = new LoggingClientControllerLogger();

    @Override
    public boolean disconnectClient(String uniqueId) {
        logger.disconnectClient(uniqueId);
        return true;
    }

    @Override
    public void configure(Configuration configuration) {
        logger.configure();
    }
}
