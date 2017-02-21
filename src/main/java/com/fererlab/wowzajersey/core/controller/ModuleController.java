package com.fererlab.wowzajersey.core.controller;

import com.fererlab.wowzajersey.core.config.Configurable;
import com.fererlab.wowzajersey.app.model.Client;
import com.fererlab.wowzajersey.app.model.HttpSession;

/**
 * Controller for module operations
 */
public interface ModuleController extends Controller, Configurable {

    /**
     * this method calls an authenticator and returns its result
     *
     * @param client {@link Client}
     * @return true if client is authenticated, otherwise false
     */
    boolean authorizeClient(Client client);

    /**
     * this method calls an authorizer and returns its result
     *
     * @param httpSession {@link HttpSession}
     * @return true if httpSession is authorized, otherwise false
     */
    boolean authenticateHttpSession(HttpSession httpSession);

    /**
     * handle client connect
     *
     * @param client {@link Client}
     */
    void clientConnected(Client client);

    /**
     * handle client disconnect
     *
     * @param client {@link Client}
     */
    void clientDisconnected(Client client);

}
