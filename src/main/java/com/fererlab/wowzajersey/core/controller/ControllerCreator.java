package com.fererlab.wowzajersey.core.controller;

import com.fererlab.wowzajersey.core.log.ControllerCreatorLogger;

import java.util.ArrayList;
import java.util.List;

public class ControllerCreator {

    private ControllerCreatorLogger logger = new ControllerCreatorLogger();

    public List<Controller> create(List<Class<? extends Controller>> controllerClasses) {
        List<Controller> controllers = new ArrayList<>();
        for (Class<? extends Controller> controllerClass : controllerClasses) {
            Controller controller = create(controllerClass);
            controllers.add(controller);
        }
        return controllers;
    }

    public Controller create(Class<? extends Controller> controllerClass) {
        Controller controller = null;
        try {
            controller = controllerClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            logger.couldNotCreateController(controllerClass.getName(), e.getMessage());
        }
        return controller;
    }

}
