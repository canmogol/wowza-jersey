package com.fererlab.wowzajersey.app.controller;

import com.fererlab.wowzajersey.app.WowzaJettyApp;
import com.fererlab.wowzajersey.core.repository.ModuleHandlerRepository;

public class ModuleClientController extends LoggingClientController {

    private ModuleHandlerRepository repository = WowzaJettyApp.getInstance().getModuleHandlerRepository();

    @Override
    public boolean disconnectClient(String uniqueId) {
        logger.disconnectClient(uniqueId);
        return repository.notifyDisconnectClient(uniqueId);
    }

}
