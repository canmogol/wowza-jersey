package com.fererlab.wowzajersey.core.service.type;

import com.fererlab.wowzajersey.core.log.BaseServiceLogger;

public abstract class StartFastService extends BaseService {

    private BaseServiceLogger logger = new BaseServiceLogger();

    /**
     * this method will call {@link Service#start()} to start the service immediately
     */
    @Override
    public void run() {
        this.start();
    }

}
