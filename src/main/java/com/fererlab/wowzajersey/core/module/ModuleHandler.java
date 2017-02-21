package com.fererlab.wowzajersey.core.module;

import com.fererlab.wowzajersey.app.model.Client;

/**
 * classes implement this interface each should handle a media server module
 */
public interface ModuleHandler {

    /**
     * find client of its media server module with unique id
     *
     * @param uniqueId client id
     * @return {@link Client}
     */
    Client findClient(String uniqueId);

    /**
     * disconnect client from its media server module
     *
     * @param client to disconnect
     * @return true if client disconnected, otherwise false
     */
    boolean disconnectClient(Client client);

}

