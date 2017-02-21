package com.fererlab.wowzajersey.core.controller;

import com.fererlab.wowzajersey.app.model.Client;
import com.fererlab.wowzajersey.core.config.Configurable;

/**
 * Controller for client operations
 */
public interface ClientController extends Controller, Configurable {

    /**
     * this method notifies all modules that this client should disconnect
     *
     * @param uniqueId {@link Client} object's uniqueId
     * @return true if client is disconnected, otherwise false
     */
    boolean disconnectClient(String uniqueId);


}
