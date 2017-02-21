package com.fererlab.wowzajersey.core.repository;

import com.fererlab.wowzajersey.app.model.Client;
import com.fererlab.wowzajersey.core.module.ModuleHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository for Module Handlers
 */
public class ModuleHandlerRepository {

    /**
     * collection of module handlers
     */
    private List<ModuleHandler> moduleHandlers = new ArrayList<>();

    /**
     * adds module handler to collection
     *
     * @param moduleHandler media server module's handler
     */
    public void register(ModuleHandler moduleHandler) {
        this.moduleHandlers.add(moduleHandler);
    }

    /**
     * removes module handler from collection
     *
     * @param moduleHandler media server module's handler
     */
    public void unregister(ModuleHandler moduleHandler) {
        this.moduleHandlers.remove(moduleHandler);
    }

    /**
     * notifies module handlers to disconnect the client with this uniqueId
     *
     * @param uniqueId {@link Client} object's unique id
     * @return true if this client is disconnected
     */
    public boolean notifyDisconnectClient(String uniqueId) {
        boolean disconnected = false;
        for (ModuleHandler moduleHandler : moduleHandlers) {
            Client client = moduleHandler.findClient(uniqueId);
            if (client != null && moduleHandler.disconnectClient(client)) {
                disconnected = true;
                break;
            }
        }
        return disconnected;
    }
}
