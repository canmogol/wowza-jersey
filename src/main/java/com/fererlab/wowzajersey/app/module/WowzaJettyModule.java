package com.fererlab.wowzajersey.app.module;

import com.fererlab.wowzajersey.app.WowzaJettyApp;
import com.fererlab.wowzajersey.core.module.MediaServerModule;
import com.fererlab.wowzajersey.core.module.ModuleHandler;
import com.fererlab.wowzajersey.app.model.HttpSession;
import com.fererlab.wowzajersey.core.repository.ModuleHandlerRepository;
import com.fererlab.wowzajersey.core.controller.ModuleController;
import com.fererlab.wowzajersey.app.log.WowzaJettyModuleLogger;
import com.fererlab.wowzajersey.app.model.Client;

/**
 * WowzaJettyModule is the internal representation of the WowzaJetty module, data types and lifecycle
 * separated from media server's internal API
 */
public class WowzaJettyModule extends LoggerModuleLifecycleListener implements ModuleHandler {

    /**
     * reference to media server's module object
     */
    private final MediaServerModule mediaServerModule;

    /**
     * WowzaJettyModule's logger
     */
    private WowzaJettyModuleLogger logger = new WowzaJettyModuleLogger();

    /**
     * repository to hold the module handlers,
     * it is expected to be more than one ModuleHandler,
     * each ModuleHandler should be created per media server's Module
     */
    private ModuleHandlerRepository moduleHandlerRepository = WowzaJettyApp.getInstance().getModuleHandlerRepository();

    /**
     * Controller for module operations
     */
    private ModuleController moduleController = WowzaJettyApp.getInstance().getController(ModuleController.class);

    public WowzaJettyModule(MediaServerModule mediaServerModule) {
        this.mediaServerModule = mediaServerModule;
    }

    /*
     * below are overridden lifecycle methods
     */

    @Override
    public void onStart(String applicationFullName) {
        moduleHandlerRepository.register(this);
        super.onStart(applicationFullName);
    }

    @Override
    public void onStop(String applicationFullName) {
        moduleHandlerRepository.unregister(this);
        super.onStop(applicationFullName);
    }

    @Override
    public void onConnect(Client client) {
        moduleController.clientConnected(client);
        super.onConnect(client);
    }

    @Override
    public void onDisconnect(Client client) {
        moduleController.clientDisconnected(client);
        super.onDisconnect(client);
    }

    /*
     * below are WowzaJetty Module specific methods
     */

    /**
     * this method calls an authenticator and returns its result
     *
     * @param client {@link Client}
     * @return true if client is authenticated, otherwise false
     */
    public boolean authenticate(Client client) {
        logger.authenticate(client);
        return moduleController.authorizeClient(client);
    }

    /**
     * this method calls an authorizer and returns its result
     *
     * @param httpSession {@link HttpSession}
     * @return true if httpSession is authorized, otherwise false
     */
    public boolean authorize(HttpSession httpSession) {
        logger.authorize(httpSession);
        return moduleController.authenticateHttpSession(httpSession);
    }

    /*
     * below are Module Handler specific methods
     */

    @Override
    public Client findClient(String uniqueId) {
        Client client = null;
        if (this.mediaServerModule.hasClient(uniqueId)) {
            client = this.mediaServerModule.getClient(uniqueId);
        }
        return client;
    }

    @Override
    public boolean disconnectClient(Client client) {
        return this.mediaServerModule.disconnectClient(client);
    }
}
