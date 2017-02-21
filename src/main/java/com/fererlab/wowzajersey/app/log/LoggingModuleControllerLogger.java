package com.fererlab.wowzajersey.app.log;

import com.fererlab.wowzajersey.core.log.BaseLogger;

import java.util.logging.Logger;

public class LoggingModuleControllerLogger extends BaseLogger {

    private Logger logger = Logger.getLogger(getClass().getSimpleName());

    @Override
    protected Logger getLogger() {
        return logger;
    }

    public void authorizeClient(Object client) {
        log("will authenticate client: " + client);
    }

    public void authenticateHttpSession(Object httpSession) {
        log("will authorize http session: " + httpSession);
    }

    public void clientConnected(Object client) {
        log("a client connected, client: " + client);
    }

    public void clientDisconnected(Object client) {
        log("a client disconnected, client: " + client);
    }

    public void configure() {
        log("configuration method called");
    }
}
