package com.fererlab.wowzajersey.core.service.loader;

import com.fererlab.wowzajersey.core.service.type.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadedServiceLoader implements ServiceLoader {

    private ExecutorService executorService = Executors.newCachedThreadPool();
    private List<Service> services = new ArrayList<>();

    @Override
    public void loadServices(List<Service> services) {
        this.services.addAll(services);
        for (Service service : services) {
            executorService.execute(service);
        }
    }

    @Override
    public void unloadServices(List<Service> services) {
        for (Service service : services) {
            service.stop();
            if (this.services.contains(service)) {
                this.services.remove(service);
            }
        }
    }

    @Override
    public void unloadAllServices() {
        for (Service service : this.services) {
            service.stop();
            if (this.services.contains(service)) {
                this.services.remove(service);
            }
        }
    }

}
