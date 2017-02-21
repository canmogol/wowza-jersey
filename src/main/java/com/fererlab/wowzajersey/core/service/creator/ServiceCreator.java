package com.fererlab.wowzajersey.core.service.creator;

import com.fererlab.wowzajersey.core.log.ServiceCreatorLogger;
import com.fererlab.wowzajersey.core.service.type.Service;
import com.fererlab.wowzajersey.core.service.listener.ServiceListener;

import java.util.ArrayList;
import java.util.List;

public class ServiceCreator {

    private ServiceCreatorLogger logger = new ServiceCreatorLogger();

    public List<Service> create(List<Class<? extends Service>> serviceClasses) {
        List<Service> services = new ArrayList<>();
        for (Class<? extends Service> serviceClass : serviceClasses) {
            Service service = create(serviceClass);
            services.add(service);
        }
        return services;
    }

    public List<Service> create(List<Class<? extends Service>> serviceClasses, ServiceListener listener) {
        List<Service> services = new ArrayList<>();
        for (Class<? extends Service> serviceClass : serviceClasses) {
            Service service = create(serviceClass, listener);
            services.add(service);
        }
        return services;
    }

    public Service create(Class<? extends Service> serviceClass) {
        Service service = null;
        try {
            service = serviceClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            logger.couldNotCreateService(serviceClass.getName(), e.getMessage());
        }
        return service;
    }

    public Service create(Class<? extends Service> serviceClass, ServiceListener listener) {
        Service service = create(serviceClass);
        service.addListener(listener);
        return service;
    }

}
