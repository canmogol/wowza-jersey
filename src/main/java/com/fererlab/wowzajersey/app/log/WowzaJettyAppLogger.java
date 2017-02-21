package com.fererlab.wowzajersey.app.log;

import com.fererlab.wowzajersey.core.log.BaseLogger;

import java.util.logging.Logger;

public class WowzaJettyAppLogger extends BaseLogger {

    private Logger logger = Logger.getLogger(getClass().getSimpleName());

    @Override
    protected Logger getLogger() {
        return logger;
    }

}
