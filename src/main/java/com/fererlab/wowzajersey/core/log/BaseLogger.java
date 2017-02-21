package com.fererlab.wowzajersey.core.log;

import java.util.logging.Logger;

public abstract class BaseLogger implements WowzaJettyLogger {

    protected abstract Logger getLogger();

    public void log(String log) {
        getLogger().info("[" + Thread.currentThread().getId() + "] [" + getClass().getSimpleName() + "] " + log);
    }

    public void error(String log) {
        getLogger().severe("[" + Thread.currentThread().getId() + "] [" + getClass().getSimpleName() + "] " + log);
    }

}
