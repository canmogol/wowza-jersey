package com.fererlab.wowzajersey.core.service.type;

import com.fererlab.wowzajersey.core.config.Configurable;
import com.fererlab.wowzajersey.core.event.Event;
import com.fererlab.wowzajersey.core.service.listener.ServiceListener;

public interface Service extends Configurable, Runnable {

    /**
     * lifecycle method start
     * this service is expected to start serving with this method call
     */
    void start();

    /**
     * lifecycle method stop
     * this service is expected to stop serving after this method call
     */
    void stop();

    /**
     * services may have listeners
     *
     * @param listener service listener
     */
    void addListener(ServiceListener listener);


    /**
     * notifies attached listeners
     *
     * @param event data containing object
     */
    void notifyListeners(Event event);

}
