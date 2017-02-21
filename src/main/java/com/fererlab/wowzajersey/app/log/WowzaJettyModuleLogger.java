package com.fererlab.wowzajersey.app.log;

import com.fererlab.wowzajersey.core.log.BaseLogger;

import java.util.logging.Logger;

public class WowzaJettyModuleLogger extends BaseLogger {

    private Logger logger = Logger.getLogger(getClass().getSimpleName());

    @Override
    protected Logger getLogger() {
        return logger;
    }

    public void authenticate(Object client) {
        log("will call authenticator to authenticate client: " + client);
    }

    public void authorize(Object httpSession) {
        log("will call authorizer to authorize session: " + httpSession);
    }
}
