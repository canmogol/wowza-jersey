package com.fererlab.wowzajersey.core.module;

import com.fererlab.wowzajersey.app.model.Client;

/**
 * every media server module should implement this interface
 */
public interface MediaServerModule {

    /**
     * check if a client with this unique id exists
     *
     * @param uniqueId client id
     * @return {@link Client}
     */
    boolean hasClient(String uniqueId);

    /**
     * get client with this unique id
     * @param uniqueId client id
     * @return {@link Client}
     */
    Client getClient(String uniqueId);

    /**
     * Disconnect client's connection / stream
     * @param client {@link Client}
     * @return false only if client could not be disconnected, otherwise true
     */
    boolean disconnectClient(Client client);

}
