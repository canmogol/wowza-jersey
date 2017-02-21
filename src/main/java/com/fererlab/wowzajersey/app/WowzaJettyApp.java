package com.fererlab.wowzajersey.app;

import com.fererlab.wowzajersey.core.app.BaseApp;

/**
 * main application
 */
public class WowzaJettyApp extends BaseApp {

    /**
     * singleton WowzaJettyApp application's private constructor
     */
    private WowzaJettyApp() {
    }

    /**
     * Thread safe method for getting singleton instance
     *
     * @return WowzaJettyApp
     */
    public static WowzaJettyApp getInstance() {
        return WowzaJettyAppInstance.instance;
    }

    private static final class WowzaJettyAppInstance {
        private static final WowzaJettyApp instance = new WowzaJettyApp();
        static {
            System.out.println("instance = " + instance);
            // check AppLifecycle interface for init and start methods' explanations
            // init method should be called only once
            instance.init();
            // it is safe to call start and stop methods multiple times
            instance.start();
        }
        private WowzaJettyAppInstance(){
        }
    }
}
