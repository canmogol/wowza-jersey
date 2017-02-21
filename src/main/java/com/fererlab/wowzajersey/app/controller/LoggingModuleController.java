package com.fererlab.wowzajersey.app.controller;

import com.fererlab.wowzajersey.core.config.Configuration;
import com.fererlab.wowzajersey.core.controller.ModuleController;
import com.fererlab.wowzajersey.app.log.LoggingModuleControllerLogger;
import com.fererlab.wowzajersey.app.model.Client;
import com.fererlab.wowzajersey.app.model.HttpSession;

public class LoggingModuleController implements ModuleController {

    private LoggingModuleControllerLogger logger = new LoggingModuleControllerLogger();

    @Override
    public boolean authorizeClient(Client client) {
        logger.authorizeClient(client);
        return true;
    }

    @Override
    public boolean authenticateHttpSession(HttpSession httpSession) {
        logger.authenticateHttpSession(httpSession);
        return true;
    }

    @Override
    public void clientConnected(Client client) {
        logger.clientConnected(client);
    }

    @Override
    public void clientDisconnected(Client client) {
        logger.clientDisconnected(client);
    }

    @Override
    public void configure(Configuration configuration) {
        logger.configure();
    }

}
