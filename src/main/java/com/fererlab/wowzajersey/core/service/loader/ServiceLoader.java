package com.fererlab.wowzajersey.core.service.loader;

import com.fererlab.wowzajersey.core.service.type.Service;

import java.util.List;

public interface ServiceLoader {

    /**
     * loads given services, implementations may use a threaded approach
     * loading services may or may not start the service, this is an implementation detail of each service
     * these services should be added to the internal service list,
     * at a later point in time {@link #unloadAllServices()} may be called on this list
     *
     * @param services services to load
     */
    void loadServices(List<Service> services);

    /**
     * unloads the given services, removes these services from internal service list,
     * may call {@link Service#stop()} that's not a prerequisite for this method's implementation
     *
     * @param services services to unload
     */
    void unloadServices(List<Service> services);

    /**
     * unloads services available in the internal service list, removes services from internal service list,
     * may call {@link Service#stop()} that's not a prerequisite for this method's implementation
     *
     */
    void unloadAllServices();

}
