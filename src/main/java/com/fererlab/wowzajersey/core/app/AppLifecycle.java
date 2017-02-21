package com.fererlab.wowzajersey.core.app;

public interface AppLifecycle {

    /**
     * lifecycle method, should be called once
     */
    void init();

    /**
     * lifecycle method, safe to call more than once
     */
    void start();

    /**
     * lifecycle method, safe to call more than once
     */
    void stop();

}
