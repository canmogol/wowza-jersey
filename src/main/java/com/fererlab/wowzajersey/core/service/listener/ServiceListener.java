package com.fererlab.wowzajersey.core.service.listener;

import com.fererlab.wowzajersey.core.event.Event;

public interface ServiceListener {

    /**
     * Should handle the event
     *
     * @param event service event
     */
    void handle(Event event);

}
