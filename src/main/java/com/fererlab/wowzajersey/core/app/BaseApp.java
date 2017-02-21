package com.fererlab.wowzajersey.core.app;

import com.fererlab.wowzajersey.core.config.Configurator;
import com.fererlab.wowzajersey.core.config.XmlConfigurator;
import com.fererlab.wowzajersey.core.controller.Controller;
import com.fererlab.wowzajersey.core.controller.ControllerCreator;
import com.fererlab.wowzajersey.core.event.Event;
import com.fererlab.wowzajersey.core.log.BaseAppLogger;
import com.fererlab.wowzajersey.core.repository.ModuleHandlerRepository;
import com.fererlab.wowzajersey.core.service.creator.ServiceCreator;
import com.fererlab.wowzajersey.core.service.listener.ServiceListener;
import com.fererlab.wowzajersey.core.service.loader.ServiceLoader;
import com.fererlab.wowzajersey.core.service.loader.ThreadedServiceLoader;
import com.fererlab.wowzajersey.core.service.type.Service;

import java.util.List;
import java.util.stream.Stream;

public abstract class BaseApp implements AppLifecycle, ServiceListener {

    /**
     * default configuration file
     */
    private String configurationFile = "configuration.xml";

    private Configurator configurator;
    private ServiceLoader serviceLoader;
    private ServiceCreator serviceCreator;
    private ControllerCreator controllerCreator;
    private ModuleHandlerRepository moduleHandlerRepository;

    private BaseAppLogger logger = new BaseAppLogger();
    private List<Service> services;
    private List<Controller> controllers;

    /**
     * lifecycle method, should be called once
     */
    @Override
    public void init() {
        logger.init();
        configurator = new XmlConfigurator();
        serviceLoader = new ThreadedServiceLoader();
        serviceCreator = new ServiceCreator();
        controllerCreator = new ControllerCreator();
        moduleHandlerRepository = new ModuleHandlerRepository();
    }

    /**
     * lifecycle method, may be called multiple times
     */
    @Override
    public void start() {
        logger.start();
        // load configuration from file
        configurator.loadConfiguration(configurationFile);
        // create and load services
        services = serviceCreator.create(configurator.getConfiguration().getServices(), this);
        serviceLoader.loadServices(services);
        // configure services
        services.forEach(service -> configurator.configure(service));
        // create and load controllers
        controllers = controllerCreator.create(configurator.getConfiguration().getControllers());
        // configure controllers
        controllers.forEach(controller -> configurator.configure(controller));
    }

    /**
     * lifecycle method, may be called multiple times
     */
    @Override
    public void stop() {
        logger.stop();
        serviceLoader.unloadAllServices();
    }

    @Override
    public void handle(Event event) {
        throw new RuntimeException("Handle this event: " + event);
    }

    public ModuleHandlerRepository getModuleHandlerRepository() {
        return moduleHandlerRepository;
    }

    @SuppressWarnings("unchecked")
    public <T extends Controller> T getController(Class<T> tClass) {
        Stream<Controller> controllerStream = controllers.stream().filter(controller -> controller.getClass().isAssignableFrom(tClass));
        if (controllerStream.count() == 0) {
            throw new RuntimeException("there is no " + tClass + " available");
        } else if (controllerStream.count() > 1) {
            throw new RuntimeException("there are multiple " + tClass + " defined");
        }
        return (T) controllerStream.findFirst().get();
    }

}
