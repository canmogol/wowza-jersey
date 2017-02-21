package com.fererlab.wowzajersey.core.service.type;

import com.fererlab.wowzajersey.core.config.Configuration;
import com.fererlab.wowzajersey.core.event.Event;
import com.fererlab.wowzajersey.core.log.BaseServiceLogger;
import com.fererlab.wowzajersey.core.service.listener.ServiceListener;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseService implements Service {

    private BaseServiceLogger logger = new BaseServiceLogger();

    private List<ServiceListener> listeners = new ArrayList<>();

    /**
     * this method might be a good point to handle the configuration of the service
     */
    @Override
    public void run() {
        logger.emptyRunMethod(getClass().getName());
    }

    @Override
    public void addListener(ServiceListener listener) {
        this.listeners.add(listener);
    }

    @Override
    public void notifyListeners(Event event) {
        for (ServiceListener listener : this.listeners) {
            listener.handle(event);
        }
    }


    @Override
    public void configure(Configuration configuration) {
        logger.configure();
    }
}
